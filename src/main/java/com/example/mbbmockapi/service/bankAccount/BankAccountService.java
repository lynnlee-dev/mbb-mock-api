package com.example.mbbmockapi.service.bankAccount;

import com.example.mbbmockapi.model.dto.account.BankAccountSummary;
import com.example.mbbmockapi.model.entity.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface BankAccountService {
    BankAccount createAccount(String accountHolderName);
    BankAccountSummary findAccountSummaryByAccountNumber(String accountNumber);
    void updateBalance(String accountNumber, BigDecimal amount);
}
