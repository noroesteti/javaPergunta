package com.example.javaPergunta.rest.endpoints;

import com.example.javaPergunta.domain.exceptions.NotFoundException;
import com.example.javaPergunta.rest.endpoints.openApi.GoogleStorageControllerOpenApi;
import com.example.javaPergunta.service.GoogleStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/gcs")
public class GoogleStorageController implements GoogleStorageControllerOpenApi {
    @Autowired
    private final GoogleStorageService googleStorageService;

    public GoogleStorageController(GoogleStorageService googleStorageService) {
        this.googleStorageService = googleStorageService;
    }

    @GetMapping("/buckets")
    public List<String> getBuckets() {
        return googleStorageService.listBuckets();
    }

    public ResponseEntity<byte[]> downloadFile(@PathVariable String bucketName,
                                               @PathVariable String filename){
        byte[] fileContent = googleStorageService.douwloadFile(bucketName, filename);
        if (fileContent == null){
            throw new NotFoundException(format("File %s not found!", filename));
        }
        return ResponseEntity.ok().body(fileContent);
    }

}
