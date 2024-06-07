package youngpeople.aliali.exception;

/**
 * 토큰 검증 실패에 대한 결과 처리 설계 해야함
 */
public class TokenException extends RuntimeException {

    private static final Integer CODE = 445;
    private static final String MESSAGE = "토큰 검증에 실패했습니다.";

}
