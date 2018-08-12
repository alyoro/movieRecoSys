package com.example.movieRecoSys.config;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.example.movieRecoSys.credential")
public class CredentialContext {

    @Autowired
    private Environment env;

    @Bean
    public DataSource customDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("postgresql.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("postgresql.datasource.url"));
        dataSource.setUsername(env.getProperty("postgresql.datasource.username"));
        dataSource.setPassword(env.getProperty("postgresql.datasource.password"));
        return dataSource;
    }
}