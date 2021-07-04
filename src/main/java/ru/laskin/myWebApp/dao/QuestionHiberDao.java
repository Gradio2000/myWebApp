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
    }

    public Question getQuestionById(Integer questionId) {
        em = EntityFactoryUtil.getEntityManager();
        return em.find(Question.class, questionId);
    }
}
