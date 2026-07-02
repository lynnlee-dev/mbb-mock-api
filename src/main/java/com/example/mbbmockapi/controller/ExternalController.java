package com.example.mbbmockapi.controller;

import com.example.mbbmockapi.model.dto.common.ApiResponse;
import com.example.mbbmockapi.model.dto.common.RandomUserResponse;
import com.example.mbbmockapi.orchestrator.externalApi.ExternalApiOrchestrator;
import com.example.mbbmockapi.orchestrator.externalApi.RandomUserOrchestrator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/external")
public class ExternalController {
    private final ExternalApiOrchestrator externalApiOrchestrator;

    public ExternalController(@Qualifier("randomUser") ExternalApiOrchestrator externalApiOrchestrator) {
        this.externalApiOrchestrator = externalApiOrchestrator;
    }

    @GetMapping("/random")
    public ResponseEntity<ApiResponse<RandomUserResponse>> getRandomUser() {
        RandomUserResponse response = externalApiOrchestrator.fetchUser();
        log.info("External API response: {}", response);
        return ResponseEntity.ok(new ApiResponse<RandomUserResponse>(true, "successful", response));
    }

}
