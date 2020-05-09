package com.finest.gateway.rateLimit.server;

import com.finest.gateway.dto.RateLimitDto;
import com.finest.gateway.dto.RateLimitInfo;
import com.finest.gateway.rateLimit.service.RateLimiterService;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by kezy on 2019-11-22.
 * 限流策略
 */
@Component
public class RateLimitStrategist {

    private PathMatcher pathMatcher = new AntPathMatcher();

    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimitStrategist.class);
    /**
     * 限流服务集合
     * **/
    private final Map<String, RateLimitInfo> limiterMap = new LinkedHashMap<>();

    private final Map<String, Semaphore> threadMap = new LinkedHashMap<>();

    /**
     * 更新频率，意为后台配置路径后5分钟生效
     */
    private static final long UPDATE_RATE = 1000*60*5;

    private long lastUpdateTime = 0;

    @Autowired
    private RateLimiterService rateLimitManager;

    /**
     * 初始化方法
     * **/
    public void init() {
        limiterMap.clear();
        threadMap.clear();
        List<RateLimitDto> rateLimitVos = rateLimitManager.findListForPriority(); //查询数据库中配置的路径信息，需要自己实现
        if(CollectionUtils.isEmpty(rateLimitVos)) {
            return;
        }
        for (RateLimitDto rateLimitVo : rateLimitVos) {
            RateLimiter rateLimiter = RateLimiter.create(rateLimitVo.getPermitsPerSecond());
            limiterMap.put(rateLimitVo.getLimitPath(), new RateLimitInfo(rateLimiter, rateLimitVo, System.currentTimeMillis()));
            threadMap.put(rateLimitVo.getLimitPath(), new Semaphore(rateLimitVo.getMaxThread(), true));
        }
        lastUpdateTime = System.currentTimeMillis();
    }

    /**
     * 获取令牌许可
     * **/
    public boolean tryAcquire(String requestUri) {
        //目前设置5分钟更新一次
        if(System.currentTimeMillis() - lastUpdateTime > UPDATE_RATE) {
            synchronized (this) {
                if(System.currentTimeMillis() - lastUpdateTime > UPDATE_RATE) {
                    init();
                }
            }
        }

        //匹配地址是否需要拦截
        for (Map.Entry<String, RateLimitInfo> entry : limiterMap.entrySet()) {
            if(!pathMatcher.match(entry.getKey(), requestUri)) {
                continue;
            }

            /**
             * 获取限流信息类
             * **/
            RateLimitInfo rateLimitInfo = entry.getValue();
            RateLimitDto rateLimitVo = rateLimitInfo.getRateLimitDto();
            RateLimiter rateLimiter = rateLimitInfo.getRateLimiter();

            /**
             * 从RateLimiter 获取指定许可数如果该许可数可以在不超过timeout的时间内获取得到的话，
             *  或者如果无法在timeout 过期之前获取得到许可数的话，那么立即返回false （无需等待）
             * */
            boolean concurrentFlag = rateLimiter.tryAcquire(1, rateLimitVo.getPermitsTimeOut(), TimeUnit.SECONDS);
            LOGGER.info(entry.getKey()+"-->"+concurrentFlag);
            if(!concurrentFlag) { //验证失败，直接返回
                return concurrentFlag;
            } else {
                if(threadMap.get(entry.getKey()).availablePermits() != 0) { //当前路径对应剩余可执行线程数不为0
                    try {
                        //申请可执行线程
                        Semaphore semaphore = threadMap.get(entry.getKey());
                        threadMap.get(entry.getKey()).acquire();
                        System.out.println("使用前--------->"+semaphore.availablePermits());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * 释放路径对应的线程数
     * @param requestURI
     */
    public void releaseSemaphore(String requestURI) {
        if(null != threadMap.get(requestURI)) {
            threadMap.get(requestURI).release();
            System.out.println("使用后--"+requestURI+"------>"+threadMap.get(requestURI).availablePermits());
        }
    }
}
