package com.hateyahighschool.controller;

import com.hateyahighschool.model.Book;
import com.hateyahighschool.model.FixedValuedData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.peer.ListPeer;
import java.util.List;

/**
 * Created by A.A.MAMUN on 8/20/2019.
 */
@Controller
public class LibraryController {

    @RequestMapping("/library")
    private String initLibrary(ModelMap modelMap)
    {
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        List<Book>  academicBookList6 = null;
        List<Book>  academicBookList7 = null;
        List<Book>  academicBookList8 = null;
        List<Book>  academicBookList9_10 = null;
        List<Book>  popularBookList = null;

        try{

            academicBookList6 = session.createQuery("from Book where forCls='Class 6'", Book.class).list();
            academicBookList7 = session.createQuery("from Book where forCls='Class 7'", Book.class).list();
            academicBookList8 = session.createQuery("from Book where forCls='Class 8'", Book.class).list();
            academicBookList9_10 = session.createQuery("from Book where forCls='Class 9-10'", Book.class).list();
            popularBookList = session.createQuery("from Book where forCls='Popular'",Book.class).list();

            modelMap.addAttribute("academicBookList6",academicBookList6);
            modelMap.addAttribute("academicBookList7",academicBookList7);
            modelMap.addAttribute("academicBookList8",academicBookList8);
            modelMap.addAttribute("academicBookList9_10",academicBookList9_10);
            modelMap.addAttribute("popularBookList",popularBookList);

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

        return "library";
    }

}
