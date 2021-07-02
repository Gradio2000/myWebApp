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
        em.createNativeQuery("INSERT INTO resulttests (attempt_id, question_id, answer_id) VALUES (?,?,?)")
                .setParameter(1, resultTest.getAttemptId())
                .setParameter(2, resultTest.getQuestionId())
                .setParameter(3, resultTest.getAnswerId());
    }

    public List<ResultTest> getAllResultByAttempt(int attemptId){
        em = EntityFactoryUtil.getEntityManager();
        return em.createQuery("SELECT r FROM resultTest r WHERE attempttests.attemptId = :attemptId")
                .setParameter("attemptId", attemptId)
                .getResultList();
    }
}
