package com.example.javaPergunta.rest.endpoints.resources;

import org.springframework.web.multipart.MultipartFile;

public class StorageFile {
    private final String bucketName;
    private final String fileName;
    private final MultipartFile file;

    public StorageFile(String bucketName, String fileName, MultipartFile file) {
        this.bucketName = bucketName;
        this.fileName = fileName;
        this.file = file;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getFileName() {
        return fileName;
    }

    public MultipartFile getFile() {
        return file;
    }
}