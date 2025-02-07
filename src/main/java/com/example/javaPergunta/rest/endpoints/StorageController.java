package com.example.javaPergunta.rest.endpoints;

import com.example.javaPergunta.rest.endpoints.resources.StorageFile;
import com.example.javaPergunta.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/storage")
public class StorageController {
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/buckets")
    public ResponseEntity<List<String>> getBuckets() {
        return ResponseEntity.ok(storageService.listBuckets());
    }

    @GetMapping("/download/{bucketName}/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String bucketName, @PathVariable String fileName) {
        StorageFile storage = new StorageFile(bucketName, fileName, null);
        return ResponseEntity.ok(storageService.downloadFile(storage));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam String bucketName, @RequestParam("file") MultipartFile file) throws IOException {
        storageService.uploadFile(new StorageFile(bucketName, file.getOriginalFilename(), file));
        return ResponseEntity.ok("File uploaded successfully");
    }

}
