package Bside.Dreamers.contoller;

import Bside.Dreamers.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bucket")
public class BucketController {

    private final BucketService bucketService;

    @Autowired
    public BucketController(BucketService bucketService){
        this.bucketService = bucketService;
    }


}
