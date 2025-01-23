package com.example.javaPergunta.rest.endpoints.openApi;

import com.example.javaPergunta.rest.endpoints.resources.AccountResource;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;

public interface AccountControllerOpenApi {
    String ACCOUNT_URL =  "/api/accounts";
    String ACCOUNT_URL_ID = ACCOUNT_URL + "/{id}";
    //ResponseEntity findAll(Long comboId, Long variableId, Long cdCombination, Pageable pageable);
    ResponseEntity save(AccountResource resource);
    //ResponseEntity update(Long id, AccountResource resource);
    //ResponseEntity delete(String id);
    ResponseEntity findById(String id);

}
