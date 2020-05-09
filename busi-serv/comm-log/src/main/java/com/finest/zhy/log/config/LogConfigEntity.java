package com.finest.zhy.log.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/6/28 0028.
 */
@Component
@ConfigurationProperties(prefix = "log")
public class LogConfigEntity {

    private String handle;


    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }
}
