package com.example.javaPergunta.service;

import com.example.javaPergunta.domain.model.Account;
import com.example.javaPergunta.domain.model.SavingsAccount;
import com.example.javaPergunta.rest.endpoints.resources.AccountResource;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Map;
import java.util.NoSuchElementException;
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
        validateAccountNumber(resource.getid());
        Account account = new SavingsAccount(resource.getid());
        accounts.put(resource.getid(), account);
        return account;
    }

    @Override
    public Account getAccount(String id) {
        validateAccountNumber(id);
        return Optional.ofNullable(accounts.get(id))
                .orElseThrow(() -> new NotFoundException("Account not found."));
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        executorService.execute(() -> getAccount(accountNumber).deposit(amount));
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        executorService.execute(() -> getAccount(accountNumber).withdraw(amount));
    }

    private void validateAccountNumber(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty.");
        }
    }
}
