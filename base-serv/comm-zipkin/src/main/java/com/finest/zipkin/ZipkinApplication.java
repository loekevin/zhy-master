package com.finest.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.internal.EnableZipkinServer;

/***
 * 服务链路追踪 zipkin
 *
 * @author kezy
 * @date 2019-11-20
 * **/

@EnableZipkinServer//默认采用HTTP通信方式启动ZipkinServer
//@EnableZipkinStreamServer//采用Stream通信方式启动ZipkinServer，也支持HTTP通信方式
@EnableEurekaClient
@SpringBootApplication
public class ZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinApplication.class, args);
	}

}
