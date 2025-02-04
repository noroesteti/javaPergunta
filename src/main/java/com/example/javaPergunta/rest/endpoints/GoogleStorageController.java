package com.example.javaPergunta.rest.endpoints;

import com.example.javaPergunta.rest.endpoints.openApi.GoogleStorageControllerOpenApi;
import com.example.javaPergunta.service.GoogleStorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gcs")
public class GoogleStorageController implements GoogleStorageControllerOpenApi {

    private final GoogleStorageService googleStorageService;

    public GoogleStorageController(GoogleStorageService googleStorageService) {
        this.googleStorageService = googleStorageService;
    }

    @GetMapping("/buckets")
    public List<String> getBuckets() {
        return googleStorageService.listBuckets();
    }

}
