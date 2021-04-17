package ru.laskin.myWebApp.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.utils.SessionFactoryUtil;

import java.util.List;

@Component
public class TestHiberDao {

    public List<Test> getAllTests(){
       Session session = SessionFactoryUtil.getSession();
       return session.createQuery("FROM tests ORDER BY test_id").list();
    }

    public Test getTestById(int id){
        Session session = SessionFactoryUtil.getSession();
        Query query = session.createQuery("FROM tests WHERE test_id = :id");
        query.setParameter("id", id);
        return (Test) query.list().stream().findAny().orElse(null);
    }
}
