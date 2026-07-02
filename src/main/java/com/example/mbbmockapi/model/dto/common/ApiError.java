package com.example.mbbmockapi.model.dto.common;

public record ApiError(
        String code,
        String message
) {}
