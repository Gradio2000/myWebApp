package ru.laskin.myWebApp.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.utils.EntityFactoryUtil;
import ru.laskin.myWebApp.utils.SessionFactoryUtil;

import javax.persistence.EntityManager;

@Component
public class QuestionHiberDao {
    public EntityManager em;

    public void saveQuestion(Question question){
        em = EntityFactoryUtil.getEntityManagerLocal();
        em.getTransaction().begin();
        em.persist(question);
        em.getTransaction().commit();
    }
}
