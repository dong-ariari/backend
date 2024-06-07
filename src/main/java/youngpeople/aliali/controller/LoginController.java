package youngpeople.aliali.controller;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import youngpeople.aliali.service.KakaoService;
import youngpeople.aliali.service.MemberService;
import youngpeople.aliali.service.TokenService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final KakaoService kakaoService;
    private final MemberService memberService;
    private final TokenService tokenService;

    /**
     * 1. get authorization code → ok (login project)
     * 2. post authorization code + get token → ok (aliali project)
     * 3. post token + get member information → ok (aliali project)
     */
    @GetMapping("/login")
    public LoginResDto login(@RequestParam(name = "code") String code) throws Exception {
        String kakaoId = kakaoService.getKakaoInfo(code);

        String token = memberService.login(kakaoId);
        String accessToken = tokenService.getAccessToken(token);
        String refreshToken = tokenService.getRefreshToken(token);

        return LoginResDto.builder()
                .message("successful")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @PostMapping("/reissue")
    public ReissueResDto reissue(@RequestBody ReissueReqDto reissueReqDto) {
        String refreshToken = reissueReqDto.getRefreshToken();

        tokenService.verifyToken(refreshToken);

        String kakaoId = tokenService.getKakaoId(refreshToken);
        String token = tokenService.createToken(kakaoId);
        String accessToken = tokenService.getAccessToken(token);

        return ReissueResDto.builder()
                .message("successful")
                .accessToken(accessToken)
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResDto {
        private String message;
        private String accessToken;
        private String refreshToken;
    }



    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReissueReqDto {
        private String refreshToken;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReissueResDto {
        private String message;
        private String accessToken;
    }

}
