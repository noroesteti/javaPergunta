package com.example.javaPergunta.service;

import com.example.javaPergunta.domain.exceptions.NotFoundException;
import com.example.javaPergunta.infra.gcp.GoogleStorageClient;
import com.example.javaPergunta.rest.endpoints.resources.StorageFile;
import com.google.cloud.storage.Bucket;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.String.format;

@Service
public class GoogleStorageService {
    private final GoogleStorageClient googleStorageClient;

    public GoogleStorageService(GoogleStorageClient googleStorageClient) {
        this.googleStorageClient = googleStorageClient;
    }

    public byte[] downloadFile(StorageFile file) {
        byte[] content = googleStorageClient.downloadFile(file.getBucketName(), file.getFileName());
        if (content == null) {
            throw new NotFoundException(format("File %s not found!", file.getFileName()));
        }
        return content;
    }

    public void uploadFile(StorageFile file) throws IOException {
        // Convert MultipartFile to a temporary file
        File tempFile = File.createTempFile("upload-", file.getFileName());
        file.getFile().transferTo(tempFile);
        Path path = tempFile.toPath();

        // Upload file
        googleStorageClient.uploadFile(file.getBucketName(), file.getFileName(), path);

        // Delete temporary file after upload
        tempFile.delete();
    }


}