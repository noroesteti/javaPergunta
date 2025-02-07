package com.example.javaPergunta.infra.local;

import com.example.javaPergunta.domain.port.output.StorageServicePort;
import com.example.javaPergunta.rest.endpoints.resources.StorageFile;
import com.google.j2objc.annotations.ObjectiveCName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Component
@Primary
public class LocalStorageAdapter implements StorageServicePort {
    @Value("${storage.local.path}") // Default directory if not specified
    private String storagePath;
    @Override
    public List<String> listBuckets() {
        File directory = new File(storagePath);

        if (!directory.exists() || !directory.isDirectory()) {
            return List.of();
        }

        return Arrays.stream(directory.listFiles())
                .filter(File::isDirectory)  // Fix: Using lambda correctly
                .map(File::getName)
                .collect(Collectors.toList());
    }

    @Override
    public byte[] downloadFile(StorageFile file){
        Path path = Paths.get(storagePath, file.getBucketName(), file.getFileName());
        try{
            return Files.readAllBytes(path);
        } catch (IOException e){
            throw new RuntimeException("Error reading file:" + path, e);
        }
    }

    @Override
    public void uploadFile(StorageFile file) throws IOException {
        Path dirPath = Paths.get(storagePath, file.getBucketName());
        Files.createDirectories(dirPath);
        Path filePath = dirPath.resolve(file.getFile().getOriginalFilename());
        Files.copy(file.getFile().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }
}
