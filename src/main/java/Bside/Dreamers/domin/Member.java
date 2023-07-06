package Bside.Dreamers.domin;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_no")
    private Long no;

    private Long id; // 아이디 칼럼 없어서 추가

    private String nickname;

    private LocalDateTime regist_dt;

    @Column(columnDefinition ="char")
    private String agree_yn;


    private String gender;

    private String birth;

    @OneToMany(mappedBy = "member")
    private List<Bucket> buckets = new ArrayList<>();



}
