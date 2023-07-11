package Bside.Dreamers.Member;

import Bside.Dreamers.domin.Member;
import Bside.Dreamers.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name="nickName", value = "닉네임", required = true, paramType = "query"),
            @ApiImplicitParam(name="agreeYn", value = "약관동의여부(y/n)", required = true, paramType = "query"),
            @ApiImplicitParam(name="gender", value = "성별", required = false, paramType = "query"),
            @ApiImplicitParam(name="birth", value = "출생연도", required = false, paramType = "query"),
    })
    @PostMapping("/join")
    public void joinMember(@RequestBody Member member) throws Exception{
        Long id = memberService.join(member);
        System.out.println("id = "+ id);
    }

}
