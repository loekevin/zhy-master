package com.finest.app.dataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
@EnableJpaRepositories(basePackages = {"com.finest.app.dao"},
        entityManagerFactoryRef="EntityManagerFactory",
        transactionManagerRef="TransationManager"
                        )
public class EntityManagerConfiguration {

    @Autowired
    DataSource dataSource;

    @Bean("EntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory() {


        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(adapter);
        factoryBean.setJpaDialect(new HibernateJpaDialect());
        factoryBean.setDataSource(dataSource);
        factoryBean.setPersistenceUnitName("firstPersistenceUnit");
        factoryBean.setPackagesToScan("com.finest.app.domain");
        return factoryBean;
    }

    /**
     * 创建事务管理器
     *
     * @return
     */
    @Bean("TransationManager")
    @Primary
    public PlatformTransactionManager firstTransactionManager() {
        return new JpaTransactionManager(firstEntityManagerFactory().getObject());
    }

    /**
     * 创建entityManager实例
     *
     * @return
     */
    @Bean("EntityManager")
    @Primary
    public EntityManager firstEntityManager() {
        return firstEntityManagerFactory().getObject().createEntityManager();
    }
}
