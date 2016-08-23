package com.shinn.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.shinn.configuration.SpringWebConfig;

@EnableAutoConfiguration
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringWebConfig.class, args);
    }
}
