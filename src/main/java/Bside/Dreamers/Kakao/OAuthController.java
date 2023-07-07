package Bside.Dreamers.Kakao;

import Bside.Dreamers.domin.Member;
import Bside.Dreamers.service.KaKaoUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@AllArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {
    @Resource
    OAuthService oAuthService;

    @Resource
    KaKaoUser kaKaoUser;

    /**
     * 카카오 callback
     * [GET] /oauth/kakao/callback
     */
    @ResponseBody
    @GetMapping("/kakao")
    public void kakaoCallback(@RequestParam String code) throws Exception{
        System.out.println(code);
        String accessToken = oAuthService.getKakaoAccessToken(code);
        Member member = kaKaoUser.createKakaoUser(accessToken);

        System.out.println(member.getNickname());
        //회원가입

    }
}