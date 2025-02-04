//package com.example.javaPergunta.rest.endpoints;
//
//import com.example.javaPergunta.rest.endpoints.openApi.ApigeeRegistryControllerOpenAPI;
//import com.example.javaPergunta.service.ApigeeRegistryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@RestController
//public class ApigeeRegistryController implements ApigeeRegistryControllerOpenAPI {
//
//    @Autowired
//    private ApigeeRegistryService apigeeRegistryService;
//
//    @GetMapping("/list-apis")
//    public String listApis(@RequestParam String projectId, @RequestParam String location) {
//        try {
//            apigeeRegistryService.listApis(projectId, location);
//            return "APIs listed successfully!";
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "Failed to list APIs: " + e.getMessage();
//        }
//    }
//}