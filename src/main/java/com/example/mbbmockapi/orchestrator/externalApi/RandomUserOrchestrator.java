package com.example.mbbmockapi.orchestrator.externalApi;

import com.example.mbbmockapi.client.RandomUserClient;
import com.example.mbbmockapi.model.dto.common.RandomUserResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("randomUser")
public class RandomUserOrchestrator implements ExternalApiOrchestrator {

    private final RandomUserClient randomUserClient;

    public RandomUserOrchestrator(RandomUserClient randomUserClient) {
        this.randomUserClient = randomUserClient;
    }

    @Override
    public RandomUserResponse fetchUser() {
        return randomUserClient.getRandomUser();
    }
}
