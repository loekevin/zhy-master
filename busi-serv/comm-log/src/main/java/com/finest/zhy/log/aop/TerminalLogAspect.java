package com.finest.zhy.log.aop;


import com.finest.zhy.log.annotation.SystemControllerLog;
import com.finest.zhy.log.config.LogConfigEntity;
import com.finest.zhy.log.domain.InterfaceLog;
import com.finest.zhy.log.service.InterfaceLogService;
import com.finest.zhy.log.service.handler.DefaultLogHandle;
import com.finest.zhy.log.service.handler.LogHandle;
import com.finest.zhy.log.util.DateFormatUtil;
import com.finest.zhy.log.util.JsonUtils;
import com.finest.zhy.log.util.SpringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/26 0026.
 */
@Component
@Aspect
public class TerminalLogAspect {


    @Autowired
    private InterfaceLogService logService;

    @Autowired
    private LogConfigEntity entity;

    private LogHandle logHandle;

    private static final Logger LOGGER = LoggerFactory.getLogger(TerminalLogAspect.class);

    @Pointcut(value = "@within(com.finest.zhy.log.annotation.SystemControllerLog) || @annotation(com.finest.zhy.log.annotation.SystemControllerLog)")
    public void terminal() {
    }

    @Around(value = "terminal()")
    public Object doTerminalInterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        if (StringUtils.isNotBlank(entity.getHandle())) {
            logHandle = (LogHandle) SpringUtil.getContext().getBean(entity.getHandle());
        } else {
            logHandle = new DefaultLogHandle();
        }
        Signature sig = joinPoint.getSignature();
        MethodSignature mss = (MethodSignature) sig;
        Method method = ((MethodSignature) sig).getMethod();
        Map<String, Object> params = new HashMap();
        String[] parameNames = mss.getParameterNames();
        Object[] args = joinPoint.getArgs();
        //过滤文件 若有文件参数直接跳过
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                if (args[i].getClass() == MultipartFile[].class || args[i].getClass() == MultipartFile.class) {
                    continue;
                }
            }
            params.put(parameNames[i], args[i]);
        }
        SystemControllerLog an = method.getAnnotation(SystemControllerLog.class);
        an = an == null ? method.getDeclaringClass().getAnnotation(SystemControllerLog.class) : an;
        HttpServletRequest request = getRequest(joinPoint);
        Object result = null;
        InterfaceLog log = new InterfaceLog();
        try {
            log.setIssuccess("0");
            log.setRequestDesc(an.value());
            log.setAuthor(an.creator());
            log.setRequestMethod(method.getDeclaringClass().getName() + "." + method.getName());
            log.setRequestTime(DateFormatUtil.getCurrentDate());
            log.setRequestParams(JsonUtils.objectToJson(params));
            if (log.getRequestParams().length() > 1300) {
                LOGGER.info("接口服务名:" + log.getRequestDesc() + "--请求参数:" + log.getRequestParams());
                log.setRequestParams("请求数据太长不记录入库");
            }
            log.setRequestUrl(request.getRequestURI());
            log.setRequestId(DateFormatUtil.long2YyyyMmDdHHmm(DateFormatUtil.getCurrentDate()));
            result = joinPoint.proceed();
            log.setResponseParams(JsonUtils.objectToJson(result));
            if (log.getResponseParams().length() > 1300) {
                LOGGER.info("接口服务名:" + log.getRequestDesc() + "--响应参数:" + log.getResponseParams());
                log.setResponseParams("响应数据数据太长不记录入库");
            }
            return result;

        } catch (Throwable e) {
            log.setIssuccess("1");
            log.setResponseParams(e.getMessage());
            //抛出异常交给全局异常拦截统一处理
            throw e;
        } finally {
            log.setResponseTime(DateFormatUtil.getCurrentDate());
            log.setTimeDif(log.getResponseTime().getTime() - log.getRequestTime().getTime());

            process(log);
            LOGGER.info("日志记录");
            logService.addLog(log);
        }


    }

    private HttpServletRequest getRequest(ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Object[] args = joinPoint.getArgs(); // 接口实际要接收的参数
        if (request != null && ServletFileUpload.isMultipartContent(request)) { // 判断是不是包含文件
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null) {
                    if (args[i].getClass() == DefaultMultipartHttpServletRequest.class) {
                        request = (DefaultMultipartHttpServletRequest) args[i];
                        break;
                    }
                }
            }
        }
        return request;
    }


    private void process(InterfaceLog log) {
        log.setRequestIp(logHandle.getIp());
        log.setImei(logHandle.getImei());
        log.setUserId(logHandle.getUserId());
        log.setSyscode(logHandle.getSyscode());
    }


}
