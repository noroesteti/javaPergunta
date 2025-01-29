package com.example.javaPergunta.service;

import com.example.javaPergunta.domain.exceptions.NotFoundException;
import com.example.javaPergunta.domain.model.Account;
import com.example.javaPergunta.domain.model.SavingsAccount;
import com.example.javaPergunta.domain.valueobject.Money;
import com.example.javaPergunta.rest.endpoints.resources.AccountResource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Service
public class AccountServiceImpl implements AccountService{
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public Account createSavingsAccount(AccountResource resource) {
        if (accounts.containsKey(resource.getid())){
            throw new IllegalArgumentException("Account with this number already exists");
        }
        Account account = new SavingsAccount(resource.getid());
        accounts.put(resource.getid(), account);
        return account;
    }

    @Override
    @Cacheable(value = "accounts", key = "#accountNumber")
    public Account getAccount(String id) {

        return Optional.ofNullable(accounts.get(id))
                .orElseThrow(() -> new NotFoundException("Account not found."));
    }

    @Override
    public void deposit(String accountNumber, Money amount) {
        executorService.execute(() -> getAccount(accountNumber).deposit(amount));
    }

    @Override
    public void withdraw(String accountNumber, Money amount) {
        executorService.execute(() -> getAccount(accountNumber).withdraw(amount));
    }

}
