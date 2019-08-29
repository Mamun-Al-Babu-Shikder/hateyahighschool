package com.hateyahighschool.dbconnection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by A.A.MAMUN on 8/22/2019.
 */
public class HibernateConnection {

    private static Configuration configuration = null;
    private static SessionFactory sf = null;
    private static Session session = null;

    private HibernateConnection(){
        configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        session = sf.openSession();
    }

    public static Session getInstanceSession(){
        if(session==null){
            new HibernateConnection();
        }
        return session;
    }

    public static void close(){
        if(session!=null){
            session.close();
            sf.close();
        }
    }

}

