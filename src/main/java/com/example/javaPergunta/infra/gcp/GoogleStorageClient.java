package com.example.javaPergunta.infra.gcp;
import com.google.cloud.storage.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class GoogleStorageClient {
    private final Storage storage;

    public GoogleStorageClient(Storage storage) {
        this.storage = storage;
    }

    public List<String> listBuckets() {
        return StreamSupport.stream(storage.list().iterateAll().spliterator(), false)
                .map(Bucket::getName)
                .collect(Collectors.toList());
    }

    public byte[] downloadFile(String bucketName, String fileName) {
        Blob blob = storage.get(BlobId.of(bucketName, fileName));
        return blob != null ? blob.getContent() : null;
    }

    public void uploadFile(String bucketName, String fileName, Path filePath) throws IOException {
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        storage.createFrom(blobInfo, filePath);
    }
}