package com.example.javaPergunta.service;

import com.example.javaPergunta.domain.exceptions.NotFoundException;
import com.example.javaPergunta.domain.port.output.StorageServicePort;
import com.example.javaPergunta.infra.gcp.GoogleStorageAdapter;
import com.example.javaPergunta.rest.endpoints.resources.StorageFile;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static java.lang.String.format;

@Service
public class StorageService {
    private final StorageServicePort storageServicePort;

    public StorageService(@Qualifier("localStorage")StorageServicePort storageServicePort) {
        this.storageServicePort = storageServicePort;
    }

    public byte[] downloadFile(StorageFile file) {
        byte[] content = storageServicePort.downloadFile(file);
        if (content == null) {
            throw new NotFoundException(format("File %s not found!", file.getFileName() + " se estiver VAZIO debugar o getFileName()"));
        }
        return content;
    }


    public void uploadFile(StorageFile file) throws IOException {
        // Upload file
        storageServicePort.uploadFile(file);
    }

    public List<String> listBuckets() {
        return storageServicePort.listBuckets();
    }

}