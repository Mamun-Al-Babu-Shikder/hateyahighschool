package com.hateyahighschool.controller;

import com.hateyahighschool.model.FixedValuedData;
import com.hateyahighschool.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by A.A.MAMUN on 8/17/2019.
 */
@Controller
public class TeacherController {

    @RequestMapping(value = "/teacher")
    private String getTeacher(ModelMap modelMap)
    {
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        List<Teacher> allTeacherList = null;
        List<Teacher> currentTeacherList = null;
        List<Teacher> retiredTeacherList = null;


        try{

            allTeacherList = session.createQuery("from Teacher", Teacher.class).list();
            currentTeacherList = session.createQuery("from Teacher where type = 'Current'", Teacher.class).list();
            retiredTeacherList = session.createQuery("from Teacher where type = 'Retired'", Teacher.class).list();


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

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
            sf.close();
        }

        modelMap.addAttribute("allTeacherList", allTeacherList);
        modelMap.addAttribute("currentTeacherList", currentTeacherList);
        modelMap.addAttribute("retiredTeacherList", retiredTeacherList);





        return "teacher";
    }
}
