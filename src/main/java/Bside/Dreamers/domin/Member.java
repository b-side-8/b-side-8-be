package Bside.Dreamers.domin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long no;

    @Column
    @ApiModelProperty(example = "카카오 ID")
    private Long id;

    @Column
    @ApiModelProperty(example = "닉네임")
    private String nickname;

    @Column
    @UpdateTimestamp
    @ApiModelProperty(example = "가입일시")
    private LocalDateTime registDt;

    @Column(columnDefinition ="char")
    @ApiModelProperty(example = "이용약관동의여부(Y/N)")
    private String agreeYn;

    @Column
    @ApiModelProperty(example = "성별")
    private String gender;

    @Column
    @ApiModelProperty(example = "출생연도")
    private String birth;

    @OneToMany(mappedBy = "member")
    private List<Bucket> buckets = new ArrayList<>();

    @Builder
    public Member(Long id, String nickname, String agreeYn, String gender, String birth){
        this.id = id;
        this.nickname = nickname;
        this.agreeYn = agreeYn;
        this.gender = gender;
        this.birth = birth;
    }

}
