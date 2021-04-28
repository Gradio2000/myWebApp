package ru.laskin.myWebApp.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.GroupTest;
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

    public List<GroupTest> getAllGroup(){
        Session session = SessionFactoryUtil.getSession();
        return session.createQuery("FROM group_test").list();
    }

    public Test getTestById(int id){
        Session session = SessionFactoryUtil.getSession();
        Query query = session.createQuery("FROM tests WHERE test_id = :id");
        query.setParameter("id", id);
        Test test = (Test) query.list().stream().findAny().orElse(null);
        session.close();
        return test;
    }

    public void updateTest(Test test){
        Session session = SessionFactoryUtil.getSession();
        session.beginTransaction();
        session.saveOrUpdate(test);
        session.getTransaction().commit();
    }

    public void saveTest(Test test){
        Session session = SessionFactoryUtil.getSession();
        session.beginTransaction();
        session.save(test);
        session.getTransaction().commit();
    }

    public void deleteTestById(int id){
        Session session = SessionFactoryUtil.getSession();
        session.beginTransaction();
        session.delete(getTestById(id));
        session.getTransaction().commit();
    }
}
