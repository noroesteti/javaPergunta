package com.example.javaPergunta.domain.port.output;

import com.example.javaPergunta.rest.endpoints.resources.StorageFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/*The port defines what operations a storage service should support, making the system independent of specific providers.*/
public interface StorageServicePort {
    List<String> listBuckets();
    byte[] downloadFile(StorageFile file);
    void uploadFile(StorageFile file) throws IOException;

}
