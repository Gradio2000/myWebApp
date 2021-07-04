package ru.laskin.myWebApp.dao;

import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.ResultTest;
import ru.laskin.myWebApp.utils.EntityFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
public class ResultTestDao {

    EntityManager em;

    public void saveResultTest(ResultTest resultTest){
        em = EntityFactoryUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(resultTest);
        em.getTransaction().commit();
    }

    public List<ResultTest> getAllResultByAttempt(int attemptId){
        em = EntityFactoryUtil.getEntityManager();
        return em.createQuery("SELECT r FROM resultTest r WHERE attemptId = :attemptId")
                .setParameter("attemptId", attemptId)
                .getResultList();
    }

    public List<Integer> getQuestionIdByAttemptId(int attemptId) {
        em = EntityFactoryUtil.getEntityManager();
        return em.createQuery("select questionId from resultTest where attemptId = :attemptId")
                .setParameter("attemptId", attemptId)
                .getResultList();
    }
}
