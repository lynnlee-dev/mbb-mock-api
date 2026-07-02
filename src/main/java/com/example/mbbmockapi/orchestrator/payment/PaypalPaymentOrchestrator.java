package com.example.mbbmockapi.orchestrator.payment;

import com.example.mbbmockapi.service.bankAccount.BankAccountService;
import com.example.mbbmockapi.service.transfer.TransferService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Qualifier("paypal")
public class PaypalPaymentOrchestrator implements PaymentOrchestrator {
    private final TransferService transferService;

    public PaypalPaymentOrchestrator(@Qualifier("paypal") TransferService transferService) {
        this.transferService = transferService;
    }

    @Override
    public void transferMoney(String accountNumberFrom, String accountNumberTo, BigDecimal amount) {
        // may contain other business logic
        transferService.executeTransfer(accountNumberFrom, accountNumberTo, amount);
    }
}
