package youngpeople.aliali.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import youngpeople.aliali.entity.member.Member;
import youngpeople.aliali.entity.member.School;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void send(String to, String subject, String text) {

        SimpleMailMessage message = createEmailForm(to, subject, text);

        mailSender.send(message);
        log.info(" Sending the mail is successful {} {} {}", to, subject, text);
    }

    private SimpleMailMessage createEmailForm(String toEmail,
                                              String title,
                                              String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);

        return message;
    }

}
