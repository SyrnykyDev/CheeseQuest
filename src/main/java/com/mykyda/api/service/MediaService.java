package com.mykyda.api.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final S3Client s3Client;

    @SneakyThrows
    public String upload(MultipartFile multipartFile) {
        String filename = multipartFile.getOriginalFilename();
        String url = "images/" + UUID.randomUUID().toString() + filename;
        PutObjectRequest request = PutObjectRequest.builder()
                .key(url)
                .bucket("userprofilepicturesbucket")
                .build();
        s3Client.putObject(request, RequestBody.fromBytes(multipartFile.getBytes()));
        return s3Client.utilities().getUrl(e -> e.bucket("userprofilepicturesbucket").key(url)).toString();
    }

}
