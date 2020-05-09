package com.finest;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 分布式事务配置
 * Created by kezy on 2020/4/14.
 */
@SpringBootApplication
@EnableTransactionManagerServer
@EnableDiscoveryClient
public class TxLcnApplication {

    public static void main(String[] args) {
        SpringApplication.run(TxLcnApplication.class, args);
    }

}
