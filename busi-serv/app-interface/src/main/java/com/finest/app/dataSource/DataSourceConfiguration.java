
package com.finest.app.dataSource;


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
    @ConfigurationProperties("app.datasource.layer")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean("layerSource")
    @Primary
    @ConfigurationProperties("app.datasource.layer")
    public DataSource firstDataSource() {

         return firstDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public JdbcTemplate firstJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


}
