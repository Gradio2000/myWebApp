package ru.laskin.myWebApp.dao;

import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.utils.EntityFactoryUtil;

import javax.persistence.EntityManager;

@Component
public class QuestionHiberDao {
    public EntityManager em;

    public void saveQuestion(Question question){
        em = EntityFactoryUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(question);
        em.getTransaction().commit();
        em.close();
    }

    public Question getQuestionById(Integer questionId) {
        em = EntityFactoryUtil.getEntityManager();
        Question question = em.find(Question.class, questionId);
        em.close();
        return question;
    }
}
