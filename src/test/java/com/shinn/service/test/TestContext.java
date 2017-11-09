package com.shinn.service.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.PlatformTransactionManager;

import com.shinn.service.UserService;
import com.shinn.service.UserServiceImpl;

/**
 * @author Petri Kainulainen
 */
@Configuration
@ComponentScan({ "com.shinn.dao", "com.shinn.service" })
public class TestContext {

    private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/messages";
    
    @Autowired
    private Environment environment;
//
//    @Bean
//    public MessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//
//        messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
//        messageSource.setUseCodeAsDefaultMessage(true);
//
//        return messageSource;
//    }
    
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/caburnay_realmaster");
        dataSource.setUsername("caburnay_aptz");
        dataSource.setPassword("r3@lM@st3rZ");
        return dataSource;
    }
//
    @Bean(name = "connection")
    public Connection connection() throws SQLException {
        Connection connection = dataSource().getConnection();
        return connection;
    }
    
    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
   
}
