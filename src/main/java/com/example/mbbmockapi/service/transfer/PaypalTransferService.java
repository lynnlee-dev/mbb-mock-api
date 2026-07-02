package com.example.mbbmockapi.service.transfer;

import com.example.mbbmockapi.exception.InsufficientFundsException;
import com.example.mbbmockapi.model.entity.BankAccount;
import com.example.mbbmockapi.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Qualifier("paypal")
public class PaypalTransferService implements TransferService {
    private final BankAccountRepository bankAccountRepository;

    public PaypalTransferService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    @Transactional
    public void executeTransfer(String accountNumberFrom, String accountNumberTo, BigDecimal amount) {
        BankAccount source = bankAccountRepository.findByAccountNumberWithLock(accountNumberFrom)
                .orElseThrow(() -> new IllegalArgumentException("Source account not found"));

        if ((source.getBalance().compareTo(amount) < 0)) {
            throw new InsufficientFundsException("insufficient fund");
        }

        BankAccount target = bankAccountRepository.findByAccountNumberWithLock(accountNumberTo)
                .orElseThrow(() -> new IllegalArgumentException("Target account not found"));

        // Execute business logic mutating state
        source.withdraw(amount);
        target.deposit(amount);

        // Save updates back to database
        bankAccountRepository.save(source);
        bankAccountRepository.save(target);
    }
}
