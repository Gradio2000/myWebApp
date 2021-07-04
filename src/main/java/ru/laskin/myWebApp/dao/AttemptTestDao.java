package ru.laskin.myWebApp.dao;

import org.hibernate.SQLQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.laskin.myWebApp.model.AttemptTest;
import ru.laskin.myWebApp.utils.EntityFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AttemptTestDao {
    private EntityManager em;

    //запись в таблицу с возвратом последнего id
    public Integer saveAttemptTest(AttemptTest attemptTest){
        em = EntityFactoryUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(attemptTest);
        em.getTransaction().commit();
        em.close();
        return attemptTest.getAttemptId();
    }

    public void updateAttemptTest(int id, int time){
        em = EntityFactoryUtil.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("update attempttests set timeAttempt = :time where attemptId = :id")
                .setParameter("time", time)
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public AttemptTest getAttemptById(int id){
        em = EntityFactoryUtil.getEntityManager();
        AttemptTest attemptTest = em.find(AttemptTest.class, id);
        em.close();
        return attemptTest;
    }

    public List<AttemptTest> getAllAttemptByUserId(Integer id) {
        em = EntityFactoryUtil.getEntityManager();
        List<AttemptTest> attemptTestList = em.createQuery("select a from attempttests a where userId = :id")
                .setParameter("id", id)
                .getResultList();
        em.close();
        return attemptTestList;
    }

    public List<AttemptTest> getAllAttempt(){
        em = EntityFactoryUtil.getEntityManager();
        List<AttemptTest> attemptTestList = em.createQuery("select a from attempttests a").getResultList();
        em.close();
        return attemptTestList;
    }

}
