package com.itacademy.config;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:database.properties")
public class DatabaseConfig {

    @Autowired
    Environment environment;

    @Bean(value = "dataSource", destroyMethod = "close")
    @Scope("singleton")
    public BasicDataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("driverName"));
        dataSource.setUrl(environment.getProperty("url"));
        dataSource.setUsername(environment.getProperty("login"));
        dataSource.setPassword(environment.getProperty("password"));
        dataSource.setInitialSize(Integer.parseInt(environment.getProperty("initialSize")));
        dataSource.setMaxActive(Integer.parseInt(environment.getProperty("maxActive")));
        return dataSource;
    }

}
