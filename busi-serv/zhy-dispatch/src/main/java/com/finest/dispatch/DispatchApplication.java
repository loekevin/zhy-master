package com.finest.dispatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/***
 * 指挥调度模块
 *
 * @author kezy
 * @date 2020-04-20
 * **/
@SpringBootApplication(scanBasePackages ="com.finest.dispatcher,com.finest.zhy" )
@EnableEurekaClient
@EnableAutoConfiguration
@EnableCaching
public class DispatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(DispatchApplication.class, args);
	}

}
