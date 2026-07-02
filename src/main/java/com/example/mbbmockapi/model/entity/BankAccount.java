package com.example.mbbmockapi.model.entity;

import com.example.mbbmockapi.exception.InsufficientFundsException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank_accounts", uniqueConstraints = {
        @UniqueConstraint(name = "UC_AccountNumber", columnNames = {"account_number"})
})
public class BankAccount {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountHolderName;

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;

    @Version
    private Long version;

    public BankAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    // Helpers
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Inadequate funds for withdrawal");
        }
        this.balance = this.balance.subtract(amount);
    }

    @PrePersist
    private void generateAccountNumber() {
        if (this.accountNumber == null) {
            String prefix = "101";
            int randomDigits = ThreadLocalRandom.current().nextInt(1000000, 10000000);
            this.accountNumber = prefix + randomDigits;
        }
    }
}
