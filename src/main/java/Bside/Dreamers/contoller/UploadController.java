package Bside.Dreamers.contoller;

import Bside.Dreamers.domin.Member;
import Bside.Dreamers.domin.dto.BucketResponseDTO;
import Bside.Dreamers.service.BucketService;
import Bside.Dreamers.service.MemberService;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import lombok.extern.log4j.Log4j;


import Bside.Dreamers.service.UploadService;
import com.amazonaws.SdkClientException;

import com.amazonaws.services.s3.AmazonS3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.net.URI;
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

    @Resource(name = "memberService")
    private MemberService memberService;

    @Resource(name = "bucketService")
    private BucketService bucketService;

    @GetMapping("/fileSelect")
    public String uploadFile(@RequestParam("id") Long id) throws IOException {

        // S3 client
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

        String bucketName = "bucket-storage";

        // one in the bucket
        URI s3Object = null;
        try {
            s3Object = s3.getObject(bucketName, String.valueOf(id)).getObjectContent().getHttpRequest().getURI();
            System.out.println("Object List : " + s3Object );
        } catch (AmazonS3Exception e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        return String.valueOf(s3Object);
    }



    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile,@RequestParam("id") Long id,@RequestParam("saveType") String saveType) throws IOException {

        String serverPath = "";
        Long fileSize = null;
        String fileNm="";
        if (saveType.equals("memberSave")) {
            fileNm = "M_" + id;
        } else if (saveType.equals("bucketSave")){
            List<BucketResponseDTO> buckList = bucketService.findBucketByMemberNo(id); //여기서부터 버킷NO 칼컴의 데이터를 조회해야되는데 SIZE만 조회됨,,,!
            int buckListSize = buckList.size();
            fileNm = "B_" + id + "_" + buckListSize  ;
        }


        //파일이동
       uploadService.moveFile(multipartFile);

        // S3 client
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

        String bucketName = "bucket-storage";

        // 서버로 파일 업로드
        String objectName = fileNm;
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

        serverPath=String.valueOf(s3.getUrl(bucketName,objectName));
        fileSize= (multipartFile.getSize()) / 1024;

        uploadService.insertFile(filePath, serverPath, fileSize);


        //이미지 링크 전달
        return serverPath;
    }







}

