package youngpeople.aliali;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import youngpeople.aliali.entity.member.Member;
import youngpeople.aliali.entity.member.School;
import youngpeople.aliali.filter.MyAuthenticationFilter;
import youngpeople.aliali.repository.MemberRepository;
import youngpeople.aliali.repository.SchoolRepository;
import youngpeople.aliali.service.MemberService;
import youngpeople.aliali.service.TokenService;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebConfig {

    private final TokenService tokenService;
    private final SchoolRepository schoolRepository;
    private final MemberRepository memberRepository;

    @Bean
    public FilterRegistrationBean addAuthenticationFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new MyAuthenticationFilter(tokenService));
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }

    /**
     * 서비스 레이어 단위 테스트는 아래 데이터셋으로 진행
     * test data init :
     *  - school
     *  - member
     */
    @PostConstruct
    public void testDataInit() {
        School school1 = new School("세종대학교", "@sju.ac.kr");
        School school2 = new School("건국대학교", "@kku.ac.kr");
        schoolRepository.save(school1);
        schoolRepository.save(school2);

        Member member1 = new Member("3456718320", "원순재");
        Member member2 = new Member("3457570799", "유한석");
        Member member3 = new Member("3458126487", "김대선");
        Member member4 = new Member("3459895644", "변성은");
        member1.setSchool(school1);
        member2.setSchool(school1);
        member3.setSchool(school2);
        member4.setSchool(school2);
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);

        for (int i = 5; i <= 20; i++) {
            Member newMember = new Member("00000000" + i, "user" + i);
            newMember.setSchool(school1);
            memberRepository.save(newMember);
        }

        log.info("Setting test data set is complete");


    }
}
