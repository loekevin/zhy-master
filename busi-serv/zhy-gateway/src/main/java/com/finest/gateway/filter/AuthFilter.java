//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//
//package com.finest.gateway.filter;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.finest.zhy.comm.constant.CommonConstants;
//import com.finest.zhy.comm.dto.ResultJsonUtil;
//import com.finest.zhy.comm.util.RedisClientUtil;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Arrays;
//
///**
// * 网关鉴权
// */
////@Slf4j
//@Component
//public class AuthFilter  implements Filter {
//
////    // 排除过滤的uri地址 swagger排除自行添加
//    private static final String[] whiteList = {};
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);
//    @Autowired
//    private RedisClientUtil redisUtil;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse rsp = (HttpServletResponse) response;
//        String url = req.getRequestURL().toString();
//        System.out.println(url);
//        // 跳过不需要验证的路径
//        if (Arrays.asList(whiteList).contains(url)) {
//            chain.doFilter(req, rsp);
//            return;
//        }
//        String token = req.getHeader(CommonConstants.TOKEN_KEY_STRING);
//        // token为空
//        if (StringUtils.isBlank(token)) {
//            return setUnauthorizedResponse(exchange, "token can't null or empty string");
//        }
//        String userStr = redisUtil.get(CommonConstants.ACCESS_TOKEN + token).toString();
//        if (StringUtils.isBlank(userStr)) {
//            return setUnauthorizedResponse(exchange, "token verify error");
//        }
//        JSONObject jo = JSONObject.parseObject(userStr);
//        String userId = jo.getString("userId");
//// 查询token信息
//        if (StringUtils.isBlank(userId)) {
//            return setUnauthorizedResponse(exchange, "token verify error");
//        }
//// 设置userId到request里，后续根据userId，获取用户信息
//        ServerHttpRequest mutableReq = exchange.getRequest().mutate().header(CommonConstants.CURRENT_ID, userId)
//                .header(CommonConstants.CURRENT_USERNAME, jo.getString("loginName")).build();
//        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
//        return chain.filter(mutableExchange);
//    }
////
////    // 排除过滤的uri地址 swagger排除自行添加
////    private static final String[] whiteList = {};
////
////    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);
////    @Autowired
////    private RedisClientUtil redisUtil;
////
////    @Override
////    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
////        String url = exchange.getRequest().getURI().getPath();
////        LOGGER.info("url:{}", url);
////        // 跳过不需要验证的路径
////        if (Arrays.asList(whiteList).contains(url)) {
////            return chain.filter(exchange);
////        }
////        String token = exchange.getRequest().getHeaders().getFirst(CommonConstants.TOKEN_KEY_STRING);
////        // token为空
////        if (StringUtils.isBlank(token)) {
////            return setUnauthorizedResponse(exchange, "token can't null or empty string");
////        }
////        String userStr = redisUtil.get(CommonConstants.ACCESS_TOKEN + token).toString();
////        if (StringUtils.isBlank(userStr)) {
////            return setUnauthorizedResponse(exchange, "token verify error");
////        }
////        JSONObject jo = JSONObject.parseObject(userStr);
////        String userId = jo.getString("userId");
////        // 查询token信息
////        if (StringUtils.isBlank(userId)) {
////            return setUnauthorizedResponse(exchange, "token verify error");
////        }
////        // 设置userId到request里，后续根据userId，获取用户信息
////        ServerHttpRequest mutableReq = exchange.getRequest().mutate().header(CommonConstants.CURRENT_ID, userId)
////                .header(CommonConstants.CURRENT_USERNAME, jo.getString("loginName")).build();
////        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
////        return chain.filter(mutableExchange);
////    }
////
//    private void setUnauthorizedResponse(HttpServletResponse resp, String msg) {
//        resp.setContentType("application/json;charset=UTF-8");
//        byte[] response = null;
//        try {
//            response = JSON.toJSONString(ResultJsonUtil.build(401, msg)).getBytes(CommonConstants.UTF8);
//            return ;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        DataBuffer buffer = resp..bufferFactory().wrap(response);
//        return originalResponse.writeWith(Flux.just(buffer));
//    }
////
////    @Override
////    public int getOrder() {
////        return -200;
////    }
//}