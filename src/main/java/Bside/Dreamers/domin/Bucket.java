package Bside.Dreamers.domin;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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


    @OneToOne(fetch = FetchType.LAZY)
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

    private LocalDateTime end_dt;

    @Column(columnDefinition ="char")
    private String del_yn;




    /** 연관관계 메서드*/
    public void setMember(Member member) {
        this.member = member;
        member.getBuckets().add(this);
    }


    public void setCategory(Category category) {
        this.category = category;
        category.setBucket(this);
    }

    public void setFile(File file) {
        this.file = file;
        file.setBucket(this);
    }
//완료

}
