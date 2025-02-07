package com.example.javaPergunta.rest.endpoints.resources;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public class StorageFile {
    private final String bucketName;
    private final String fileName;
    private  MultipartFile file;
    private  Path path = null;

    public StorageFile(String bucketName, String fileName, MultipartFile file, Path path) {
        this.bucketName = bucketName;
        this.fileName = fileName;
        this.file = file;
        this.path = path;
    }

    public StorageFile(String bucketName, String fileName,MultipartFile file) {
        this.bucketName = bucketName;
        this.fileName = fileName;
        this.file = file;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public MultipartFile getFile() {
        return this.file;
    }

    public Path getPath(){
        return this.path;
    }
}