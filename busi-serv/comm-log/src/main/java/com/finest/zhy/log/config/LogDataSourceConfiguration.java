package com.finest.zhy.log.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.sql.DataSource;

/**
 * Created by Administrator on 2018/6/27 0027.
 */
@Configuration
public class LogDataSourceConfiguration {


    @Bean("logDataSource")
    @ConfigurationProperties("log.datasource")
    public DataSource getDataSource(){
        return DataSourceBuilder.create().build();
    }

}
