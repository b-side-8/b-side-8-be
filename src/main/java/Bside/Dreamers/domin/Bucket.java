package Bside.Dreamers.domin;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bucket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bucketNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member; //버킷리스트 생성회원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no")
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;
    
    private String title;
    
    private String detail;

    @Column
    @UpdateTimestamp
    @ApiModelProperty(example = "등록일시")
    private LocalDateTime registDt;

    @Column(columnDefinition ="char")
    private String achvYn;

    @Temporal(TemporalType.DATE)
    private Date endDt;

    @Column(columnDefinition ="char")
    private String delYn;




    /** 연관관계 메서드*/
    public void setMember(Member member) {
        this.member = member;
        member.getBuckets().add(this);
    }


    public void setCategory(Category category) {
        this.category = category;
        category.getBuckets().add(this);
    }

    public void setFile(File file) {
        this.file = file;
        file.setBucket(this);
    }


    /** 버킷리스트 생성*/
    public static Bucket createBucket(Member member, Category category,
                                    File file, String title, String detail, String achv_yn,Date end_dt , String del_yn) {
        Bucket bucket = new Bucket();
        bucket.setMember(member);
        bucket.setCategory(category);
        bucket.setFile(file);

        bucket.setTitle(title);
        bucket.setDetail(detail);
        bucket.setRegistDt(LocalDateTime.now());
        bucket.setAchvYn(achv_yn);
        bucket.setEndDt(end_dt);
        bucket.setDelYn(del_yn);

        return bucket;
    }



    @Builder
    public Bucket(Member member, Category category, File file, String title, String detail, Date endDt){
        this.member = member;
        this.category = category;
        this.file = file;
        this.title = title;
        this.detail = detail;
        this.endDt = endDt;

    }

}
