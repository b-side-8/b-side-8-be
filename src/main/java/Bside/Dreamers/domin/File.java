package Bside.Dreamers.domin;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(
        name="FILE_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="FILE_SEQ", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
        )
public class File {

    @Id
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE, //사용할 전략을 시퀀스로  선택
            generator="FILE_SEQ_GEN" //식별자 생성기를 설정해놓은  USER_SEQ_GEN으로 설정
    )
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
    private Long file_size;

    @Comment("삭제여부")
    @Column(columnDefinition ="char")
    private String del_yn;

    @Comment("등록 일시")
    @UpdateTimestamp
    @CreatedDate
    private LocalDateTime regist_dt;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "file",cascade = CascadeType.ALL)
    private List<Bucket> buckets = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "file")
    private List<Member> members = new ArrayList<>();


    @Builder
    public File(Long id, String file_stre_cours,String stre_file_name, String originial_file_nm, String file_extsn, Long file_size, String del_yn) {
        this.id = id;
        this.file_stre_cours = file_stre_cours;
        this.stre_file_name = stre_file_name;
        this.originial_file_nm = originial_file_nm;
        this.file_extsn = file_extsn;
        this.file_size = file_size;
        this.del_yn = del_yn;

    }

}
