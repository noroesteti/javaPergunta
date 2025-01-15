package com.example.javaPergunta.service;

import com.example.javaPergunta.domain.model.Account;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountServiceImpl implements AccountService{
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public Account createSavingAccount(String accountNumber) {
        validateAccountNumber(accountNumber);
        return null;
    }

    @Override
    public Account getAccount(String accountNumber) {
        return null;
    }

    @Override
    public void deposit(String accountNumber, double amount) {

    }

    @Override
    public void withdraw(String accountNumber, double amount) {

    }

    private void validateAccountNumber(String accountNumber){
        if (accountNumber == null || accountNumber.isEmpty()){
            throw new IllegalArgumentException("Account number must not be empty");
        }
    }
}
