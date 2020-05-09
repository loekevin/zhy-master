package com.finest.gateway.rateLimit.dataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * Created by Administrator on 2018/6/5 0005.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.finest.zuul.**.dao"},
        entityManagerFactoryRef="rateLimiterEntityManagerFactory",
        transactionManagerRef="rateLimiterTransationManager"
                        )
public class EntityManagerConfiguration {

    @Autowired
    @Qualifier("commSource")
    DataSource dataSource;

    @Bean("rateLimiterEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean commEntityManagerFactory() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(adapter);
        factoryBean.setJpaDialect(new HibernateJpaDialect());
        factoryBean.setDataSource(dataSource);
        factoryBean.setPersistenceUnitName("commPersistenceUnit");
        factoryBean.setPackagesToScan("com.finest.comm.**.domain");
        return factoryBean;
    }

    /**
     * 创建事务管理器
     *
     * @return
     */
    @Bean("rateLimiterTransationManager")
    @Primary
    public PlatformTransactionManager commTransactionManager() {
        return new JpaTransactionManager(commEntityManagerFactory().getObject());
    }

    /**
     * 创建entityManager实例
     *
     * @return
     */
    @Bean("rateLimiterEntityManager")
    @Primary
    public EntityManager commEntityManager() {
        return commEntityManagerFactory().getObject().createEntityManager();
    }
}
