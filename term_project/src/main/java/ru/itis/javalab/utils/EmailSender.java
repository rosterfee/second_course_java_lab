package ru.itis.javalab.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

/**
 * 15.02.2021
 * 19. spring-boot-demo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Component
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ExecutorService executorService;

    @Value("${spring.mail.username}")
    private String from;

    public void sendMail(String to, String subject, String text) {
        executorService.submit(() -> javaMailSender.send(mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true, "UTF-8");
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);
        }));
    }
}

