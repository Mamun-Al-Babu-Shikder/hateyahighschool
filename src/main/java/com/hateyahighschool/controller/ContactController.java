package com.hateyahighschool.controller;

import com.hateyahighschool.model.FixedValuedData;
import com.hateyahighschool.model.IncomeMessage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * Created by A.A.MAMUN on 7/18/2019.
 */
@Controller
public class ContactController {


    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping("/contact")
    private String initContactPage(ModelMap modelMap)
    {
        modelMap.addAttribute("send_status",null);

        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        try{

            FixedValuedData footer_social_media_facebook = session.get(FixedValuedData.class,"footer_social_media_facebook");
            modelMap.addAttribute("footer_social_media_facebook",footer_social_media_facebook.getLink());

            FixedValuedData footer_social_media_twitter = session.get(FixedValuedData.class,"footer_social_media_twitter");
            modelMap.addAttribute("footer_social_media_twitter",footer_social_media_twitter.getLink());

            FixedValuedData footer_social_media_linkedin = session.get(FixedValuedData.class,"footer_social_media_linkedin");
            modelMap.addAttribute("footer_social_media_linkedin",footer_social_media_linkedin.getLink());

            FixedValuedData footer_information_tel = session.get(FixedValuedData.class,"footer_information_tel");
            modelMap.addAttribute("footer_information_tel",footer_information_tel.getValue());

            FixedValuedData footer_information_email = session.get(FixedValuedData.class,"footer_information_email");
            modelMap.addAttribute("footer_information_email",footer_information_email.getValue());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
            sf.close();
        }


        return "contact";
    }


    @RequestMapping(value = "/send_message", method = RequestMethod.POST)
    private String sendMessage(@RequestParam String name, @RequestParam String email, @RequestParam String phone,
                               @RequestParam String message, ModelMap modelMap)
    {
        boolean bol = true;
        System.out.println("Name : "+name+", Email : "+email+", Phone : "+phone+", Message : "+message);


        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        try{

            IncomeMessage incomeMessage = new IncomeMessage();
            incomeMessage.setName(name);
            incomeMessage.setEmail(email);
            incomeMessage.setPhone(phone);
            incomeMessage.setMessage(message);
            incomeMessage.setDate(new Date().toLocaleString());
            incomeMessage.setStatus(IncomeMessage.UNSEEN);

            Transaction t = session.beginTransaction();
            session.save(incomeMessage);
            t.commit();

        }catch (Exception ex){
            bol = false;
            ex.printStackTrace();
        }finally {
            session.close();
            sf.close();
        }

        if(bol){
            modelMap.addAttribute("send_status","SUCCESS");
        }else{
            modelMap.addAttribute("send_status","FAILED");
        }


        return "contact";
    }






    /*
    private void sendEmail() {

        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            // msg.setTo("to_1@gmail.com", "to_2@gmail.com", "to_3@yahoo.com");

            msg.setFrom("babushikder340@gmail.com");
            msg.setText("aalmamun295@gmail.com");
            msg.setSubject("Testing from Spring Boot");
            msg.setText("Hello World \n Spring Boot Email");

            javaMailSender.send(msg);

            System.out.println("Successfully sent....................");
        }catch (Exception e){
            System.out.println(e+"\n................");
        }

    }
    */
}
