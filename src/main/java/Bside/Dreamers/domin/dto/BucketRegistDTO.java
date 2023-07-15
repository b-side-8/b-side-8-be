package Bside.Dreamers.domin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BucketRegistDTO {

    private Long bucketNo;
    private Long categoryNo;
    private Long memberNo;
    private String detail;
}
