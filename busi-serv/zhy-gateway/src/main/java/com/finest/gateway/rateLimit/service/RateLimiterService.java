package com.finest.gateway.rateLimit.service;

import com.finest.gateway.dto.RateLimitDto;
import com.finest.gateway.rateLimit.dao.RateLimiterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kezy on 2020/1/13.
 * 限流服务层
 */
@Service
public class RateLimiterService {

    @Autowired
    private RateLimiterDao rateLimiterDao;


    /**
     * 获取所有需要限流的服务
     * **/
    public List<RateLimitDto> findListForPriority(){
        return rateLimiterDao.findListForPriority();
    }

}
