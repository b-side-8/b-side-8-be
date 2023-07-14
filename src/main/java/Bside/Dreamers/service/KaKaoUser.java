package Bside.Dreamers.service;

import Bside.Dreamers.domin.Member;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
public class KaKaoUser {

    public HashMap<String, Object> createKakaoUser(String token) throws Exception {

        String reqURL = "https://kapi.kakao.com/v2/user/me";

        HashMap<String, Object> memberInfo = new HashMap<String, Object>();

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            Long id = element.getAsJsonObject().get("id").getAsLong();
            System.out.println("id : " + id);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickName = properties.getAsJsonObject().get("nickname").getAsString();


            boolean hasEmail = kakao_account.get("has_email").getAsBoolean();
            if(hasEmail){
                String email = kakao_account.getAsJsonObject().get("email").getAsString();
                memberInfo.put("email", email);
            }

            memberInfo.put("id", id);
            memberInfo.put("nickname", nickName);

            br.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return memberInfo;
    }


}
