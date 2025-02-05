package com.example.javaPergunta.service;


import com.example.javaPergunta.domain.exceptions.NotFoundException;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.String.format;

@Service
public class GoogleStorageService {

    private final Storage storage;

    public GoogleStorageService(Storage storage) {
        this.storage = storage;
    }


    public List<String> listBuckets() {
        return StreamSupport.stream(storage.list().iterateAll().spliterator(), false)
                .map(Bucket::getName)
                .collect(Collectors.toList());
    }

    public byte[] douwloadFile(String bucketName, String filename){
        Blob blob = storage.get(BlobId.of(bucketName, filename));
        if (blob == null){
            throw new NotFoundException(format("File %s not found!", filename));
        }
        return blob.getContent();
    }
}
