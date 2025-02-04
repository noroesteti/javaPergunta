package com.example.javaPergunta.infra.gcp;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.javaPergunta.domain.exceptions.IllegalStateException;
import com.example.javaPergunta.domain.exceptions.IOException;
import com.example.javaPergunta.domain.exceptions.FileNotFoundException;
import java.io.FileInputStream;

@Configuration
public class GoogleCloudConfig {
    //File in C:\:
//    private static final String CREDENTIALS_PATH = "C:\\client_secret_google.json";
//
//    @Bean
//    public Storage googleStorage() throws IOException {
//        GoogleCredentials credentials;
//
//        try (FileInputStream serviceAccountStream = new FileInputStream(CREDENTIALS_PATH)) {
//            credentials = GoogleCredentials.fromStream(serviceAccountStream);
//        }
//
//        return StorageOptions.newBuilder()
//                .setCredentials(credentials)
//                .build()
//                .getService();
//    }

    //System variable:
    @Bean
    public Storage googleStorage() {
        String credentialPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");

        if (credentialPath == null || credentialPath.isEmpty()) {
            throw new IllegalStateException("GOOGLE_APPLICATION_CREDENTIALS environment variable is not set.");
        }
        try {

            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialPath));
            return StorageOptions.newBuilder()
                    .setCredentials(credentials)
                    .build()
                    .getService();

        }catch (IOException | FileNotFoundException | java.io.FileNotFoundException e){
            throw new IOException("Can't read file!");
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }

    }

}
