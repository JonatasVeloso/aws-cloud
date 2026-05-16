package br.com.aws.cloud.s3;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.List;

@Service
public class S3Service {

    private final S3Client s3Client;

// Mudar para o nome do seu bucket
    private static final String BUCKET =
            "my-bucket-882557516561-sa-east-1-an";

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void upload(MultipartFile file) throws IOException {

// 

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key("pasta_teste/" + file.getOriginalFilename())
                .build();

        s3Client.putObject(
                request,
                software.amazon.awssdk.core.sync.RequestBody
                        .fromBytes(file.getBytes())
        );
    }

    public List<String> listFiles() {

        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(BUCKET)
                .prefix("Java Class/")
                .build();

        ListObjectsV2Response response =
                s3Client.listObjectsV2(request);

        return response.contents()
                .stream()
                .map(S3Object::key)
                .toList();
    }
}