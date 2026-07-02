package com.example.mbbmockapi.model.dto.account;

import com.example.mbbmockapi.model.entity.BankAccount;

import java.math.BigDecimal;

public record BankAccountSummary(
        String accountHolderName,
        String accountNumber,
        BigDecimal balance
) {
    public static BankAccountSummary from(BankAccount account) {
        return new BankAccountSummary(
                account.getAccountNumber(),
                account.getAccountHolderName(),
                account.getBalance()
        );
    }
}