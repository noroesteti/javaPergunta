package com.example.javaPergunta.infra.gcp;
import com.example.javaPergunta.domain.exceptions.NotFoundException;
import com.example.javaPergunta.domain.port.output.StorageServicePort;
import com.example.javaPergunta.rest.endpoints.resources.StorageFile;
import com.google.cloud.storage.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import static java.lang.String.format;

@Component
/*Each provider (GCP, AWS) implements the StorageServicePort interface.*/
public class GoogleStorageAdapter implements StorageServicePort {
    private final Storage storage;

    public GoogleStorageAdapter(Storage storage) {
        this.storage = storage;
    }

    public List<String> listBuckets() {
        return StreamSupport.stream(storage.list().iterateAll().spliterator(), false)
                .map(Bucket::getName)
                .collect(Collectors.toList());
    }

    @Override
    public byte[] downloadFile(StorageFile file) {
        Blob blob = storage.get(BlobId.of(file.getBucketName(), file.getFile().getOriginalFilename()));
        if(blob == null){
            throw new NotFoundException(format("File %s not found!", file.getFileName()));
        }
        return blob.getContent();
    }
    @Override
    public void uploadFile(StorageFile file) throws IOException {
        String fileName = file.getFile().getOriginalFilename();
        BlobId blobId = BlobId.of(file.getBucketName(), fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        File temp = File.createTempFile("upload-", file.getFile().getOriginalFilename());
        file.getFile().transferTo(temp);
        Path path = temp.toPath();
        storage.createFrom(blobInfo, path);
        temp.delete();
    }

}