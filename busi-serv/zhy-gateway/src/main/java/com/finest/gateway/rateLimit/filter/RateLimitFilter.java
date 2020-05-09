package com.finest.gateway.rateLimit.filter;

import com.finest.gateway.rateLimit.server.RateLimitStrategist;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kezy on 2019-11-22.
 * 限流拦截器
 */
@Component
@Configuration
public class RateLimitFilter implements Filter {

//    @Autowired
    private RateLimitStrategist rateLimitStrategist;

    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimitFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        rateLimitStrategist = ctx.getBean("rateLimitStrategist",RateLimitStrategist.class);
        LOGGER.info("[rateLimitStrategist对象],初始化...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        if(rateLimitStrategist == null) {
//            rateLimitStrategist = new RateLimitStrategist();
//        }
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        if(StringUtils.isNotBlank(contextPath)) {
            requestURI = StringUtils.substring(requestURI, contextPath.length());
        }
        if(!rateLimitStrategist.tryAcquire(requestURI)) {
            res.setContentType("text/html;charset=UTF-8");
            res.setStatus(HttpStatus.OK.value());
            response.getWriter().write("当前服务器繁忙，请稍后再试！");
            LOGGER.info(requestURI + "路径请求服务器繁忙，请稍后再试");
        } else {
            try {
                LOGGER.info(requestURI + "成功获取许可证！");
                chain.doFilter(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rateLimitStrategist.releaseSemaphore(requestURI);
            }
        }

    }

    @Override
    public void destroy() {

    }
}