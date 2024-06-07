package youngpeople.aliali.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import youngpeople.aliali.dto.MemberReqDto;
import youngpeople.aliali.dto.MemberResDto;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired private MemberService memberService;
    @Autowired private TokenService tokenService;

    private String EXISTING_NICKNAME = "원순재";
    private String EXISTING_KAKAO_ID = "3456718320";

    @Test
    void 회원_저장_및_조회() {
        String NEW_KAKAO_ID = "0000000099";

        memberService.register(NEW_KAKAO_ID);
        MemberResDto memberResDto = memberService.findByKakaoId(NEW_KAKAO_ID);

        assertThat(memberResDto.getNickname()).isEqualTo("user100");
    }

    @Test
    void 기존_회원_조회() {
        MemberResDto memberResDto = memberService.findByKakaoId(EXISTING_KAKAO_ID);

        String nickname = memberResDto.getNickname();

        assertThat(nickname).isEqualTo(EXISTING_NICKNAME);
    }

    @Test
    void 회원_수정() {
        MemberReqDto memberReqDto = new MemberReqDto("김순재", 1);
        memberService.updateMy(EXISTING_KAKAO_ID, memberReqDto);

        MemberResDto memberResDto = memberService.findByKakaoId(EXISTING_KAKAO_ID);

        assertThat(memberResDto.getNickname()).isEqualTo("김순재");
    }

    @Test
    void 회원_로그인() {
        String token = memberService.login(EXISTING_KAKAO_ID);

        String accessToken = tokenService.getAccessToken(token);
        String refreshToken = tokenService.getRefreshToken(token);
        log.info("token = {}", token);
        log.info("accessToken = {}", accessToken);
        log.info("refreshToken = {}", refreshToken);

        assertThat(token).isEqualTo(accessToken + "/////" + refreshToken);
    }

    /**
     * 미작성
     */
    @Test
    void 회원_탈퇴() {
    }

    /**
     * 학교 인증은 TokenServiceTest에서 진행
     */


}