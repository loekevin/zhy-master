package com.finest.zhy.log.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Administrator on 2018/6/26 0026.
 */

//@Component
public class SpringUtil implements ApplicationContextAware{

    private static final Logger logger= LoggerFactory.getLogger(SpringUtil.class);

    private static ApplicationContext context = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(context==null){
            context=applicationContext;
        }
    }

    public static ApplicationContext getContext(){
        return context;
    }
}
