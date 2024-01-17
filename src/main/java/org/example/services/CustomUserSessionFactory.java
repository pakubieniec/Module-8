package org.example.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CustomUserSessionFactory {
    public static SessionFactory getUserSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure("/hibernate.cfg.xml");
        return configuration.buildSessionFactory();
    }
}
