package com.example.mbbmockapi.model.dto.payment;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record TransferRequest(
        @NotBlank(message = "Source account number is required")
        String accountNumberFrom,

        @NotBlank(message = "Target account number is required")
        String accountNumberTo,

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
        BigDecimal amount
) {}
