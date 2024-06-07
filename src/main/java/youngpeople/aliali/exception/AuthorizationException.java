package youngpeople.aliali.exception;

public class AuthorizationException extends RuntimeException {

    private static final Integer CODE = 444;
    private static final String MESSAGE = "접근 권한이 없습니다.";

}
