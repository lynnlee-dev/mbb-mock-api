package com.example.mbbmockapi.model.dto.common;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data
) {
    // Static factory methods for cleaner controller syntax
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, message, data);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "successful", data);
    }
}
