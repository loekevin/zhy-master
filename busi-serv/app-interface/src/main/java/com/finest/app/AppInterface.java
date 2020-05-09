package com.finest.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/***
 * 公共资源服务接口
 *
 * @author kezy
 * @date 2019-11-20
 * **/
@SpringBootApplication(scanBasePackages ="com.finest.app" )
@EnableEurekaClient
@EnableAutoConfiguration
@EnableFeignClients
public class AppInterface {

	public static void main(String[] args) {
		SpringApplication.run(AppInterface.class, args);
	}

}
