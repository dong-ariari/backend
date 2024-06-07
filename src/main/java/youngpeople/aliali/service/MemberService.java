package youngpeople.aliali.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youngpeople.aliali.dto.BasicResDto;
import youngpeople.aliali.dto.MemberReqDto;
import youngpeople.aliali.dto.MemberResDto;
import youngpeople.aliali.dto.SchoolAuthReqDto;
import youngpeople.aliali.entity.member.Member;
import youngpeople.aliali.entity.member.School;
import youngpeople.aliali.exception.TokenException;
import youngpeople.aliali.repository.MemberRepository;
import youngpeople.aliali.repository.SchoolRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private static Long sequence = 100L;

    private final MemberRepository memberRepository;
    private final SchoolRepository schoolRepository;

    private final TokenService tokenService;
    private final MailService mailService;

    private final static String MAIL_SUBJECT = "ALIALI school auth : ";
    private final static String SCHOOL_AUTH_URI = "http://localhost:8888/my/auth/school-certification?authToken=";

    public MemberResDto findByKakaoId(String kakaoId) {
        Member member = memberRepository.findByKakaoId(kakaoId).get();
        log.info("service member = {}", member);
        return new MemberResDto(member);
    }

    public String login(String kakaoId) {
        Optional<Member> findMember = memberRepository.findByKakaoId(kakaoId);
        if (findMember.isEmpty()) {
            register(kakaoId);
        }

        return tokenService.createToken(kakaoId);
    }

    public void register(String kakaoId) {
        log.info("member join");
        Member member = new Member();
        member.setKakaoId(kakaoId);
        member.setNickname(autoNickname());

        memberRepository.save(member);
    }

    public BasicResDto updateMy(String kakoId, MemberReqDto memberReqDto) {
        Member member = memberRepository.findByKakaoId(kakoId).get();

        member.setNickname(memberReqDto.getNickname());
        member.setProfile(memberReqDto.getProfile());

        return BasicResDto.builder()
                .message("successful")
                .build();
    }

    public BasicResDto registerMySchool(String kakaoId, SchoolAuthReqDto schoolAuthReqDto) {
        Member member = memberRepository.findByKakaoId(kakaoId).get();

        Long schoolId = schoolAuthReqDto.getSchoolId();
        School school = schoolRepository.findById(schoolId).get();

        if (!schoolAuthReqDto.getEmail().contains(school.getEmail())) {
            // 익셉션 터뜨리기
        }

        String authToken = tokenService.createAuthToken(kakaoId, schoolId);
        mailService.send(schoolAuthReqDto.getEmail(), MAIL_SUBJECT + member.getNickname(), SCHOOL_AUTH_URI + authToken);

        return BasicResDto.builder()
                .message("successful")
                .build();
    }

    public BasicResDto authenticateSchool(String kakaoId, String authToken) {

        try {
            tokenService.verifyAuthToken(authToken);
        } catch (TokenException e) {
            log.info("Filter : ★★★ Authentication Warning ★★★ {}", e.getMessage());
            throw e;
        }

        Member member = memberRepository.findByKakaoId(kakaoId).get();

        Long schoolId = Long.parseLong(tokenService.getSchoolId(authToken));
        School school = schoolRepository.findById(schoolId).get();

        member.setSchool(school);

        return BasicResDto.builder()
                .message("successful")
                .build();
    }

    public void unregister(String kakaoId) {
        Member member = memberRepository.findByKakaoId(kakaoId).get();
        member.setActivated(false);
    }

    // 랜덤 닉네임 생성 메서드 필요
    private String autoNickname() {
        return "user" + sequence++;
    }

}
