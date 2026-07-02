package com.example.mbbmockapi.controller;

import com.example.mbbmockapi.model.dto.account.BankAccountSummary;
import com.example.mbbmockapi.model.dto.account.CreateBankAccountRequest;
import com.example.mbbmockapi.model.dto.account.TopupBalanceRequest;
import com.example.mbbmockapi.model.dto.common.ApiResponse;
import com.example.mbbmockapi.model.dto.common.PageResponse;
import com.example.mbbmockapi.orchestrator.bankAccount.BankAccountOrchestrator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/account")
public class BankAccountController {
    private final BankAccountOrchestrator bankAccountOrchestrator;

    public BankAccountController(@Qualifier("saving") BankAccountOrchestrator bankAccountOrchestrator) {
        this.bankAccountOrchestrator = bankAccountOrchestrator;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BankAccountSummary>> createAccount(
            @Valid @RequestBody CreateBankAccountRequest request) {

        log.info("Create bank account accountName: {}", request.accountHolderName());

        BankAccountSummary response = bankAccountOrchestrator.createAccount(request.accountHolderName());

        log.info("Bank account created successfully. AccountName={}, Account Number={}",
                response.accountHolderName(), response.accountNumber());

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "successful", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<BankAccountSummary>>> getAllAccounts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        // Cap the size at a maximum of 100
        int cappedSize = Math.min(size, 100);

        log.info("Get bank accounts - Page: {}, Size: {}", page, cappedSize);

        // Dynamically pass both the page number and the requested size
        Pageable pageable = PageRequest.of(page - 1, cappedSize);

        PageResponse<BankAccountSummary> accounts =
                bankAccountOrchestrator.getAllAccounts(pageable);

        log.info("Get bank accounts: {}", accounts);

        return ResponseEntity.ok(new ApiResponse<>(true, "successful", accounts));
    }

    @PostMapping("/topup")
    public ResponseEntity<ApiResponse<Void>> topup(
            @Valid @RequestBody TopupBalanceRequest request) {

        log.info("Create bank account accountNumber: {}, amount: {}", request.accountNumber(), request.amount());

        bankAccountOrchestrator.topup(request.accountNumber(), request.amount());

        log.info("Bank account topup successfully. AccountName={}, Account Number={}",
                request.accountNumber(), request.amount());

        return ResponseEntity.ok(new ApiResponse<>(true, "successful", null));
    }
}
