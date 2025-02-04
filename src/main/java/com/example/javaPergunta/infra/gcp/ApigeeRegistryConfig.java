//package com.example.javaPergunta.infra.gcp;
//
//import com.example.javaPergunta.domain.exceptions.FileNotFoundException;
//import com.example.javaPergunta.domain.exceptions.IllegalStateException;
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.apigeeregistry.v1.RegistryClient;
//import com.google.cloud.apigeeregistry.v1.RegistrySettings;
//import com.google.cloud.storage.StorageOptions;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//@Configuration
//public class ApigeeRegistryConfig {
//
////    @Bean
////    public RegistryClient registryClient() throws IOException {
////        // Load credentials from the JSON file
////        GoogleCredentials credentials = GoogleCredentials.fromStream(
////                new FileInputStream("src/main/resources/gcp/client_secret_google.json")
////        );
////
////        // Configure RegistrySettings with the credentials
////        RegistrySettings registrySettings = RegistrySettings.newBuilder()
////                .setCredentialsProvider(() -> credentials)
////                .build();
////
////        // Create and return the RegistryClient
////        return RegistryClient.create(registrySettings);
////    }
//    @Bean
//    public RegistryClient registryClient() throws IOException {
//        // Load credentials from the JSON file
//
//        String credentialPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
//
//        if (credentialPath == null || credentialPath.isEmpty()) {
//            throw new IllegalStateException("GOOGLE_APPLICATION_CREDENTIALS environment variable is not set.");
//        }
//        try {
//            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialPath));
//
//            // Configure RegistrySettings with the credentials
//            RegistrySettings registrySettings = RegistrySettings.newBuilder()
//                    .setCredentialsProvider(() -> credentials)
//                    .build();
//
//            return RegistryClient.create(registrySettings);
//
//        } catch (com.example.javaPergunta.domain.exceptions.IOException | FileNotFoundException |
//                 java.io.FileNotFoundException e) {
//            throw new com.example.javaPergunta.domain.exceptions.IOException("Can't read file!");
//        } catch (java.io.IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}