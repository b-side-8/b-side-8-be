package Bside.Dreamers.domin;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Bucket {


    @Id
    @GeneratedValue
    @Column(name = "bucket_no")
    private Long no;

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
    
    private LocalDateTime regist_dt;

    @Column(columnDefinition ="char")
    private String achv_yn;

    @Temporal(TemporalType.DATE)
    private Date end_dt;

    @Column(columnDefinition ="char")
    private String del_yn;




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
        bucket.setRegist_dt(LocalDateTime.now());
        bucket.setAchv_yn(achv_yn);
        bucket.setEnd_dt(end_dt);
        bucket.setDel_yn(del_yn);

        return bucket;
    }



//완료

}
