package ru.laskin.myWebApp.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.utils.SessionFactoryUtil;

@Component
public class QuestionHiberDao {
    public void saveQuestion(Question question){
        Session session = SessionFactoryUtil.getSession();
        session.beginTransaction();
        session.save(question);
        session.getTransaction().commit();

    }
}
