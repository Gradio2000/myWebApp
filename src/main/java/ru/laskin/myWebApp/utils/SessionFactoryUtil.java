package ru.laskin.myWebApp.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import ru.laskin.myWebApp.model.*;

import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class SessionFactoryUtil {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();

            properties.put(Environment.URL, "jdbc:postgresql://localhost:5433/postgres");
            properties.put(Environment.USER, "user");
            properties.put(Environment.PASS, "password");

//            properties.put(Environment.URL, "jdbc:postgres://pohrbepygisbbd:892707cfa40e14b18b0a66020cf206a96796a484d0d8abb5bdd99e8c7f3c509a@ec2-34-254-69-72.eu-west-1.compute.amazonaws.com:5432/d677pta579u9mh");
//            properties.put(Environment.USER, "pohrbepygisbbd");
//            properties.put(Environment.PASS, "892707cfa40e14b18b0a66020cf206a96796a484d0d8abb5bdd99e8c7f3c509a");

//            properties.put(Environment.URL, "${JDBC_DATABASE_URL}");
//            properties.put(Environment.USER, "${SPRING_DATASOURCE_USERNAME}");
//            properties.put(Environment.PASS, "${SPRING_DATASOURCE_PASSWORD}");

            properties.put(Environment.DRIVER, "org.postgresql.Driver");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL82Dialect");
            properties.put(Environment.HBM2DDL_AUTO, "update");
            ourSessionFactory = configuration
                    .addProperties(properties)
                    .addAnnotatedClass(Test.class)
                    .addAnnotatedClass(Question.class)
                    .addAnnotatedClass(Answer.class)
                    .addAnnotatedClass(GroupTest.class)
                    .addAnnotatedClass(AttemptTest.class)
                    .addAnnotatedClass(Position.class)
                    .addAnnotatedClass(ResultTest.class)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("Ищи проблему в SessionFactoryUtil");
        }

//        private static final SessionFactory ourSessionFactory;
//
//    static {
//        try {
//            Configuration configuration = new Configuration();
//
//            Map<String, String> jdbcUrlSettings = new HashMap<>();
//            String jdbcDbUrl = System.getenv("JDBC_DATABASE_URL");
//            if (null != jdbcDbUrl) {
//                jdbcUrlSettings.put("hibernate.connection.url", System.getenv("JDBC_DATABASE_URL"));
//            }
//
//            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().
//                    configure("hibernate.cfg.xml").
//                    applySettings(jdbcUrlSettings).
//                    build();
//
//            ourSessionFactory = configuration.buildSessionFactory(registry);
//
//    } catch (Throwable ex) {
//            throw new ExceptionInInitializerError("Ищи проблему в SessionFactoryUtil");
//        }


    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }
}