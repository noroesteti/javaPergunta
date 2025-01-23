package com.example.javaPergunta;

import com.example.javaPergunta.domain.model.Account;
import com.example.javaPergunta.domain.model.SavingsAccount;
import com.example.javaPergunta.rest.endpoints.resources.AccountResource;
import com.example.javaPergunta.service.AccountService;
import com.example.javaPergunta.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl();
    }


    @Test
    void testStreamAccount(){
        AccountResource accountResource = new AccountResource();
        accountResource.setId("555555");
        Account account = accountService.createSavingsAccount(accountResource);
        AccountResource accountResource2 = new AccountResource();
        accountResource2.setId("666666");
        Account account2 = accountService.createSavingsAccount(accountResource2);
        assertNotNull(account);
        assertNotNull(account2);

        account.deposit(10);
        account2.deposit(20);

        List<Account> accounts = Arrays.asList(
             new SavingsAccount("55555", 11),
             new SavingsAccount("66666", 20)
        );

        List<String> accountsFilered = accounts.stream()
                .filter(p -> p.getBalance() > 10)
                .sorted((p1, p2) -> p1.getId().compareTo(p2.getId()))
                .map(Account::getId)
                .collect(Collectors.toList());

        accountsFilered.forEach(System.out::println);

    }

//    @Test
//    void testCreateSavingsAccount() {
//        Account account = accountService.createSavingsAccount("12345");
//        assertNotNull(account);
//        assertEquals("12345", account.getAccountNumber());
//    }
//
//    @Test
//    void testDeposit() {
//        accountService.createSavingsAccount("12345");
//        accountService.deposit("12345", 100.0);
//        assertEquals(100.0, accountService.getAccount("12345").getBalance());
//    }
//
//    @Test
//    void testWithdraw() {
//        accountService.createSavingsAccount("12345");
//        accountService.deposit("12345", 100.0);
//        accountService.withdraw("12345", 50.0);
//        assertEquals(50.0, accountService.getAccount("12345").getBalance());
//    }
//
//    @Test
//    void testWithdrawInsufficientFunds() {
//        accountService.createSavingsAccount("12345");
//        assertThrows(IllegalArgumentException.class, () -> accountService.withdraw("12345", 50.0));
//    }
//
//    @Test
//    void testInvalidDepositAmount() {
//        accountService.createSavingsAccount("12345");
//        assertThrows(IllegalArgumentException.class, () -> accountService.deposit("12345", -10.0));
//    }
}
