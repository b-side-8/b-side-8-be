package Bside.Dreamers.domin;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class File {

    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long id;

    private String file_stre_cours;

    private String stre_file_name;

    private String originial_file_nm;

    private String file_extsn;

    private long file_size;

    @Column(columnDefinition ="char")
    private String del_yn;

    private LocalDateTime regist_dt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bucket_no")
    private Bucket bucket;


}
