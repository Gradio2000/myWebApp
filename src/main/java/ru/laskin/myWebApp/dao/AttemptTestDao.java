package ru.laskin.myWebApp.dao;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.AttemptTest;
import ru.laskin.myWebApp.utils.EntityFactoryUtil;

import javax.persistence.EntityManager;
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
        return attemptTest.getAttemptId();
    }

    public void updateAttemptTest(int id, int time){
        em = EntityFactoryUtil.getEntityManager();
        em.createNativeQuery("UPDATE attempttests SET time_attempt = ? WHERE attempt_id = ?")
                .setParameter(1, id)
                .setParameter(2, time)
                .executeUpdate();
    }

    public AttemptTest getAttemptById(int id){
        em = EntityFactoryUtil.getEntityManager();
        return em.find(AttemptTest.class, id);
    }

    public List<AttemptTest> getAllAttemptByUserId(Integer id) {
        em = EntityFactoryUtil.getEntityManager();
        return em.createQuery("select a from attempttests a where userId =: id")
                .setParameter("id", id)
                .getResultList();
    }

    public List<AttemptTest> getAllAttempt(){
        em = EntityFactoryUtil.getEntityManager();
        return em.createQuery("select a from attempttests a").getResultList();
    }

}
