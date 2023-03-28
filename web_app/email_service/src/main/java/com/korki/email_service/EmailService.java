package com.korki.email_service;

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
    private static final String EMAIL_TEXT = "TEST_TEXT\nActivation Link: http://localhost:8080/activation/";

    @Async
    public void sendActivationLink(String to, String url) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("teachingserviceks@gmail.com");
        message.setTo(to);
        message.setSubject(EMAIL_SUBJECT);
        message.setText(createActivationLinkMessage(url));
        emailSender.send(message);
    }

    @Async
    public void sendApplyAdvertMessage(String to, String studentName, String teacherName){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("teachingserviceks@gmail.com");
        message.setTo(to);
        message.setSubject("Hi " + teacherName + ", someone applied for You advert!");
        message.setText("Hi " + teacherName + ", " + studentName + " has applied for Your advert!");
    }
    private String createActivationLinkMessage(String url){
        return EMAIL_TEXT +
                url;
    }
}
