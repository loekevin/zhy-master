package com.finest.zhy.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Administrator on 2018/6/27 0027.
 */
@SpringBootApplication
public class WebApplication {
    public static void main(String args[]){
        new SpringApplication(WebApplication.class).run(WebApplication.class);
    }
}
