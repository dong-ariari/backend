package youngpeople.aliali.controller.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import youngpeople.aliali.controller.Message;
import youngpeople.aliali.service.MailService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/test/11")
    public Message send() {
        String to = "won9619v@naver.com";
        String subject = "test";
        String text = "여긴 text";

        mailService.send(to, subject, text);

        return new Message(
                "잘 보내졌다"
        );
    }

}
