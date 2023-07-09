package Bside.Dreamers.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import javax.transaction.Transactional;
import java.io.File;

@Service
public class UploadService {

    private final S3Client s3Client;
    private final String bucketName;

    public UploadService(@Value("${navercloud.accessKey}") String accessKey,
                         @Value("${navercloud.secretKey}") String secretKey,
                         @Value("${navercloud.bucketName}") String bucketName,
                         @Value("${navercloud.region}") String region) {
        this.bucketName = bucketName;
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(() -> AwsBasicCredentials.create(accessKey, secretKey))
                .build();
    }

    @Transactional
    public void uploadFile(File file) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(file.getName())
                .build();

        PutObjectResponse response = s3Client.putObject(request, file.toPath());
        System.out.println("Uploaded file: " + response.eTag());
    }
}
