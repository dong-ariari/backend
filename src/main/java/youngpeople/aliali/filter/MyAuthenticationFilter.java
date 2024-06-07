package youngpeople.aliali.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import youngpeople.aliali.controller.Message;
import youngpeople.aliali.entity.member.Member;
import youngpeople.aliali.exception.AuthorizationException;
import youngpeople.aliali.exception.TokenException;
import youngpeople.aliali.repository.MemberRepository;
import youngpeople.aliali.service.TokenService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class MyAuthenticationFilter implements Filter {
    ;
    private static final String LOGIN_URL = "/login";
    private static final String[] whitelist = {
            "/",
            LOGIN_URL,
            "/reissue",
            "/token/**",
            "/test/**"};

    private final TokenService tokenService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Filter Bean was registered");
    }

    // refreshToken에 대한 처리도 해야함
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("===== MyAuthenticationFilter start ===== ");
        String requestURI = httpRequest.getRequestURI();

        if (!isLoginCheckPath(requestURI)) {
            log.info(" Filter : Allowed to All {} === ", requestURI);
            chain.doFilter(request, response);
            return;
        }

        log.info(" Filter : Authentication is Necessary {} ", requestURI);

        String accessToken = getToken(httpRequest);
        if (accessToken == null) {
            log.info(" Filter : there is no token  ");
            return;
        }

        try {
            tokenService.verifyToken(accessToken);
        } catch (TokenException e) {
            log.info(" Filter : ★★★ Authentication Warning ★★★ ( wrong token or timeout )");
            return;
        }

        log.info(" Filter : Authentication is Successful {} ", requestURI);
        String kakaoId = tokenService.getKakaoId(accessToken);
        httpRequest.setAttribute("kakaoId", kakaoId);

        chain.doFilter(request, httpResponse);

        log.info("===== MyAuthenticationFilter end ===== ");
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }

    private String getToken(HttpServletRequest request) {
        String accessToken = request.getHeader("authorization");
        if (accessToken == null) {
            return null;
        } else {
            return accessToken;
        }

    }

}
