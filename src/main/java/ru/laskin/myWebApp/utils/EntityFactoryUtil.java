package ru.laskin.myWebApp.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityFactoryUtil {
    private static EntityManagerFactory entityManagerFactoryLocal;
    private static EntityManagerFactory entityManagerFactoryHeroku;

    static {
        entityManagerFactoryLocal = Persistence.createEntityManagerFactory("localConnect");
        entityManagerFactoryHeroku = Persistence.createEntityManagerFactory("herokuConnect");
    }

    public static EntityManager getEntityManager(){
//        return entityManagerFactoryLocal.createEntityManager();
        return entityManagerFactoryHeroku.createEntityManager();
    }
}
