//package com.example.javaPergunta.service;
//
//import org.springframework.stereotype.Service;
//import java.io.IOException;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.auth.oauth2.AccessToken;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import java.io.FileInputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//import java.util.Scanner;
//@Service
//public class ApiRegistryService {
//    private final String GOOGLE_CLOUD_PROJECT_ID = "organic-edge-408018";
//    private final String GOOGLE_APPLICATION_CREDENTIALS = "C:\\client_secret_googleusercontent.json";
//
//    public String listApis() throws IOException {
//        // Get Access Token from Service Account
//        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(GOOGLE_APPLICATION_CREDENTIALS))
//                .createScoped("https://www.googleapis.com/auth/cloud-platform");
//        credentials.refresh();
//        AccessToken token = credentials.getAccessToken();
//
//        // Call API Registry REST endpoint
//        String urlString = String.format(
//                "https://apigeeregistry.googleapis.com/v1/projects/%s/locations/global/apis",
//                GOOGLE_CLOUD_PROJECT_ID
//        );
//        URL url = new URL(urlString);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Authorization", "Bearer " + token.getTokenValue());
//
//        // Read response
//        Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8);
//        String response = scanner.useDelimiter("\\A").next();
//        scanner.close();
//
//        // Parse JSON response
//        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
//        return jsonResponse.toString();
//    }
//}