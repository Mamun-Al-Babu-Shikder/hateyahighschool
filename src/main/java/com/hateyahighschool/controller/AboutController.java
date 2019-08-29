package com.hateyahighschool.controller;

import com.hateyahighschool.model.FixedValuedData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by A.A.MAMUN on 7/13/2019.
 */


@Controller
public class AboutController {

    @RequestMapping(value = "/about")
    private String init(ModelMap modelMap) {

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

            FixedValuedData class_6 =  session.get(FixedValuedData.class,"class_6");
            modelMap.addAttribute("class_6",class_6);

            FixedValuedData class_7 =  session.get(FixedValuedData.class,"class_7");
            modelMap.addAttribute("class_7",class_7);

            FixedValuedData class_8 =  session.get(FixedValuedData.class,"class_8");
            modelMap.addAttribute("class_8",class_8);

            FixedValuedData class_9_10 =  session.get(FixedValuedData.class,"class_9_10");
            modelMap.addAttribute("class_9_10",class_9_10);


        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
            sf.close();
        }

        return "about";
    }



}
