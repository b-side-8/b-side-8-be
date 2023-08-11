package Bside.Dreamers.Kakao;

import Bside.Dreamers.service.KaKaoUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity kakaoCallback(@RequestParam String code, HttpServletResponse response) throws Exception{
        System.out.println(code);
        TokenDto tokenDto = oAuthService.getKakaoAccessToken(code);
        HashMap<String, Object> member = kaKaoUser.createKakaoUser(tokenDto.getAccessToken());

        System.out.println(member.get("nickname"));

        HttpHeaders headers = new HttpHeaders();
        ResponseCookie cookie = ResponseCookie.from("RefreshToken", tokenDto.getRefreshToken())
                .path("/")
                .maxAge(60*60*24*7) // 쿠키 유효기간 7일로 설정
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        headers.add("Set-cookie", cookie.toString());
        headers.add("Authorization", tokenDto.getAccessToken());

        return ResponseEntity.ok().headers(headers).body("accessToken: " + tokenDto.getAccessToken());
    }
}