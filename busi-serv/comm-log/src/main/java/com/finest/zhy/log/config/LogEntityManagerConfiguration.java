package com.finest.zhy.log.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by Administrator on 2018/6/5 0005.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.finest.zhy.log.dao"},
        entityManagerFactoryRef = "logEntityManagerFactory",
        transactionManagerRef = "logTransationManager"
)
public class LogEntityManagerConfiguration implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(LogEntityManagerConfiguration.class);
    private static ApplicationContext context = null;
    @Autowired
    @Qualifier("logDataSource")
    DataSource dataSource;

    @Bean("logEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(adapter);
        factoryBean.setDataSource(dataSource);
        factoryBean.setPersistenceUnitName("logPersistenceUnit");
        factoryBean.setPackagesToScan("com.finest.zhy.log.domain");
        return factoryBean;
    }

    /**
     * 创建事务管理器
     *
     * @return
     */
    @Bean("logTransationManager")
    public PlatformTransactionManager firstTransactionManager(
            @Qualifier("logEntityManagerFactory") EntityManagerFactory factory) {

        return new JpaTransactionManager(factory);
    }

    /**
     * 创建entityManager实例
     *
     * @return
     */
    @Bean("logEntityManager")
    public EntityManager firstEntityManager(@Qualifier("logEntityManagerFactory") EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (context == null) {
            context = applicationContext;
        }
    }


}
