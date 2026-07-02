package com.example.mbbmockapi.client;

import com.example.mbbmockapi.config.RandomUserProperties;
import com.example.mbbmockapi.model.dto.common.RandomUserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class RandomUserClient {
    private final WebClient webClient;

    public RandomUserClient(WebClient.Builder webClientBuilder, RandomUserProperties props) {
        this.webClient = webClientBuilder
                .baseUrl(props.getBaseUrl())
                .build();
    }

    public RandomUserResponse getRandomUser() {
        return webClient.get()
                .uri("/api/")
                .retrieve()
                .bodyToMono(RandomUserResponse.class)
                .block(); // block for mocking simple call
    }
}
