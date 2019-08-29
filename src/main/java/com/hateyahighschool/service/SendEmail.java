package com.hateyahighschool.service;

/**
 * Created by A.A.MAMUN on 8/9/2019.
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

    public static void sendEmail(String toEmail,String subject,String  message)
    {

        try
        {

            String fromEmail="hateyahighschool@gmail.com";
            String username="hateyahighschool@gmail.com";
            String password="abulbasar";

            Properties props= System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.fallback", "false");
            //props.put("mail.smtp.starttls.enable", "true");
            props.put( "mail.smtp.ssl.enable", "true");

            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(true);

            Message mailMessage=new MimeMessage(mailSession);
            mailMessage.setFrom(new InternetAddress(fromEmail));
            mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            //mailMessage.setContent(message, "html/text");
            mailMessage.setContent(message, "text/plain");
            mailMessage.setSubject(subject);

            Transport transport=mailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com",username,password);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        }
        catch (Exception ex) {
           ex.printStackTrace();
        }

   }

}

/*

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail
{
    public static void sendEmail(String toEmail,String subject,String  message)
    {
        try
        {
            String fromEmail="yourmail@gmail.com";
            String username="yourmail@gmail.com";
            String password="yourpassword";
            Properties props= System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put( "mail.smtp.ssl.enable", "true");
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(true);
            Message mailMessage=new MimeMessage(mailSession);
            mailMessage.setFrom(new InternetAddress(fromEmail));
            mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mailMessage.setContent(message, "text/plain");
            mailMessage.setSubject(subject);
            Transport transport=mailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com",username,password);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        }
        catch (Exception ex) {
           ex.printStackTrace();
        }
   }
}





 */
