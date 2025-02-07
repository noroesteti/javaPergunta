package com.example.javaPergunta.infra.config;

import com.example.javaPergunta.domain.port.output.StorageServicePort;
import com.example.javaPergunta.infra.gcp.GoogleStorageAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class StorageConfig {

    @Bean
    @Profile("gcp")
    public StorageServicePort gcpStorage(GoogleStorageAdapter googleStorageAdapter) {
        return googleStorageAdapter;
    }

//    @Bean
//    @Profile("aws")
//    public StorageServicePort awsStorage(S3StorageAdapter s3StorageAdapter) {
//        return s3StorageAdapter;
//    }
}