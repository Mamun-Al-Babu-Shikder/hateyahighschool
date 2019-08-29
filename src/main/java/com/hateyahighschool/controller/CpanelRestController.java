package com.hateyahighschool.controller;

import com.hateyahighschool.model.*;
import com.hateyahighschool.service.SendEmail;
import com.hateyahighschool.service.SendSmsByTwilio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A.A.MAMUN on 7/5/2019.
 */

@RestController
public class CpanelRestController {

    //private Session session;
    //private SessionFactory sessionFactory;



    private static final String[] month = {"NaN","JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};


    /*
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    private void getDataFromJquery(@RequestBody AppUser appUser)
    {
        //System.out.println("Name : "+name+", City : "+city);
        System.out.println("Email : "+appUser.getEmail()+", Pass : "+appUser.getPassword());
    }
    */


    /*
    @RequestMapping(value = "/data2", method = RequestMethod.GET)
    private List<String> getDataFromJquery2()
    {
        List<String> list = new ArrayList<>();
        list.add("Abc");
        list.add("Mamun");
        list.add("Mitu");

        return list;
    }
    */


     /*
    @ CPANEL HOME
    @ All Method for CPANEL HOME
     */

     /*
     @ Fetch all notice
      */
     @RequestMapping(value = "fetch_notice", method = RequestMethod.POST)
     private List<Notice> getNoticeList()
     {

         Configuration configuration = new Configuration().configure();
         SessionFactory sf = configuration.buildSessionFactory();
         Session session = sf.openSession();
         List<Notice> noticeList = null;
         try{
             Transaction t = session.beginTransaction();
             noticeList = session.createQuery("from Notice order by id desc",Notice.class).list();
             t.commit();
         }catch (Exception ex){
             ex.printStackTrace();
         }finally {
             session.close();
             sf.close();
         }

         //System.out.println("Notice List : \n"+noticeList.toString());

         return noticeList;
     }

     /*
     @ Save Notice To Database
      */
     @RequestMapping(value = "save_notice", method = RequestMethod.POST)
     private boolean saveNotice(String noticeTitle, String noticeDate, String noticeBody)
     {
         boolean bol = true;

         Configuration configuration = new Configuration().configure();
         SessionFactory sf = configuration.buildSessionFactory();
         Session session = sf.openSession();

         try{

             Notice notice = new Notice();
             notice.setTitle(noticeTitle);
             notice.setDate(noticeDate);
             notice.setBody(noticeBody);

             Transaction t = session.beginTransaction();
             session.save(notice);
             t.commit();

         }catch (Exception ex){
             bol = false;
             ex.printStackTrace();
         }finally {
             session.close();
             sf.close();
         }

         return bol;
     }



     /*
     @ Method to edit notice
      */
     @RequestMapping(value = "edit_notice", method = RequestMethod.POST)
     private boolean editNotice(@RequestBody Notice notice)
     {
         boolean bol = true;

         System.out.println("Notice : "+notice.toString());

         Configuration configuration = new Configuration().configure();
         SessionFactory sf = configuration.buildSessionFactory();
         Session session = sf.openSession();

         try{

             Transaction t = session.beginTransaction();
             session.update(notice);
             t.commit();

         }catch (Exception ex){
             bol = false;
             ex.toString();
         }finally {
             session.close();
             sf.close();
         }

         return bol;
     }


     /*
     @ Delete Notice From Database
      */
     @RequestMapping(value = "delete_notice", method = RequestMethod.POST)
     private boolean deleteNotice(@RequestParam int id)
     {

         boolean bol =  true;

         Configuration configuration = new Configuration().configure();
         SessionFactory sf = configuration.buildSessionFactory();
         Session session = sf.openSession();

         try{

             Transaction t = session.beginTransaction();
             Notice notice = session.get(Notice.class, id);
             session.delete(notice);
             t.commit();

         }catch (Exception ex){
             bol = false;
             ex.printStackTrace();
         }finally {
             session.close();
             sf.close();
         }

         return bol;
     }


     /*
     @ Fetch all event
      */
     @RequestMapping(value = "fetch_events", method = RequestMethod.POST)
     private List<Events> getEventList()
     {
         List<Events> eventList = null;
         Configuration configuration = new Configuration().configure();
         SessionFactory sf = configuration.buildSessionFactory();
         Session session = sf.openSession();

         try{
             Transaction t = session.beginTransaction();
             eventList = session.createQuery("from Events order by id desc", Events.class).list();
             t.commit();
         }catch (Exception ex){
             ex.printStackTrace();
         }finally {
             session.close();
             sf.close();
         }

        // System.out.println("EventList : \n"+eventList.toString());

         return eventList;
     }



     /*
     @ Save Event
      */

     @RequestMapping(value = "save_event", method = RequestMethod.POST)
     private boolean saveEvents(@RequestBody Events event)
     {
         boolean bol = true;

         String[] date = event.getDate().split("-");
         event.setDd(date[2]);
         event.setMm(month[Integer.parseInt(date[1])]);

         event.setImgName("images/event/sports_event_1.jpg");

         Configuration configuration = new Configuration().configure();
         SessionFactory sf = configuration.buildSessionFactory();
         Session session = sf.openSession();

         try{
             Transaction t = session.beginTransaction();
             session.save(event);
             t.commit();
         }catch (Exception ex){
             bol =false;
             ex.printStackTrace();
         }finally {
             session.close();
             sf.close();
         }
         return bol;
     }


     /*
     @ Edit event method
      */
     @RequestMapping(value = "edit_event", method = RequestMethod.POST)
     private boolean editEvent(@RequestBody Events event)
     {
         boolean bol = true;

         System.out.println("Event : "+event.toString());

         String[] date = event.getDate().split("-");
         event.setDd(date[2]);
         event.setMm(month[Integer.parseInt(date[1])]);

         event.setImgName("images/event/sports_event_1.jpg");

         Configuration configuration = new Configuration().configure();
         SessionFactory sf = configuration.buildSessionFactory();
         Session session = sf.openSession();

         try{
             Transaction t = session.beginTransaction();
             session.update(event);
             t.commit();
         }catch (Exception ex){
             bol =false;
             ex.printStackTrace();
         }finally {
             session.close();
             sf.close();
         }

         return bol;
     }

     /*
     @ Delete event method
      */
     @RequestMapping(value = "delete_event", method = RequestMethod.POST)
     private boolean deleteEvent(@RequestParam int id)
     {
         boolean bol = true;

         Configuration configuration = new Configuration().configure();
         SessionFactory sf = configuration.buildSessionFactory();
         Session session = sf.openSession();

         try{
             Transaction t = session.beginTransaction();
             Events event = session.get(Events.class, id);
             session.delete(event);
             t.commit();
         }catch (Exception ex){
             bol = false;
             ex.printStackTrace();
         }finally {
             session.close();
             sf.close();
         }
         return bol;
     }



     /*
     @ Fetch subscribe method
      */
     @RequestMapping(value = "fetch_subscriber", method = RequestMethod.POST)
     private List<Subscriber> getSubscriberList()
     {
         //System.out.println("-------------------------------------Subscriber---------------------------------");
         List<Subscriber> subscriberList = null;
         Configuration configuration = new Configuration().configure();
         SessionFactory sf = configuration.buildSessionFactory();
         Session session = sf.openSession();
         try{
             subscriberList = session.createQuery("from Subscriber",Subscriber.class).list();
         }catch (Exception ex){
             ex.printStackTrace();
         }finally {
             session.close();
             sf.close();
         }
         return subscriberList;
     }

     /*
     @ Delete subscriber method
      */

     @RequestMapping(value = "delete_subscriber", method = RequestMethod.POST)
     private boolean deleteSubscriber(@RequestParam String email)
     {
         boolean bol = true;

         Configuration configuration = new Configuration().configure();
         SessionFactory sf = configuration.buildSessionFactory();
         Session session = sf.openSession();


         try{
             Transaction t = session.beginTransaction();
             Subscriber subscriber = session.createQuery("from Subscriber where email = '"+email+"'",
                     Subscriber.class).getSingleResult();
             session.delete(subscriber);
             t.commit();

         }catch (Exception ex){
             bol = false;
             ex.printStackTrace();
         }finally {
             session.close();
             sf.close();
         }

         return bol;
     }


     /*
     @ Section for About Us
     @ All methods for cpanel_about_us
      */
     /*
     @ Fetch all fixed valued data
      */
     @RequestMapping(value = "fetch_about_date", method = RequestMethod.POST)
     private List<FixedValuedData> getAboutUsData()
     {
         Configuration configuration = new Configuration().configure();
         SessionFactory sf = configuration.buildSessionFactory();
         Session session = sf.openSession();

         List<FixedValuedData> fixedValuedDataList = null;

         try{
             fixedValuedDataList = session.createQuery("from FixedValuedData", FixedValuedData.class).list();
         }catch (Exception ex){
             ex.printStackTrace();
         }finally {
             session.close();
             sf.close();
         }
         return fixedValuedDataList;
     }


     /*
     @ Update data of about us
      */

     @RequestMapping(value = "update_about_us", method = RequestMethod.POST)
     private boolean updateAboutData(@RequestParam String id, @RequestParam String imgurl, @RequestParam String value,
                                     @RequestParam String link)
     {
         boolean b = true;
         Configuration configuration = new Configuration().configure();
         SessionFactory sf = configuration.buildSessionFactory();
         Session session = sf.openSession();

         try{

             FixedValuedData object = session.get(FixedValuedData.class, id);
             if(id.equals("footer_social_media_facebook")
                     || id.equals("footer_social_media_twitter")
                     || id.equals("footer_social_media_linkedin")){

                 Transaction t = session.beginTransaction();
                 object.setLink(link);
                 session.update(object);
                 t.commit();

             }else if(id.equals("class_6")
                     || id.equals("class_7")
                     || id.equals("class_8")
                     || id.equals("class_9_10")){

                 Transaction t = session.beginTransaction();
                 object.setImgurl(imgurl);
                 object.setValue(value);
                 session.update(object);
                 t.commit();

             }else if(id.equals("footer_information_tel")
                     || id.equals("footer_information_email")){
                 Transaction t = session.beginTransaction();
                 object.setValue(value);
                 session.update(object);
                 t.commit();
             }

         }catch (Exception e){
             b = false;
             e.printStackTrace();
         }finally {
             session.close();
             sf.close();
         }

         return b;
     }




     /*
     @ Section for Contact
     @ All methods for cpanel_contact
      */

     /*
     @ Fetch all income message.
      */
     @RequestMapping(value = "fetch_income_message", method = RequestMethod.POST)
     private List<IncomeMessage> getIcomeMessageList()
     {
         List<IncomeMessage> incomeMessageList = null;

         Configuration configuration = new Configuration().configure();
         SessionFactory sf = configuration.buildSessionFactory();
         Session session = sf.openSession();

         try{

             incomeMessageList = session.createQuery("from IncomeMessage order by id desc ", IncomeMessage.class)
                     .list();

         }catch (Exception ex){

             ex.printStackTrace();
         }finally {
             session.close();
             sf.close();
         }

         return incomeMessageList;
     }

     /*
     @ Update income message status
      */
     @RequestMapping(value = "update_income_message_status", method = RequestMethod.POST)
     private boolean updateIncomeMessageStatus(@RequestBody IncomeMessage incomeMessage)
     {
         boolean bol = true;

         System.out.println(incomeMessage.toString());

         Configuration configuration = new Configuration().configure();
         SessionFactory sf = configuration.buildSessionFactory();
         Session session = sf.openSession();
         try{
             Transaction t =  session.beginTransaction();
             session.update(incomeMessage);
             t.commit();
         }catch (Exception ex){
             bol = false;
             System.out.println(ex.getMessage());
             ex.printStackTrace();
         }finally {
             session.close();
             sf.close();
         }

         return bol;
     }



     /*
     @ Delete income message
      */
     @RequestMapping(value = "delete_income_message", method = RequestMethod.POST)
     private boolean deleteIncomeMessage(@RequestParam int id)
     {
         boolean bol = true;

         Configuration  configuration = new Configuration().configure();
         SessionFactory sf = configuration.buildSessionFactory();
         Session session = sf.openSession();

         try{
             Transaction t= session.beginTransaction();
             IncomeMessage incomeMessage = session.get(IncomeMessage.class, id);
             session.delete(incomeMessage);
             t.commit();
         }catch (Exception ex){
             bol = false;
             ex.printStackTrace();
         }finally {

             session.close();;
             sf.close();
         }

         return bol;
     }


     /*
     @ Reply message by email
      */
     @RequestMapping(value = "send_reply_email", method = RequestMethod.POST)
     private boolean sendEmail(@RequestParam String email, @RequestParam String subject, @RequestParam String message)
     {
         boolean bol = true;


         System.out.println("sending to : "+email+".......");

         System.out.println(email+", "+subject+", "+message);

         try{
             SendEmail.sendEmail(email, subject, message);
         }catch (Exception ex){
             bol = false;
             ex.printStackTrace();
         }

         return bol;
     }


     /*
     @ Reply message by phone
      */
     @RequestMapping(value = "send_sms", method = RequestMethod.POST)
     private boolean sendSms(@RequestParam String phone, @RequestParam String message)
     {
         boolean bol = true;

         System.out.println("Phone : "+phone+", Message : "+message);
         try{
             SendSmsByTwilio.sendSms(phone, message);
         }catch (Exception ex){
             bol = false;
             ex.printStackTrace();
         }

         return bol;
     }






    /*
    @ CPANEL CLASS
    @ All Method for CPANEL CLASS
     */

    /*
    @ Method for get syllabus
     */
    @RequestMapping(value = "access_class_syllabus", method = RequestMethod.POST)
    private Syllabus getSyllabus(@RequestParam int cls)
    {
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        Syllabus syllabus = null;
        try{
            syllabus = session.get(Syllabus.class, cls);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
            sf.close();
        }

        return syllabus;
    }

    @RequestMapping(value = "update_syllabus", method = RequestMethod.POST)
    private boolean updateSyllabus(@RequestParam int cls, @RequestParam String link)
    {
        boolean b = true;
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        try{

            Syllabus syllabus = session.get(Syllabus.class, cls);
            Transaction t = session.beginTransaction();
            syllabus.setLink(link);
            session.update(syllabus);
            t.commit();

        }catch (Exception e){
            b = false;
            e.printStackTrace();;
        }finally {
            session.close();
            sf.close();
        }

        return b;
    }

    /*
    Read all book reference order by class from database and send to view via jquery.
     */
    @RequestMapping(value = "access_class_book", method = RequestMethod.POST)
    private List<BookReference> getBookList(@RequestParam int cls)
    {

        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        List<BookReference> bookList = null;
        try {
            bookList = session.createQuery("from BookReference where forClass=" + cls + "  order by id asc",
                    BookReference.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
           session.close();
           sf.close();
        }
        //System.out.println(bookList.toString());
        return bookList;
    }

    /*
    Save book to database
     */
    @RequestMapping(value = "save_book", method = RequestMethod.POST)
    private boolean saveBook(@RequestParam String bookName, @RequestParam String pdfLink, @RequestParam int forClass){

        boolean bol = true;
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        try{
            Transaction t = session.beginTransaction();
            BookReference book = new BookReference();
            book.setBookName(bookName);
            book.setPdfFileLink(pdfLink);
            book.setForClass(forClass);
           // System.out.println("Book : "+book.toString());
            session.save(book);
            t.commit();

        }catch (Exception ex){
            System.out.println("Ex : \n"+ex.getMessage());
            bol = false;
        }finally {
            session.close();
            sf.close();
        }
        return bol;
    }


    /*
    Delete book from database.
    book id must come from view by jquery.
     */
    @RequestMapping(value = "delete_book", method = RequestMethod.POST)
    private boolean deleteBook(@RequestParam int bookId)
    {
        boolean bol = true;
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        try{

            Transaction t = session.beginTransaction();
            Object book = session.get(BookReference.class, bookId);
            //System.out.println("Delete book : \n"+book.toString());
            session.delete(book);
            t.commit();

        }catch (Exception ex){
            System.out.println("Ex : \n"+ex.getMessage());
            bol = false;
        }finally {
            session.close();
            sf.close();
        }

        return bol;
    }


    /*
    Read Class Routine from database order by class and send to view via jquery.
     */
    @RequestMapping(value ="access_class_routine", method = RequestMethod.POST)
    private List<ClassRoutine> getClassRoutine(@RequestParam int cls)
    {
        List<ClassRoutine> classRoutine = null;
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        try {
            classRoutine = session.createQuery("from ClassRoutine where forClass=" + cls + "  order by id asc",
                    ClassRoutine.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
            sf.close();
        }

        //System.out.println(classRoutine.toString());

        return classRoutine;
    }


    @RequestMapping(value = "update_class_routine", method = RequestMethod.POST)
    private boolean updateClassRoutine(@RequestParam int id,
                                       @RequestParam int forClass,
                                       @RequestParam String day,
                                       @RequestParam String one,
                                       @RequestParam String two,
                                       @RequestParam String three,
                                       @RequestParam String four,
                                       @RequestParam String five,
                                       @RequestParam String six)
    {
        boolean bol = true;

        ClassRoutine classRoutine = new ClassRoutine();
        classRoutine.setId(id);
        classRoutine.setForClass(forClass);
        classRoutine.setDay(day);
        classRoutine.setOne(one);
        classRoutine.setTwo(two);
        classRoutine.setThree(three);
        classRoutine.setFour(four);
        classRoutine.setFive(five);
        classRoutine.setSix(six);

        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        try{

            Transaction t = session.beginTransaction();
            session.update(classRoutine);
            t.commit();

        }catch (Exception ex){
            System.out.println("Ex : \n"+ex.getMessage());
            bol = false;
        }finally {
            session.close();
            sf.close();
        }
        //System.out.println("-----------------------------------> : "+classRoutine.toString());
      return bol;
    }


    @RequestMapping(value = "access_exam_routine", method = RequestMethod.POST)
    private List<ExamRoutine> getExamRoutine(@RequestParam int cls)
    {
        List<ExamRoutine> examRoutine = null;
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        try{
           examRoutine = session.createQuery("from ExamRoutine where forClass=" + cls + "  order by id asc",
                   ExamRoutine.class).list();
        }catch (Exception ex){
            System.out.println("Ex : \n"+ex.getMessage());
        }finally {
            session.close();
            sf.close();
        }

        //System.out.println(examRoutine.toString());
        return examRoutine;
    }


    @RequestMapping(value = "save_exam_routine", method = RequestMethod.POST)
    private boolean saveExamRoutine(
     @RequestParam String date, @RequestParam String subject,
     @RequestParam String startTime, @RequestParam String endTime,
     @RequestParam int forClass
    ) {
        boolean bol = true;

        ExamRoutine examRoutine = new ExamRoutine();
        examRoutine.setDate(date);
        examRoutine.setSubject(subject);
        examRoutine.setStartTime(startTime);
        examRoutine.setEndTime(endTime);
        examRoutine.setForClass(forClass);

        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        try{
            Transaction t = session.beginTransaction();
            session.save(examRoutine);
            t.commit();
        }catch (Exception ex){
            bol = false;
            System.out.println("Ex : \n"+ex.getMessage());
        }finally {
            session.close();
            sf.close();
        }
        //System.out.println("ExamRoutine : "+examRoutine.toString());
        return bol;
    }


    @RequestMapping(value = "update_exam_routine", method = RequestMethod.POST)
    private boolean updateExamRoutine(@RequestBody ExamRoutine examRoutine)
    {
        boolean bol = true;
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        try{
            Transaction t = session.beginTransaction();
            session.update(examRoutine);
            t.commit();
        }catch (Exception ex){
            bol = false;
            System.out.println("Ex : \n"+ex.getMessage());
        }finally {
            session.close();
            sf.close();
        }
        return bol;
    }

    @RequestMapping(value = "delete_exam_routine", method = RequestMethod.POST)
    private boolean deleteExamRoutine(@RequestParam int id)
    {
        boolean bol = true;
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        try{
            Transaction t = session.beginTransaction();
            session.delete(session.get(ExamRoutine.class, id));
            t.commit();
        }catch (Exception ex){
            System.out.println("Ex : \n"+ex.getMessage());
            bol = false;
        }finally {
            session.close();
            sf.close();
        }
        return bol;
    }



    @RequestMapping(value = "access_class_lecture", method = RequestMethod.POST)
    private List<ClassLecture> getClassLecture(@RequestParam int cls)
    {
        List<ClassLecture> classLectures = null;
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        try{
            Transaction t = session.beginTransaction();
            classLectures = session.createQuery("from ClassLecture where forClass=" + cls + "  order by id asc",
                    ClassLecture.class).list();
            t.commit();
        }catch (Exception ex){
            System.out.println("Ex : \n"+ex.getMessage());
        }finally {
            session.close();
            sf.close();
        }
       // System.out.println("ClassLecture : "+classLectures.toString());
        return classLectures;
    }

    @RequestMapping(value = "save_class_lecture", method = RequestMethod.POST)
    private boolean saveClassLecture(@RequestBody ClassLecture classLecture){
        boolean bol = true;
        //System.out.println("class lecture : "+classLecture.toString());
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        try{
            Transaction t = session.beginTransaction();
            session.save(classLecture);
            t.commit();
        }catch (Exception ex){
            System.out.println("Ex : \n"+ex.getMessage());
            bol = false;
        }finally {
            session.close();
            sf.close();
        }
        return bol;
    }



    @RequestMapping(value = "delete_class_lecture", method = RequestMethod.POST)
    private boolean deleteClassLecture(@RequestParam int id){
        boolean bol = true;
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        try{
            Transaction t = session.beginTransaction();
            ClassLecture obj = session.get(ClassLecture.class, id);
            session.delete(obj);
            t.commit();
        }catch (Exception ex){
            System.out.println("Ex : \n"+ex.getMessage());
            bol = false;
        }finally {
            session.close();
            sf.close();
        }
        return bol;
    }


    @RequestMapping(value = "access_exam_result", method = RequestMethod.POST)
    private List<ExamResult> getExamResult(@RequestParam int cls)
    {
        List<ExamResult> examResult = null;
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        try{
            Transaction t = session.beginTransaction();
            examResult = session.createQuery("from ExamResult where forClass=" + cls + "  order by id asc",
                    ExamResult.class).list();
            t.commit();
        }catch (Exception ex){
            System.out.println("Ex : \n"+ex.getMessage());
        }finally {
            session.close();
            sf.close();
        }
       // System.out.println("ExamResult : "+examResult.toString());
        return examResult;
    }

    @RequestMapping(value = "save_exam_result", method = RequestMethod.POST)
    private boolean saveExamResult(@RequestBody ExamResult examResult){
        boolean bol = true;
        //System.out.println("class lecture : "+examResult.toString());
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        try{
            Transaction t = session.beginTransaction();
            session.save(examResult);
            t.commit();
        }catch (Exception ex){
            System.out.println("Ex : \n"+ex.getMessage().toString());
            bol = false;
        }finally {
            session.close();
            sf.close();
        }
        return bol;
    }


    @RequestMapping(value = "delete_exam_result", method = RequestMethod.POST)
    private boolean deleteExamResult(@RequestParam int id){
        boolean bol = true;
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        try{
            Transaction t = session.beginTransaction();
            Object obj = session.get(ExamResult.class, id);
            session.delete(obj);
            t.commit();
        }catch (Exception ex){
            System.out.println("Ex : \n"+ex.getMessage());
            bol = false;
        }finally {
            session.close();
            sf.close();
        }
        return bol;
    }



    /*
    @ All method for cpanel teacher
     */
    /*
    @ Fetch all teacher method
     */
    @RequestMapping(value = "access_teacher_info", method = RequestMethod.POST)
    private List<Teacher> getTeacher()
    {

        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        List<Teacher> teacherList =  null;
        try{
            teacherList = session.createQuery("from Teacher", Teacher.class).list();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
            sf.close();
        }
        return teacherList;
    }



    /*
    @ Save Teacher method
     */
    @RequestMapping(value = "save_teacher", method = RequestMethod.POST)
    private boolean saveTeacher(@RequestBody Teacher teacher)
    {

        //System.out.println("----------------------> Save : "+teacher);

        boolean bol = true;

        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        try{
            Transaction t = session.beginTransaction();
            session.save(teacher);
            t.commit();
        }catch (Exception ex){
            bol = false;
            System.err.println("Ex : "+ex.toString());
        }finally {
            session.close();
            sf.close();
        }

        return bol;
    }

    /*
    @ Update teacher method
     */
    @RequestMapping(value = "update_teacher", method = RequestMethod.POST)
    private boolean updateTeacher(@RequestBody Teacher teacher)
    {

       // System.out.println("----------------------> Update : "+teacher);

        boolean bol = true;
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        try{
            Transaction t = session.beginTransaction();
            session.update(teacher);
            t.commit();
        }catch (Exception e){
            bol = false;
            System.err.println("Ex : "+e.toString());
        }finally {
            session.close();
            sf.close();
        }
        return bol;
    }

    /*
    @ Delete teacher method
     */
    @RequestMapping(value = "delete_teacher", method = RequestMethod.POST)
    private boolean deleteTeacher(@RequestParam int id)
    {
       // System.out.println("----------------------> delete teacher id : "+id);

        boolean bol = true;
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        try{
            Transaction t = session.beginTransaction();
            Teacher teacher = session.get(Teacher.class,id);
            session.delete(teacher);
            t.commit();
        }catch (Exception e){
            bol = false;
            e.printStackTrace();
        }finally {
            session.close();
            sf.close();
        }
        return bol;
    }



    /*
    @ All methods for Library
     */
    /*
    @  Fetch all Book method
     */
    @RequestMapping(value = "fetch_library_book", method = RequestMethod.POST)
    private List<Book> getLibraryBook()
    {
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        List<Book> bookList = null;
        try{
            bookList = session.createQuery("from Book", Book.class).list();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
            sf.close();
        }

        //System.out.println("-----------> : "+bookList.toString());

        return bookList;
    }

    /*
    @ Save library book methods
     */

    @RequestMapping(value = "save_library_book", method = RequestMethod.POST)
    private boolean saveLibraryBook(@RequestBody Book book)
    {
        //System.out.println("--------------> Save : "+book.toString());

        boolean b = true;
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        try{
            Transaction t = session.beginTransaction();
            session.save(book);
            t.commit();
        }catch (Exception ex){
            b = false;
            System.out.println(ex);
        }finally {
            session.close();
            sf.close();
        }
        return b;
    }


    /*
    @ Update library book method
     */
    @RequestMapping(value = "update_library_book", method = RequestMethod.POST)
    private boolean updateLibraryBook(@RequestBody Book book)
    {
        //System.out.println("--------------> Update : "+book.toString());

        boolean b = true;
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        try{
            Transaction t = session.beginTransaction();
            session.update(book);
            t.commit();
        }catch (Exception ex){
            b = false;
            System.out.println(ex);
        }finally {
            session.close();
            sf.close();
        }
        return b;
    }


    /*
    @ Delete library book method
     */
    @RequestMapping(value = "delete_library_book", method = RequestMethod.POST)
    private boolean deleteLibraryBook(@RequestParam int id)
    {
        System.out.println("--------------> delete id : "+id);

        boolean b = true;
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        try{
            Transaction t = session.beginTransaction();
            session.delete(session.get(Book.class, id));
            t.commit();
        }catch (Exception ex){
            b = false;
            System.out.println(ex);
        }finally {
            session.close();
            sf.close();
        }
        return b;
    }

    /*
    @ Update book view method
     */
    @RequestMapping(value = "update_book_view", method = RequestMethod.POST)
    private void updateBookView(@RequestParam int id)
    {

        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        try{
            Book book = session.get(Book.class, id);
            book.setView(1+book.getView());
            Transaction t = session.beginTransaction();
            session.update(book);
            t.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
            sf.close();
        }
    }


    //---------------- GET AND DESTROY SESSION -----------//
    /*
    private void startSession(){
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
            session = sessionFactory.openSession();
            System.out.println("Successfully started..........................................SESSION");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void destroySession(){
        if (session!=null){
            session.close();
            sessionFactory.close();
            System.out.println("Successfully destroyed........................................SESSION");
        }
    }
    */



}
