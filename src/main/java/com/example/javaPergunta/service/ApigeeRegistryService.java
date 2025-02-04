//package com.example.javaPergunta.service;
//
//import com.google.cloud.apigeeregistry.v1.RegistryClient;
//import com.google.cloud.apigeeregistry.v1.Api;
//import com.google.cloud.apigeeregistry.v1.LocationName;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//public class ApigeeRegistryService {
//
//    @Autowired
//    private RegistryClient registryClient;
//
//    public void listApis(String projectId, String location) throws IOException {
//        LocationName parent = LocationName.of(projectId, location);
//
//        // List APIs in the specified location
//        for (Api api : registryClient.listApis(parent).iterateAll()) {
//            System.out.println("API Name: " + api.getName());
//            System.out.println("Display Name: " + api.getDisplayName());
//            System.out.println("Description: " + api.getDescription());
//            System.out.println("-----------------------------");
//        }
//    }
//}