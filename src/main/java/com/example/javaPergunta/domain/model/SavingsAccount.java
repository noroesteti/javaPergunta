package com.example.javaPergunta.domain.model;

import com.example.javaPergunta.domain.valueobject.AccountStatus;
import com.example.javaPergunta.domain.valueobject.DateClass;
import com.example.javaPergunta.domain.valueobject.Money;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;

public class SavingsAccount implements Account{

    @Schema(name = "Account ID", example = "1", required = true, description = "This is the Account Number")
    private final String id;
    private Money balance;
    private final Instant createdDate;

    private AccountStatus accountStatus;
    private final Object lock = new Object();

    public SavingsAccount(String id) {
        this.id = id;
        this.balance = new Money(new BigDecimal("00.00"));
        this.createdDate = new DateClass().getDateTime();
        this.accountStatus =AccountStatus.PENDING;
    }

    @Override
    public String getId() {
        return this.id;
    }
    @Override
    public Money getBalance() {
        synchronized (lock) {
            return balance;
        }
    }

    @Override
    public void deposit(Money amount) {
        synchronized (lock) {
            balance = balance.add(amount);
        }
    }

    @Override
    public void withdraw(Money amount) {
        synchronized (lock) {
            balance = balance.subtract(amount);
        }
    }
    @Override
    public Instant getCreatedDate(){
        return createdDate;
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                ", createdDate=" + createdDate +
                ", lock=" + lock +
                '}';
    }
}
