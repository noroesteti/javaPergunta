package com.example.javaPergunta.domain.model;

public interface Account {
    String getAccountNumnber();
    double getBalance();
    void deposit(double amount);
    void withdraw(double amount);

}
