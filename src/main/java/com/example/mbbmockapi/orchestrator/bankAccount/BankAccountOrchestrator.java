package com.example.mbbmockapi.orchestrator.bankAccount;

import com.example.mbbmockapi.model.dto.account.BankAccountSummary;
import com.example.mbbmockapi.model.dto.common.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountOrchestrator {
    BankAccountSummary createAccount(String accountHolderName);
    PageResponse<BankAccountSummary> getAllAccounts(Pageable pageable);
    void topup(String accountNumber, BigDecimal amount);
}
