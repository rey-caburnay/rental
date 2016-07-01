package com.shinn.configuration;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import com.shinn.dao.factory.JndiDBManager;
import com.shinn.service.SampleService;
import com.shinn.service.SampleServiceImpl;

@Configuration
@ComponentScan({ "com.shinn.dao" })
@PropertySource(value = { "classpath:application.properties" })
public class DatabaseConfiguration {
    
    @Autowired
    private Environment environment;
    
    /**
     * using property files
     * @return
     */
    
    @Bean(name="dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }
   
    
    /**
     * using jndi lookup
     * @return
     */
//    @Bean(name = "jdbc/MySQL57")
//    public DataSource dataSourceLookup() {
//        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
//        dsLookup.setResourceRef(true);
//        DataSource dataSource = dsLookup.getDataSource("java:comp/env/jdbc/MySQL57");
//        return dataSource;
//    }
    
    @Bean(name ="connection")
    public Connection connection() throws SQLException {
        Connection connection = dataSource().getConnection();
        return connection;
    }
    
    @Bean
    public SampleService getSampleService() {
        return new SampleServiceImpl();
    }
   
}
