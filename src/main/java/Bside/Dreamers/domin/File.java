package Bside.Dreamers.domin;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class File {

    @Id
    @GeneratedValue
    @Comment("파일아이디")
    @Column(name = "file_id")
    private Long id;

    @Comment("파일 저장경로")
    private String file_stre_cours;

    @Comment("파일 저장명")
    private String stre_file_name;

    @Comment("원본 파일명")
    private String originial_file_nm;

    @Comment("파일 확장자")
    private String file_extsn;

    @Comment("파일 사이즈")
    private long file_size;

    @Comment("삭제여부")
    @Column(columnDefinition ="char")
    private String del_yn;

    @Comment("등록 일시")
    private LocalDateTime regist_dt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bucket_no")
    private Bucket bucket;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Bucket member_no;


    @Builder
    public File(Long id, String file_stre_cours, String originial_file_nm, String file_extsn, long file_size, String del_yn, LocalDateTime regist_dt) {
        this.id = id;
        this.file_stre_cours = file_stre_cours;
        this.originial_file_nm = originial_file_nm;
        this.file_extsn = file_extsn;
        this.file_size = file_size;
        this.del_yn = del_yn;
        this.regist_dt = regist_dt;
    }

}
