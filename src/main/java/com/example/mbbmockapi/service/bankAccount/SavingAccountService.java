package com.example.mbbmockapi.service.bankAccount;

import com.example.mbbmockapi.model.dto.account.BankAccountSummary;
import com.example.mbbmockapi.model.entity.BankAccount;
import com.example.mbbmockapi.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Qualifier("saving")
public class SavingAccountService implements BankAccountService{
    private final BankAccountRepository bankAccountRepository;

    public SavingAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    @Transactional
    public BankAccount createAccount(String accountHolderName) {
        BankAccount account = new BankAccount();
        account.setAccountHolderName(accountHolderName);

        // assuming here contains other entity dependencies for creating an account

        return bankAccountRepository.save(account);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public BankAccountSummary findAccountSummaryByAccountNumber(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber, BankAccountSummary.class)
                .orElseThrow(() -> new IllegalArgumentException("account not found"));
    }

    @Transactional
    public void updateBalance(String accountNumber, BigDecimal amount) {

        BankAccount account = bankAccountRepository
                .findByAccountNumberWithLock(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // update balance
        account.setBalance(account.getBalance().add(amount));

        bankAccountRepository.save(account);
    }

}
