package ru.laskin.myWebApp.dao;

import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.ResultTest;
import ru.laskin.myWebApp.utils.EntityFactoryUtil;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class ResultTestDao {

    EntityManager em;

    public void saveResultTest(ResultTest resultTest){
        em = EntityFactoryUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(resultTest);
        em.getTransaction().commit();
        em.close();
    }

    public List<ResultTest> getAllResultByAttempt(int attemptId){
        em = EntityFactoryUtil.getEntityManager();
        List<ResultTest> resultTestList = em.createQuery("SELECT r FROM resultTest r WHERE attemptId = :attemptId")
                .setParameter("attemptId", attemptId)
                .getResultList();
        em.close();
        return resultTestList;
    }

    public List<Integer> getRegistredQuestionByattempt(int attemptId) {
        em = EntityFactoryUtil.getEntityManager();
        List<Integer> list = em.createQuery("select quesId from registrTest where attemptId = :a")
                .setParameter("a", attemptId)
                .getResultList();
        em.close();
        return list;
    }
}
