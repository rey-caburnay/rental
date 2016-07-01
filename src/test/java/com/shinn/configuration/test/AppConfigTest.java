package com.shinn.configuration.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shinn.service.SampleService;
import com.shinn.service.SampleServiceImpl;



@Configuration
public class AppConfigTest {
    
    @Bean
    public SampleService getSampleService() {
        return new SampleServiceImpl();
    }

}
