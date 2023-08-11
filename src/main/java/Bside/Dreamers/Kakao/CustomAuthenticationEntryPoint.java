package Bside.Dreamers.Kakao;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /*정상적인 JWT가 오지 않은 경우에 대해 필터링하는 클래스입니다.
    비정상적인 JWT를 가지고 접근 시 401 UNAUTHORIZED 응답을 하도록 했습니다.*/

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED); //401
    }

}
