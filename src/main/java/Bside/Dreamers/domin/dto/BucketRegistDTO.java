package Bside.Dreamers.domin.dto;

import Bside.Dreamers.domin.Bucket;
import Bside.Dreamers.domin.Category;
import Bside.Dreamers.domin.File;
import Bside.Dreamers.domin.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BucketRegistDTO {

    private Long memberId;
    private Long categoryId;
    private Long fileid;
    private String title;
    private String detail;
    private Date endDt;

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
