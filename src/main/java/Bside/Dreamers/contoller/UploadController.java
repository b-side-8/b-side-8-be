package Bside.Dreamers.contoller;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Bside.Dreamers.service.UploadService;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Log4j
@RestController
public class UploadController {
    final String endPoint = "https://kr.object.ncloudstorage.com";
    final String regionName = "kr-standard";
    final String accessKey = "yE1WLl95u6mAmlYNECwk";
    final String secretKey = "in2mVPZaeXuxtn3fPQVSOD9iQtwoFOjCMnBtYWie";

    @Resource(name = "uploadService")
    private UploadService uploadService;


    @GetMapping("/listselect")
    public void uploadFile() throws IOException {


        // S3 client
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();


        List<Bucket> buckets = s3.listBuckets();
    }



    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        //파일이동
       uploadService.uploadFile(multipartFile);

        // S3 client
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

        String bucketName = "bucket-storage";

        // 서버로 파일 업로드
        String objectName = multipartFile.getOriginalFilename();
        String filePath = "C:\\upload\\"+multipartFile.getOriginalFilename();

        try {
            s3.putObject(bucketName, objectName, new File(filePath));
            System.out.format("Object %s has been created.\n", objectName);
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch(SdkClientException e) {
            e.printStackTrace();
        }



        s3.setObjectAcl(bucketName,objectName,CannedAccessControlList.PublicRead);

        //이미지 링크 전달
        return String.valueOf(s3.getUrl(bucketName,multipartFile.getOriginalFilename()));
    }







}

