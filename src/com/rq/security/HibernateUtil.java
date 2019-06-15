package com.rq.security;

import org.hibernate.*;
import org.hibernate.cfg.*;



public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static{

        try{
            sessionFactory =new Configuration().configure().buildSessionFactory();

        }catch (Exception ex){

            System.out.println("Exception :"+ ex);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

     public static void shutdown(){
        getSessionFactory().close();
     }

}
