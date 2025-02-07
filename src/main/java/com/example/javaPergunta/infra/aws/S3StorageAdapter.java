//package com.example.javaPergunta.infra.aws;
//
//
//import com.example.javaPergunta.domain.port.output.StorageServicePort;
//import org.springframework.stereotype.Component;
//
//import com.example.javaPergunta.domain.ports.StorageServicePort;
//import com.example.javaPergunta.rest.endpoints.resources.StorageFile;
//import org.springframework.stereotype.Component;
//import software.amazon.awssdk.core.sync.RequestBody;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.*;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//@Component
//public class S3StorageAdapter implements StorageServicePort {
//    private final S3Client s3Client;
//
//    public S3StorageAdapter(S3Client s3Client) {
//        this.s3Client = s3Client;
//    }
//
//    @Override
//    public List<String> listBuckets() {
//        return s3Client.listBuckets().buckets().stream()
//                .map(Bucket::name)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public byte[] downloadFile(StorageFile file) {
//        GetObjectRequest request = GetObjectRequest.builder()
//                .bucket(file.getBucketName())
//                .key(file.getFileName())
//                .build();
//
//        return s3Client.getObjectAsBytes(request).asByteArray();
//    }
//
//    @Override
//    public void uploadFile(StorageFile file) throws IOException {
//        PutObjectRequest request = PutObjectRequest.builder()
//                .bucket(file.getBucketName())
//                .key(file.getFile().getOriginalFilename())
//                .build();
//
//        s3Client.putObject(request, RequestBody.fromBytes(file.getFile().getBytes()));
//    }
//}