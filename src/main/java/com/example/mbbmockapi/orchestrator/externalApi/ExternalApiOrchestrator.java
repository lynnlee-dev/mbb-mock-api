package com.example.mbbmockapi.orchestrator.externalApi;

import com.example.mbbmockapi.model.dto.common.RandomUserResponse;

public interface ExternalApiOrchestrator {
    RandomUserResponse fetchUser();
}
