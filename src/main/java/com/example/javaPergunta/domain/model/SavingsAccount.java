package com.example.javaPergunta.domain.model;

public class SavingsAccount implements Account{
    private final String id;
    private double balance;
    private final Object lock = new Object();

    public SavingsAccount(String id) {
        this.id = id;
        this.balance = 0.0;
    }
    public SavingsAccount(String id, double amount){
        this.id = id;
        this.balance = amount;
    }

    @Override
    public String getId() {
        return this.id;
    }
    @Override
    public double getBalance() {
        synchronized (lock) {
            return balance;
        }
    }

    @Override
    public void deposit(double amount) {
        validateAmount(amount);
        synchronized (lock) {
            balance += amount;
        }
    }

    @Override
    public void withdraw(double amount) {
        validateAmount(amount);
        synchronized (lock) {
            if (balance >= amount) {
                balance -= amount;
            } else {
                throw new IllegalArgumentException("Insufficient balance.");
            }
        }
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }
}
