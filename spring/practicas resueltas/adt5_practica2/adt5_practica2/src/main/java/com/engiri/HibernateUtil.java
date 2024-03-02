package com.engiri;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {

            // Crea la SessionFactory utilizando la configuración de hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            // Crea una factoría
            sessionFactory = configuration.buildSessionFactory();

        } catch (Throwable ex) {
            // Maneja los errores
            System.err.println("Error en la creación de la SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Devuelve una sesión de Hibernate
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    // Cierra Hibernate
    public static void closeSessionFactory() {
        if (sessionFactory != null)
            sessionFactory.close();
    }
}
