package com.example.mbbmockapi.orchestrator.bankAccount;

import com.example.mbbmockapi.model.dto.account.BankAccountSummary;
import com.example.mbbmockapi.model.dto.common.PageResponse;
import com.example.mbbmockapi.model.entity.BankAccount;
import com.example.mbbmockapi.repository.BankAccountRepository;
import com.example.mbbmockapi.service.bankAccount.BankAccountService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Qualifier("saving")
public class SavingAccountOrchestrator implements BankAccountOrchestrator {
    private final BankAccountService bankAccountService;
    private final BankAccountRepository bankAccountRepository;

    public SavingAccountOrchestrator(
            @Qualifier("saving") BankAccountService bankAccountService,
            BankAccountRepository bankAccountRepository
        ) {
        this.bankAccountService = bankAccountService;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccountSummary createAccount(String accountHolderName) {
        // may contain other business logic

        BankAccount savedAccount = bankAccountService.createAccount(accountHolderName);
        return BankAccountSummary.from(savedAccount);
    }

    @Override
    public PageResponse<BankAccountSummary> getAllAccounts(Pageable pageable) {
        // since no transaction needed, direct call from repo to reduce overhead
        Page<BankAccountSummary> accounts =  bankAccountRepository.findAllBy(pageable, BankAccountSummary.class);
        return new PageResponse<>(accounts);
    }

    @Override
    public void topup(String accountNumber, BigDecimal amount) {
        bankAccountService.updateBalance(accountNumber, amount);
    }
}
