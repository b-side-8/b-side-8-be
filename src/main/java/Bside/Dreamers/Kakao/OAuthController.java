package Bside.Dreamers.Kakao;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@AllArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {
    @Resource
    OAuthService oAuthService;

    /**
     * 카카오 callback
     * [GET] /oauth/kakao/callback
     */
    @ResponseBody
    @GetMapping("/kakao")
    public void kakaoCallback(@RequestParam String code) throws Exception{
        System.out.println(code);
        oAuthService.getKakaoAccessToken(code);

    }
}