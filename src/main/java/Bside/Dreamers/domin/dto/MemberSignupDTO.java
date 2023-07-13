package Bside.Dreamers.domin.dto;

import Bside.Dreamers.domin.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignupDTO {

    private Long id;
    private String nickName;
    private String agreeYn;
    private String gender;
    private String birth;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .nickName(nickName)
                .agreeYn(agreeYn)
                .gender(gender)
                .birth(birth)
                .build();

    }

}
