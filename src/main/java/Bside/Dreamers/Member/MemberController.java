package Bside.Dreamers.Member;

import Bside.Dreamers.domin.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/")
    public void registMember(@RequestParam String nickName) throws Exception{


    }

}
