package com.example.javaPergunta.service;

import com.example.javaPergunta.domain.model.Account;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;

public interface AccountService {
    Account createSavingAccount(String accountNumber);
    Account getAccount(String accountNumber);
    void deposit(String accountNumber, double amount);
    void withdraw(String accountNumber, double amount);
}
