package youngpeople.aliali.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import youngpeople.aliali.dto.BasicResDto;
import youngpeople.aliali.dto.MemberReqDto;
import youngpeople.aliali.dto.MemberResDto;
import youngpeople.aliali.dto.SchoolAuthReqDto;
import youngpeople.aliali.service.MemberService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/my")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public MemberResDto getMy(HttpServletRequest request) {
        String kakaoId = getKakaoId(request);

        return memberService.findByKakaoId(kakaoId);
    }

    @PutMapping
    public BasicResDto updateMy(HttpServletRequest request,
                                 @RequestBody MemberReqDto memberReqDto) {
        String kakaoId = getKakaoId(request);

        return memberService.updateMy(kakaoId, memberReqDto);
    }

    @DeleteMapping
    public BasicResDto deleteMy(HttpServletRequest request) {
        String kakaoId = getKakaoId(request);
        memberService.unregister(kakaoId);

        return BasicResDto.builder()
                .message("successful")
                .build();
    }

    /**
     * ★★★ 각 학교의 이름 - 이메일 자료롸 매칭 여부를 판단하는 메서드 필요
     */
    @PostMapping("/auth/school-request")
    public BasicResDto authenticationSchool(HttpServletRequest request,
                                            @RequestBody SchoolAuthReqDto schoolAuthReqDto) {
        String kakaoId = getKakaoId(request);

        return memberService.registerMySchool(kakaoId, schoolAuthReqDto);
    }

    @GetMapping("/auth/school-certification")
    public BasicResDto schoolAuth(HttpServletRequest request,
                                  @RequestParam(name = "authToken") String authToken) {
        String kakaoId = getKakaoId(request);

        return memberService.authenticateSchool(kakaoId, authToken);
    }

    private String getKakaoId(HttpServletRequest request) {
        return (String) request.getAttribute("kakaoId");
    }



}