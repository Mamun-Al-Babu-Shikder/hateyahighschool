package com.hateyahighschool.controller;

import com.hateyahighschool.dbconnection.DbConnection;
import com.hateyahighschool.model.Events;
import com.hateyahighschool.model.FixedValuedData;
import com.hateyahighschool.model.Notice;
import com.hateyahighschool.model.Subscriber;
import com.hateyahighschool.service.SendEmail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by A.A.MAMUN on 6/23/2019.
 */

@Controller
public class HomeController {

    //public static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static";
    //public static String uploadDir = "https://morejust.herokuapp.com/file";

    @RequestMapping(value = "/")
    public String home(Model model){
        //model.addAttribute("url","images/bk1.PNG");
        List<Notice> noticeList = null;
        List<Events> eventList = null;
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        try{
            noticeList = session.createQuery("from Notice order by id DESC",Notice.class).list();
            eventList = session.createQuery("from Events order by id DESC",Events.class).list();

            FixedValuedData footer_social_media_facebook = session.get(FixedValuedData.class,"footer_social_media_facebook");
            model.addAttribute("footer_social_media_facebook",footer_social_media_facebook.getLink());

            FixedValuedData footer_social_media_twitter = session.get(FixedValuedData.class,"footer_social_media_twitter");
            model.addAttribute("footer_social_media_twitter",footer_social_media_twitter.getLink());

            FixedValuedData footer_social_media_linkedin = session.get(FixedValuedData.class,"footer_social_media_linkedin");
            model.addAttribute("footer_social_media_linkedin",footer_social_media_linkedin.getLink());

            FixedValuedData footer_information_tel = session.get(FixedValuedData.class,"footer_information_tel");
            model.addAttribute("footer_information_tel",footer_information_tel.getValue());

            FixedValuedData footer_information_email = session.get(FixedValuedData.class,"footer_information_email");
            model.addAttribute("footer_information_email",footer_information_email.getValue());

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
            sf.close();
        }
        model.addAttribute("noticeList",noticeList);
        model.addAttribute("eventList", eventList);
        return "index";
    }


    @RequestMapping(value = "subscribe", method = RequestMethod.POST)
    private String subscribeRequest(@RequestParam String name, @RequestParam String email,
     @RequestParam String phone, @RequestParam String address)
    {
        //System.out.println("Name : "+name+", Email : "+email+", Phone : "+phone+", Address : "+address);

        boolean bol = true;

        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        try{

            Subscriber subscriber = new Subscriber();
            subscriber.setName(name);
            subscriber.setEmail(email);
            subscriber.setPhone(phone);
            subscriber.setAddress(address);

            Transaction t = session.beginTransaction();
            session.save(subscriber);
            t.commit();

        }catch (Exception ex){
            bol = false;
            ex.printStackTrace();
        }finally {
            session.close();
            sf.close();
        }

        try {
            if (bol) {

                SendEmail.sendEmail(email, "Subscribe Success !",
                        "Dear " + name + ", You successfully subscribe to our website. Now you will get all update" +
                                " of our activity.");
            } else {
                SendEmail.sendEmail(email, "Subscribe Failed !",
                        "Dear " + name + ", Sorry your subscribe is not success. Please try again with another email" +
                                " address. Thanks.");

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "redirect:/";
    }



    /*
    @RequestMapping(value = "/upload")
    public String uploadFile(@RequestParam MultipartFile file, Model model)
    {
        Path filePath = Paths.get(uploadDir, file.getOriginalFilename());
        try {
            Files.write(filePath, file.getBytes());
            model.addAttribute("file_ex",filePath.toAbsolutePath());
        } catch (IOException e) {
            model.addAttribute("file_ex",e);
            e.printStackTrace();
        }
        return "home";
    }
    */








}



