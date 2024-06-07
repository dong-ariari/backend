package youngpeople.aliali.controller.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import youngpeople.aliali.controller.Message;
import youngpeople.aliali.controller.dtotest.MemberDtoTest;
import youngpeople.aliali.entity.member.Member;
import youngpeople.aliali.repository.MemberRepository;
import youngpeople.aliali.service.TokenService;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class HelloController {

    private final TokenService tokenService;
    private final MemberRepository memberRepository;

    @GetMapping("/")
    public LocalDateTime home() {
        LocalDateTime now = LocalDateTime.now();
        return now;
    }

    @GetMapping("/test/1")
    public Message test1() {
        String token = tokenService.createToken("234235254");
        String accessToken = tokenService.getAccessToken(token);
        String refreshToken = tokenService.getRefreshToken(token);

        return new Message(
                "good message",
                new MemberDtoTest("원순재", 2342365),
                accessToken,
                refreshToken
                );
    }

    @GetMapping("/test/2")
    public Message test2() {
        return new Message(
                "good message",
                null);
    }

    @PostMapping("/test/3")
    public Message test3(@RequestBody MemberDtoTest memberDtoTest) {
        return new Message(
                "good message",
                memberDtoTest
        );
    }

    @GetMapping("/test/4")
    public Message test4() {
        memberRepository.save(new Member("324234", "바보"));
        return new Message(
                "성공적"
        );
    }

    @GetMapping("/test/5")
    public void test5() {
        Member member = memberRepository.findByKakaoId("324234").get();
        member.setNickname("바바아아ㅏ앙보");
    }




}
