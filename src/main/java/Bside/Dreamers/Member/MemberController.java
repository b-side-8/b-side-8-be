package Bside.Dreamers.Member;

import Bside.Dreamers.domin.Member;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @ApiOperation(value = "회원 정보 저장")
    @ApiImplicitParams({
            @ApiImplicitParam(name="nickName", value = "닉네임", required = true, paramType = "query"),
            @ApiImplicitParam(name="agreeYn", value = "약관동의여부", required = true, paramType = "query"),
            @ApiImplicitParam(name="gender", value = "성별", required = false, paramType = "query"),
            @ApiImplicitParam(name="birth", value = "출생연도", required = false, paramType = "query"),
    })
    @PostMapping("/join")
    public void joinMember(@RequestBody Member member) throws Exception{

        System.out.println("!!");

    }

}
