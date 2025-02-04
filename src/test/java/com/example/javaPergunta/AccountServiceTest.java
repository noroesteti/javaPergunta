package com.example.javaPergunta;

import com.example.javaPergunta.domain.model.Account;
import com.example.javaPergunta.domain.model.SavingsAccount;
import com.example.javaPergunta.domain.valueobject.Money;
import com.example.javaPergunta.rest.endpoints.resources.AccountResource;
import com.example.javaPergunta.service.AccountService;
import com.example.javaPergunta.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;
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

        account.deposit(new Money(new BigDecimal("10.00")));
        account2.deposit(new Money(new BigDecimal("20.00")));

        List<Account> accounts = Arrays.asList(
             new SavingsAccount("55555"),
             new SavingsAccount("66666")
        );

        for (Account account1 : accounts) {
            //System.out.println(account1.getId() + " - " + account1.getBalance());
            BigDecimal min = new BigDecimal("10.50"); // Lower bound
            BigDecimal max = new BigDecimal("100.75"); // Upper bound
            BigDecimal randomValue = generateRandomBigDecimal(min, max, 2);
            account1.deposit(new Money(randomValue));
        }
        for (Account s : accounts) {
            System.out.println(s.toString());

        }
        List<String> accountsFilered = accounts.stream()
                .filter(p -> p.getBalance().isGreaterThan(new Money(new BigDecimal("11.00"))))
                .sorted((p1, p2) -> p1.getId().compareTo(p2.getId()))
                .map(Account::getId)
                .collect(Collectors.toList());

       // accountsFilered.forEach(System.out::println);



    }

    public static BigDecimal generateRandomBigDecimal(BigDecimal min, BigDecimal max, int scale) {
        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random())
                .multiply(max.subtract(min)));
        return randomBigDecimal.setScale(scale, RoundingMode.HALF_UP);
    }
//
//    @BeforeEach
//    void setup() {
//        accountService.createSavingsAccount("12345");
//    }
//
//    @Test
//    void testCreateSavingsAccount() {
//        Account account = accountService.createSavingsAccount("67890");
//        assertNotNull(account);
//        assertEquals("67890", account.getId());
//        assertNotNull(account.getCreatedDate());
//    }
//
//    @Test
//    void testDeposit() {
//        accountService.deposit("12345", new Money(500));
//        Account account = accountService.getAccount("12345");
//        assertEquals(500, account.getBalance().getAmount());
//    }
//
//    @Test
//    void testWithdraw() {
//        accountService.deposit("12345", new Money(500));
//        accountService.withdraw("12345", new Money(200));
//        Account account = accountService.getAccount("12345");
//        assertEquals(300, account.getBalance().getAmount());
//    }
//
//    @Test
//    void testInsufficientBalance() {
//        accountService.deposit("12345", new Money(100));
//        assertThrows(IllegalArgumentException.class, () -> accountService.withdraw("12345", new Money(200)));
//    }
//
//    @Test
//    void testAccountNotFound() {
//        assertThrows(IllegalArgumentException.class, () -> accountService.getAccount("99999"));
//    }
//
//    @Test
//    void testDuplicateAccountCreation() {
//        assertThrows(IllegalArgumentException.class, () -> accountService.createSavingsAccount("12345"));
//    }
}
