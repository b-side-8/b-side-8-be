package Bside.Dreamers.Kakao;

import Bside.Dreamers.service.KaKaoUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
    public void kakaoCallback(@RequestParam String code, HttpServletResponse response) throws Exception{
        System.out.println(code);
        String accessToken = oAuthService.getKakaoAccessToken(code);
        HashMap<String, Object> member = kaKaoUser.createKakaoUser(accessToken);

        System.out.println(member.get("nickname"));

        String id = String.valueOf(member.get("id"));


        String redirect_uri="http://localhost:3000/login/nickname?id="+id;
        response.sendRedirect(redirect_uri);
    }
}