package com.example.javaPergunta.infra.gcp;

import com.example.javaPergunta.domain.exceptions.IOException;
import com.google.cloud.storage.Storage;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.StorageOptions;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.javaPergunta.domain.exceptions.IllegalStateException;
import com.example.javaPergunta.domain.exceptions.FileNotFoundException;
import java.io.FileInputStream;

@Configuration
public class GoogleCloudConfig {
    //System variable:
    private static GoogleCloudConfig instance;
    private final String projectId;

    public String getProjectId() {
        return this.projectId ;
    }

    public GoogleCloudConfig(){
        String credentialPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
        if (credentialPath == null || credentialPath.isEmpty()) {
            throw new IllegalStateException("GOOGLE_APPLICATION_CREDENTIALS environment variable is not set.");
        }
        // Read JSON credentials
        try {
            FileInputStream serviceAccountStream = new FileInputStream(credentialPath);
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream);

            // Parse project_id from JSON
            JsonObject jsonObject = JsonParser.parseReader(new java.io.FileReader(credentialPath)).getAsJsonObject();
            this.projectId = jsonObject.get("project_id").getAsString();

        }catch(FileNotFoundException | java.io.FileNotFoundException e){
            throw new FileNotFoundException("Can't read file!");
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized GoogleCloudConfig getInstance(){
        if(instance == null){
            instance = new GoogleCloudConfig();
        }
        return instance;
    }

    @Bean
    public Storage googleStorage() {
        String credentialPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");


        try {
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialPath));
//            if (credentials instanceof ServiceAccountCredentials) {
//                this.projectId = ((ServiceAccountCredentials) credentials).getProjectId();
//            }
            return  StorageOptions.newBuilder()
                    .setCredentials(credentials)
                    .build()
                    .getService();


        }catch (IOException | FileNotFoundException e){
            throw new IOException("Can't read file!");
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }



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

}
