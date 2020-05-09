package com.finest.gateway.rateLimit.dataSource;


import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 多数据源配置.<br>
 *
 * Created by kezy on 2019/11/27.
 */
@Configuration
public class DataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.comm")
    public DataSourceProperties commDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean("commSource")
    @Primary
    @ConfigurationProperties("app.datasource.comm")
    public DataSource commDataSource() {

         return commDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    public JdbcTemplate commJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


}
