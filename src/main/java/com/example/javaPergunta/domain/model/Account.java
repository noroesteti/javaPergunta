package com.example.javaPergunta.domain.model;

import com.example.javaPergunta.domain.valueobject.Money;

import java.time.Instant;

public interface Account {
    String getId();

    Money getBalance();
    void deposit(Money amount);
    void withdraw(Money amount);
    Instant getCreatedDate();

}
