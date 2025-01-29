package com.example.javaPergunta.rest.endpoints;


import com.example.javaPergunta.domain.model.Account;
import com.example.javaPergunta.domain.valueobject.Money;
import com.example.javaPergunta.rest.endpoints.openApi.AccountControllerOpenApi;
import com.example.javaPergunta.rest.endpoints.resources.AccountResource;
import com.example.javaPergunta.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(produces = HAL_JSON_VALUE)
public class AccountController implements AccountControllerOpenApi {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    @Operation(description = "Do you want to save an account? Here is the place baby")
    @RequestMapping(path = ACCOUNT_URL, method = GET)
    public ResponseEntity save(@RequestParam AccountResource resource) {
        Account entity = accountService.createSavingsAccount(resource);
        return ResponseEntity.ok(entity);
    }
    @Override
    @RequestMapping(path = ACCOUNT_URL_ID, method = GET)
    public ResponseEntity findById(@PathVariable(value = "id", name = "id") String id) {
        Account entity = accountService.getAccount(id);
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/{accountNumber}/deposit")
    public void deposit(@PathVariable String accountNumber, @RequestParam double amount) {
        accountService.deposit(accountNumber, new Money(new BigDecimal(amount)));
    }

    @PostMapping("/{accountNumber}/withdraw")
    public void withdraw(@PathVariable String accountNumber, @RequestParam double amount) {
        accountService.withdraw(accountNumber, new Money(new BigDecimal(amount)));
    }

}
