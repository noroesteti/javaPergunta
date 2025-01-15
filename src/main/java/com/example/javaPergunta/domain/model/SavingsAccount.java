package com.example.javaPergunta.domain.model;

public class SavingsAccount implements Account{
    private final String accountNumner;
    private double balance;

    private final Object lock = new Object();

    public SavingsAccount(String accountNumner) {
        this.accountNumner = accountNumner;
    }

    @Override
    public String getAccountNumnber() {
        return accountNumner;
    }

    @Override
    public double getBalance() {
        synchronized (lock){
            return balance;
        }
    }

    @Override
    public void deposit(double amount) {
        validadeAmount(amount);
        synchronized (lock) {
            balance += amount;
        }

    }

    @Override
    public void withdraw(double amount) {
        validadeAmount(amount);
        synchronized (lock){
            if (balance >= amount){
                balance -= amount;
            }else{
                throw new IllegalArgumentException("Amount must be greater than zero");
            }
        }

    }

    private void validadeAmount(double amount) {
        if(amount <= 0){
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }
}
