package com.example.authsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${mail.from}")
    private String mailFrom;

    public void sendEmail (String recipient, String subject, String body){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(mailFrom);
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }
}
