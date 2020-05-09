package com.finest.zhy.log.service.handler;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 默认日志处理类 on 2018/6/27 0027.
 */
public class DefaultLogHandle implements LogHandle{

    @Override
    public String getIp() {
        HttpServletRequest request=getRequest();
        String ip=request.getHeader("x-forwarded-for");
        if(!StringUtils.isNotEmpty(ip)||"unknown".equals(ip)){
            ip=request.getHeader("X-Real-IP");
        }
        if(!StringUtils.isNotEmpty(ip)||"unknown".equals(ip)){
            ip=request.getHeader("Proxy-Client-IP");
        }
        if(!StringUtils.isNotEmpty(ip)||"unknown".equals(ip)){
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        if(!StringUtils.isNotEmpty(ip)||"unknown".equals(ip)){
            ip=request.getRemoteAddr();
        }
        if(ip.contains(",")){
            return ip.split(",")[0];
        }
        return ip;
    }

    @Override
    public String getUserId() {
      /*  User user=(User) getRequest().getSession().getAttribute(Constants.USER_INFO_SESSION);
        return user==null?null:user.getLoginId();*/
        return null;
    }

    @Override
    public String getSyscode() {
        return "-1";
    }

    @Override
    public String getImei() {
        return getRequest().getHeader("user-agent");
    }




    private HttpServletRequest getRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return request;
    }

}
