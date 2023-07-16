package Bside.Dreamers.Kakao;

import Bside.Dreamers.domin.Member;
import Bside.Dreamers.service.KaKaoUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

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
    public String kakaoCallback(@RequestParam String code) throws Exception{
        System.out.println(code);
        String accessToken = oAuthService.getKakaoAccessToken(code);
        HashMap<String, Object> member = kaKaoUser.createKakaoUser(accessToken);

        System.out.println(member.get("nickname"));

        return (String)member.get("id");
    }
}