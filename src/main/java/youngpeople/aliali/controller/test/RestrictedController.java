package youngpeople.aliali.controller.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import youngpeople.aliali.controller.Message;

/**
 * 토큰 테스트를 위한 컨트롤러 :
 *  MyAuthenticationFilter에 의해 토큰 검증이 필요한 경로
 */
@Slf4j
@RestController
@RequestMapping("/restricted")
public class RestrictedController {

    @GetMapping("/1")
    public Message restricted1() {
        log.info("RESTRICTED 1 : Access is Successful");
        return new Message(
                "Access is Successful",
                null
        );
    }
}
