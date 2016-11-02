package com.teamtreehouse.giflib.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import javax.sql.DataSource;

/**
 * Created by emil on 11/1/16.
 */
@Configuration
@PropertySource("app.properties")
public class DataConfig {

    @Autowired
    private Environment env;

    @Bean
    public org.springframework.orm.hibernate5.LocalSessionFactoryBean sessionFactory() {
        Resource config = new ClassPathResource("hibernate.cfg.xml");
        org.springframework.orm.hibernate5.LocalSessionFactoryBean sessionFactory = new org.springframework.orm.hibernate5.LocalSessionFactoryBean();
        sessionFactory.setConfigLocation(config);
        sessionFactory.setPackagesToScan(env.getProperty("giflib.entity.package"));
        sessionFactory.setDataSource(dataSource());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        //Set Driver class name
        ds.setDriverClassName(env.getProperty("giflib.db.driver"));
        //Set URL
        ds.setUrl(env.getProperty("giflib.db.url"));
        //Set username and password
        ds.setUsername(env.getProperty("giflib.db.username"));
        ds.setPassword(env.getProperty("giflib.db.password"));
        return ds;
    }


}
