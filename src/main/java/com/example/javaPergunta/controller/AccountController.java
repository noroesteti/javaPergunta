package com.example.javaPergunta.controller;

import com.example.javaPergunta.domain.model.Account;
import com.example.javaPergunta.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/savings")
    public Account createSavingsAccount(@RequestParam String accountNumber){
        return accountService.createSavingAccount(accountNumber);
    }

    @GetMapping("/{accountNumber}")
    public Account getAccount(@PathVariable String accountNumber){
        return accountService.getAccount(accountNumber);
    }

    @PostMapping("/{accountNumber}/deposit")
    public void deposit(@PathVariable String accountNumber, @RequestParam double amount){
        accountService.deposit(accountNumber, amount);
    }

    @PostMapping("/{accountNumber}/withdraw")
    public void withdraw(@PathVariable String accountNumber, @RequestParam double amount){
        accountService.withdraw(accountNumber, amount);
    }



}
