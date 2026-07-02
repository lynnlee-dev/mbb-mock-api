package com.example.mbbmockapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MbbMockApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbbMockApiApplication.class, args);
    }

}
