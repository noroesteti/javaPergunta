package com.example.javaPergunta.service;


import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

//    public void storeFile(){
//
//    }
}
