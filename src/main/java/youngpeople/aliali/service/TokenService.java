package youngpeople.aliali.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import youngpeople.aliali.entity.member.School;
import youngpeople.aliali.exception.TokenException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 1. JwtToken 생성 : createAccessToken
 * 2. JwtToken 분석 : verifyToken
 *
 * auth0 라이브러리 사용
 */
@Slf4j
@Service
public class TokenService {

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${security.jwt.accees-expiration}")
    private int accessExpiration;

    private static final Long refreshExpiration = 3600000L * 24 * 360;

    @Value("${security.secret-key}")
    private String secretKey;

    private static final String SEPERATE_VALUE = "/////";

    /**
     * accessToken과 refreshToken을 합쳐서 반환 ("/"를 기준으로 구분)
     */
    public String createToken(String kakaoId) {
        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        Date accessDate = new Date(System.currentTimeMillis() + accessExpiration);
        Date refreshDate = new Date(System.currentTimeMillis() + refreshExpiration);

        String accessToken = JWT.create()
                .withIssuer(issuer)
                .withSubject(kakaoId)
                .withClaim("kakaoId", kakaoId)
                .withExpiresAt(accessDate)
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withIssuer(issuer)
                .withSubject(kakaoId)
                .withClaim("kakaoId", kakaoId)
                .withExpiresAt(refreshDate)
                .sign(algorithm);


        return accessToken + SEPERATE_VALUE + refreshToken;
    }

    //    성공 시 true 반환 및 log 출력 | 실패 시 exception 발생
    public void verifyToken(String accessToken) {
        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        log.info(" TokenService : Verifying accessToken started ");

        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();

            DecodedJWT verify = verifier.verify(accessToken);
            log.info(" TokenService : Verifying is successful ");

        } catch (Exception e) {
            log.info(" TokenService : Verifying is failed {}", e.getMessage());
            throw new TokenException();
        }
    }

    public String getKakaoId(String accessToken) {
        return JWT.decode(accessToken).getClaim("kakaoId").asString();
    }

    public String getAccessToken(String token) {
        return token.split(SEPERATE_VALUE)[0];
    }

    public String getRefreshToken(String token) {
        return token.split(SEPERATE_VALUE)[1];
    }

    /**
     * for school certification
     */
    public String createAuthToken(String kakaoId, Long schoolId) {
        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        Date date = new Date(System.currentTimeMillis() + 300000);

        String authToken = JWT.create()
                .withIssuer(issuer)
                .withSubject(kakaoId)
                .withClaim("kakaoId", kakaoId)
                .withClaim("schoolId", schoolId)
                .withExpiresAt(date)
                .sign(algorithm);

        return authToken;
    }

    public void verifyAuthToken(String authToken) {
        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        log.info(" authToken verifying ");

        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();

            DecodedJWT verify = verifier.verify(authToken);
            log.info(" verifying is successful {}", verify.getIssuer());

        } catch (Exception e) {
            log.info(" verifying is failed {}", e.getMessage());
            throw new TokenException();
        }
    }

    public String getSchoolId(String authToken) {
        return JWT.decode(authToken).getClaim("schoolId").asString();
    }

}