package youngpeople.aliali.controller.test;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import youngpeople.aliali.controller.Message;
import youngpeople.aliali.service.TokenService;

@Slf4j
@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;


    @GetMapping("/get")
    public Message getCookie(HttpServletRequest request, HttpServletResponse response) {

        String accessToken = request.getHeader("authorization");
        log.info("========= header =========");
        log.info("accessToken = {}", accessToken);

        return new Message(
                "ok",
                accessToken);
    }

    @GetMapping("/get/token")
    public Message getToken(HttpServletRequest request, HttpServletResponse response) {
        log.info("========-=-=-=-=-=========");

        String kakaoId = "3456718320";
        String token = tokenService.createToken(kakaoId);
        String accessToken = tokenService.getAccessToken(token);

        response.addHeader("authorization", accessToken);
        log.info("accessToken : {}", tokenService.getAccessToken(token));

        return new Message(
                "ok",
                accessToken
        );
    }

    @GetMapping("/get/kakaoId")
    public String getKakaoId(HttpServletRequest request, HttpServletResponse response) {

        String accessToken = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("access_token")) {
                    accessToken = cookie.getValue();
                }
            }
        }

        tokenService.verifyToken(accessToken);
        return tokenService.getKakaoId(accessToken);
    }

}
