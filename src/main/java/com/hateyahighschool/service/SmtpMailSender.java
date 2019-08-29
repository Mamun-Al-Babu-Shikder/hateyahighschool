package com.hateyahighschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by A.A.MAMUN on 8/6/2019.
 */
/*
@Component
public class SmtpMailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to, String subject, String body)
    {

        try {



            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setProtocol("SMTP");
            mailSender.setPort(587);
            mailSender.setUsername("Babu Shikder");
            mailSender.setPassword("aamamun2262686");

            javaMailSender = mailSender;

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            mimeMessageHelper.setTo(to);
            mimeMessage.setFrom("babushikder340@gmail.com");
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body, true);

            javaMailSender.send(mimeMessage);


        }catch (Exception ex){
            ex.printStackTrace();
        }


    }
}
*/