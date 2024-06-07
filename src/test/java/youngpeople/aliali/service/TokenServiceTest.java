package youngpeople.aliali.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import youngpeople.aliali.exception.TokenException;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
class TokenServiceTest {

    @Autowired TokenService tokenService;

    private String EXISTING_KAKAO_ID = "3456718320";
    private Long EXISTING_SCHOOL_ID = 1L;

    @Test
    void 토큰_생성_및_검증() {
        String kakaoId = "123456";
        String token = tokenService.createToken(kakaoId);

        String accessToken = tokenService.getAccessToken(token);

        tokenService.verifyToken(accessToken);

    }

    @Test
    void 토큰에서_카카오아이디_조회() {
        String kakaoId = "19961119";

        String token = tokenService.createToken(kakaoId);
        String accessToken = tokenService.getAccessToken(token);
        String findKakaoId = tokenService.getKakaoId(accessToken);

        log.info("kakaoId = {}", kakaoId);
        assertThat(findKakaoId).isEqualTo(kakaoId);
    }


}