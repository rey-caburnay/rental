package com.shinn.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.shinn.configuration.SpringWebConfig;


@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringWebConfig.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringWebConfig.class, args);
    }

}

//public class Application {
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(SpringWebConfig.class, args);
//    }
//}
