package com.korki.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;
    private static final String EMAIL_SUBJECT = "TEST_SUBJECT";
    private static final String EMAIL_TEXT = "TEST_TEXT";

    @Async
    public void sendActivationLink(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("teachingserviceks@gmail.com");
        message.setTo(to);
        message.setSubject(EMAIL_SUBJECT);
        message.setText(EMAIL_TEXT);
        emailSender.send(message);
    }
}
