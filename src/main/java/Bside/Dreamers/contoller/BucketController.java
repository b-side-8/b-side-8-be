package Bside.Dreamers.contoller;

import Bside.Dreamers.domin.dto.BucketRegistDTO;
import Bside.Dreamers.service.BucketService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bucket")
public class BucketController {

    private final BucketService bucketService;

    @Autowired
    public BucketController(BucketService bucketService){
        this.bucketService = bucketService;
    }

    @ApiOperation(value = "버킷 등록")
    @PostMapping("/registBucket")
    public ResponseEntity<BucketRegistDTO> registBucket(@RequestBody BucketRegistDTO bucketRegistDTO) throws Exception{
        bucketService.registBucket(bucketRegistDTO);
        return new ResponseEntity<>(bucketRegistDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "회원별 버킷 목록")
    @GetMapping("/bucketList")
    public ResponseEntity<List> bucketList(@RequestParam("memberNo") Long memberNo) throws Exception{
        List bucketList = bucketService.findBucketByMemberNo(memberNo);
        return ResponseEntity.ok(bucketList);
    }
}
