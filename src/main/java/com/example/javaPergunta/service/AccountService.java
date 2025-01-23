package com.example.javaPergunta.service;

import com.example.javaPergunta.domain.model.Account;
import com.example.javaPergunta.rest.endpoints.resources.AccountResource;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;

public interface AccountService {
    Account createSavingsAccount(AccountResource resource);
    Account getAccount(String id);
    void deposit(String accountNumber, double amount);
    void withdraw(String accountNumber, double amount);
}
