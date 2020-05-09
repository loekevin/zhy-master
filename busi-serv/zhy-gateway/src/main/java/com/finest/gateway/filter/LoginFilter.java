package com.finest.gateway.filter;

import com.finest.zhy.comm.constant.CommonConstants;
import com.finest.zhy.comm.util.RedisClientUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by kezy on 2020/4/29.
 */
@Slf4j
@Component
public class LoginFilter  extends ZuulFilter {

    //非拦截地址
    @Value("${app.filter.noInterceptUrl}")
    private String noInterceptUrl;

    @Autowired
    private RedisClientUtil redisUtil;

    //非拦截地址
    private List<String> noAuthUrlList;

    /**
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     * **/
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     *  filterOrder：过滤的顺序
     *  */
    @Override
    public int filterOrder() {
        return 2;
    }

    /**
     * 这里可以写逻辑判断，是否要过滤，本文true,永远过滤
     * */
    @Override
    public boolean shouldFilter() {
        String[] noUrl =  noInterceptUrl.split(",");
        noAuthUrlList = Arrays.asList(noUrl);
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String uri = request.getRequestURI();
        log.info("uri:{}", uri);
        if (!CollectionUtils.isEmpty(noAuthUrlList)) {
            for (String str : noAuthUrlList) {
                if (uri.contains(str)) {
                   return false;
                }
            }
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest request = ctx.getRequest();
            log.info("send  {} request to {} ",request.getMethod(),request.getRequestURL().toString());
            String accessToken=request.getHeader(CommonConstants.ACCESS_TOKEN);
            if(StringUtils.isEmpty(accessToken)){
                accessToken = request.getParameter(CommonConstants.TOKEN_KEY_STRING);
            }
            if(StringUtils.isEmpty(accessToken)) {
                log.warn("access token is empty");
                ctx.setSendZuulResponse(false); //表示不继续转发该请求。
                ctx.setResponseStatusCode(401);
                ctx.setResponseBody("access token is empty");
                return  null;
            }
            Object userStr = redisUtil.get(CommonConstants.ACCESS_TOKEN +"_"+ accessToken);
            //验证token正确性
            if(userStr== null || StringUtils.isBlank(userStr.toString())) {
//            if(userStr==null||!userInfoDto.getToken().equals(accessToken)) {
                log.warn("access token is invalid");
                ctx.setSendZuulResponse(false); //表示不继续转发该请求。
                ctx.setResponseStatusCode(401);
                ctx.setResponseBody("access token time out");
                return  null;
            }
            log.warn("user-->{}"+userStr.toString());
            //放到request对象中,方便后续方法取值
//            request.setAttribute("useId",userStr);
            this.addParam(ctx,userStr.toString());
            //刷新token
            redisUtil.set(CommonConstants.ACCESS_TOKEN +"_"+ accessToken,userStr,Long.valueOf(300));
            log.info("access token ok");
            return null;
    }

    public void addParam(RequestContext ctx,String userStr){
        Map<String,List<String>> requestQueryParams=ctx.getRequestQueryParams();
        if(requestQueryParams==null) {
            requestQueryParams=new HashMap<>();
        }
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(userStr.toString());
        requestQueryParams.put("user", arrayList);
        ctx.setRequestQueryParams(requestQueryParams);
    }
}
