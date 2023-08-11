package Bside.Dreamers.Kakao;


import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /*인증, 인가 단계에서 발생한 에러를 처리하는 클래스입니다.
    권한이 부여되지 않은 상태에서 접근 시 403 FOBIDDEN 응답을 하도록 했습니다.*/
    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.sendError(HttpServletResponse.SC_FORBIDDEN); //403
    }

}