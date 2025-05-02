package com.example.authsystem.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final String mailFrom;

    public EmailService(
            JavaMailSender javaMailSender,
            @Value("${mi-app.mail.from}") String mailFrom
    ) {
        this.javaMailSender = javaMailSender;
        this.mailFrom = mailFrom;
    }

    public void sendEmail (String recipient, String subject, String body){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(mailFrom);
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }
}
