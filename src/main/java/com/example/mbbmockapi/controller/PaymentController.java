package com.example.mbbmockapi.controller;

import com.example.mbbmockapi.model.dto.common.ApiResponse;
import com.example.mbbmockapi.model.dto.payment.TransferRequest;
import com.example.mbbmockapi.orchestrator.payment.PaymentOrchestrator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentOrchestrator paymentOrchestrator;

    public PaymentController(@Qualifier("paypal") PaymentOrchestrator paymentOrchestrator) {
        this.paymentOrchestrator = paymentOrchestrator;
    }

    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse<Void>> transfer(@Valid @RequestBody TransferRequest request) {

        log.info(
                "Transfer request received. from={}, to={}, amount={}",
                request.accountNumberFrom(),
                request.accountNumberTo(),
                request.amount()
        );

        paymentOrchestrator.transferMoney(
                request.accountNumberFrom(),
                request.accountNumberTo(),
                request.amount()
        );

        log.info(
                "Transfer completed. from={}, to={}, amount={}",
                request.accountNumberFrom(),
                request.accountNumberTo(),
                request.amount()
        );

        return ResponseEntity.ok(new ApiResponse<>(true, "successful", null));
    }
}
