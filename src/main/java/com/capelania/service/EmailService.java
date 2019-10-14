package com.capelania.service;

import com.capelania.form.EmailForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender emailSender;

    @Autowired
    public EmailService(@Qualifier("JavaMailSenderImpl") JavaMailSender emailSender){
        this.emailSender = emailSender;
    }

    public void sendSimpleEmail(EmailForm form) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(form.getEmail());
        message.setSubject(form.getSubject() + " - " + form.getName());
        message.setText(form.getText());
        emailSender.send(message);
    }
}
