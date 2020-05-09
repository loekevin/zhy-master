package com.finest.terminal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/***
 * 公共资源服务接口
 *
 * @author kezy
 * @date 2019-11-20
 * **/
@SpringBootApplication(scanBasePackages ="com.finest.terminal,com.finest.zhy" )
@EnableEurekaClient
@EnableAutoConfiguration
@EnableCaching
public class ResourceInterface {

	public static void main(String[] args) {
		SpringApplication.run(ResourceInterface.class, args);
	}

}
