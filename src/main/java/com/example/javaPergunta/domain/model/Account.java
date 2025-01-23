package com.example.javaPergunta.domain.model;

public interface Account {
    String getId();

    double getBalance();
    void deposit(double amount);
    void withdraw(double amount);

}
