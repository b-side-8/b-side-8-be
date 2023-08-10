package Bside.Dreamers.service;
import Bside.Dreamers.domin.Member;
import Bside.Dreamers.domin.dto.FileSignupDTO;
import Bside.Dreamers.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
/*
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
*/

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UploadService {

    private final FileRepository fileRepository;

    // 업로드할 디렉토리 경로 (이동할 경로)
    private static final String UPLOAD_DIR = "C:\\upload\\";

    
    /**
     * 파일 업로드를 위해 파일 이동
     * */
    public String moveFile(MultipartFile multipartFile) {
        try {

            // 업로드할 디렉토리 경로 (이동할 경로)
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 업로드된 파일의 바이트 데이터를 읽어와 지정된 경로로 이동시킵니다.
            String fileName = multipartFile.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.write(filePath, multipartFile.getBytes());

            return "File uploaded successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "File upload failed!";
        }
    }


    /**
     * 회원 사진 업로드
     * */
    public void insertFile(String filePath, String serverPath, Long fileSize) {

        String originalFileName =  serverPath.substring(serverPath.lastIndexOf("/")+1);    //오리지날 파일명
        String extension = filePath.substring(filePath.lastIndexOf(".")+1);    //파일 확장자
        String savedFileName = originalFileName;  // UUID.randomUUID() + extension;    //  저장될 파일 명



        FileSignupDTO fileDTO = FileSignupDTO.builder()
                .file_stre_cours(serverPath)
                .stre_file_name(originalFileName)
                .originial_file_nm(originalFileName)
                .file_extsn(extension)
                .file_size(fileSize)
                .del_yn("N")
                .build();


        //파일 insert
        Bside.Dreamers.domin.File file2 = fileDTO.toEntity() ;
        String resultPath = insertFile(file2);
        log.info("fileId={}", resultPath);

    }

    /** 파일 저장 db */
    @Transactional
    public String insertFile(Bside.Dreamers.domin.File file) {
        return fileRepository.save(file).getFile_stre_cours();
    }

}
