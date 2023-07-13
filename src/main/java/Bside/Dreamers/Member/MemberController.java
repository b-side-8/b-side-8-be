package Bside.Dreamers.Member;

import Bside.Dreamers.domin.dto.MemberSignupDTO;
import Bside.Dreamers.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ApiOperation(value = "회원 정보 저장")
    @PostMapping("/join")
    public void joinMember(@RequestBody MemberSignupDTO memberSignupDTO) throws Exception{
        Long no = memberService.join(memberSignupDTO.toEntity());
        System.out.println("no = "+ no);
    }

}
