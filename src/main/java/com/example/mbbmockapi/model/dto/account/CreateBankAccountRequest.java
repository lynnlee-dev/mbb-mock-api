package com.example.mbbmockapi.model.dto.account;

import jakarta.validation.constraints.NotBlank;

public record CreateBankAccountRequest(
        @NotBlank(message = "Source account holder name is required")
        String accountHolderName
) {}
