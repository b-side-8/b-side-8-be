package Bside.Dreamers.Member;

import Bside.Dreamers.domin.Member;
import Bside.Dreamers.domin.dto.MemberSignupDTO;
import Bside.Dreamers.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Member> joinMember(@RequestBody MemberSignupDTO memberSignupDTO) throws Exception{
        Member member = new Member();
        try{
            member = memberService.join(memberSignupDTO);
            System.out.println("no = "+ member.getMemberNo());

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @ApiOperation(value = "회원 정보 조회")
    @GetMapping("/getMemberInfo")
    public ResponseEntity<Member> getMemberInfo(@RequestParam("id") Long id){
        Member member = memberService.getMemberInfo(id);
        return ResponseEntity.ok(member);
    }
}
