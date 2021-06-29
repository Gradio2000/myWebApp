package ru.laskin.myWebApp.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class EntityFactoryUtil {
//    private static EntityManagerFactory entityManagerFactoryLocal;
    private static EntityManagerFactory entityManagerFactoryHeroku;

    static {
//        entityManagerFactoryLocal = Persistence.createEntityManagerFactory("localConnect");
        entityManagerFactoryHeroku = Persistence.createEntityManagerFactory("herokuConnect");
    }

    public static EntityManager getEntityManager(){
//        return entityManagerFactoryLocal.createEntityManager();
        return entityManagerFactoryHeroku.createEntityManager();
    }
}
