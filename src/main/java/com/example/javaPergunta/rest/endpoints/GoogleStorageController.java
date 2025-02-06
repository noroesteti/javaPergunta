package com.example.javaPergunta.rest.endpoints;

import com.example.javaPergunta.domain.exceptions.NotFoundException;
import com.example.javaPergunta.rest.endpoints.openApi.GoogleStorageControllerOpenApi;
import com.example.javaPergunta.rest.endpoints.resources.StorageFile;
import com.example.javaPergunta.service.GoogleStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import static java.lang.String.format;

import com.example.javaPergunta.rest.endpoints.resources.StorageFile;
import com.example.javaPergunta.service.GoogleStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/gcs")
public class GoogleStorageController {
    private final GoogleStorageService googleStorageService;

    public GoogleStorageController(GoogleStorageService googleStorageService) {
        this.googleStorageService = googleStorageService;
    }

    @GetMapping("/download/{bucketName}/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String bucketName, @PathVariable String fileName) {
        StorageFile storage = new StorageFile(bucketName, fileName, null);
        return ResponseEntity.ok(googleStorageService.downloadFile(storage));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam String bucketName, @RequestParam("file") MultipartFile file) throws IOException {
        googleStorageService.uploadFile(new StorageFile(bucketName, file.getOriginalFilename(), file));
        return ResponseEntity.ok("File uploaded successfully");
    }
}
