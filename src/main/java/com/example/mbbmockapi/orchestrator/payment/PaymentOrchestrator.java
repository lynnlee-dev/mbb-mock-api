package com.example.mbbmockapi.orchestrator.payment;

import java.math.BigDecimal;

public interface PaymentOrchestrator {
    void transferMoney(String accountNumberFrom, String accountNumberTo, BigDecimal amount);
}
