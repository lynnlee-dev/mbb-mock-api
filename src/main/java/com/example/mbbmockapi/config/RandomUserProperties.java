package com.example.mbbmockapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "random-user")
@Getter
@Setter
public class RandomUserProperties {
    private String baseUrl;
}