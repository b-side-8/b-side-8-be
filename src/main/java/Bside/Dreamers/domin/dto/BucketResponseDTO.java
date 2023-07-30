package Bside.Dreamers.domin.dto;

import Bside.Dreamers.domin.Bucket;
import Bside.Dreamers.domin.Category;
import Bside.Dreamers.domin.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketResponseDTO {

    private Long bucketNo;
    private Long memberId;
    private Long categoryId;
    private Long fileId;
    private String title;
    private String detail;
    private Date endDt;
    private LocalDateTime registDt;
    private String achvYn;

    public Bucket toEntity(Member member, Category category){
        return Bucket.builder()
                .member(member)
                .category(category)
//                .file(file)
                .title(title)
                .detail(detail)
                .endDt(endDt)
                .build();
    }
}
