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
//       Session session = SessionFactoryUtil.getSession();
//       Query query = session.createQuery("FROM tests ORDER BY test_id");
//       List<Test> testList = query.list();
//       session.getTransaction().commit();
//       session.close();
//       return testList;

        Session session = SessionFactoryUtil.getSession();
        return session.createQuery("FROM tests ORDER BY test_id").list();
    }

    public List<GroupTest> getAllGroup(){
//        Session session = SessionFactoryUtil.getSession();
//        Query query = session.createQuery("FROM group_test ORDER BY grouptest_id");
//        List<GroupTest> groupTestList = query.list();
//        session.getTransaction().commit();
//        session.close();
//        return groupTestList;

        Session session = SessionFactoryUtil.getSession();
        return session.createQuery("FROM group_test ORDER BY grouptest_id").list();
    }

    public Test getTestById(int id){
        Session session = SessionFactoryUtil.getSession();
        Query query = session.createQuery("FROM tests WHERE test_id = :id");
        query.setParameter("id", id);
        Test test = (Test) query.list().stream().findAny().orElse(null);
        session.flush();
        session.close();
        return test;
    }

    public void updateTest(Test test){
        Session session = SessionFactoryUtil.getSession();
        session.beginTransaction();
        session.saveOrUpdate(test);
        session.getTransaction().commit();
        session.flush();
        session.close();

    }

    public void saveTest(Test test){
        Session session = SessionFactoryUtil.getSession();
        session.beginTransaction();
        session.save(test);
        session.getTransaction().commit();
        session.flush();
        session.close();

    }

    public void deleteTestById(int id){
        Session session = SessionFactoryUtil.getSession();
        session.beginTransaction();
        session.delete(getTestById(id));
        session.getTransaction().commit();
        session.flush();
        session.close();

    }

    public void deleteGroupTest(Integer id) {
        Session session = SessionFactoryUtil.getSession();
        session.beginTransaction();
        GroupTest groupTest = getGroupById(id);
        session.delete(groupTest);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }

    public GroupTest getGroupById(Integer id) {
        Session session = SessionFactoryUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM group_test WHERE grouptest_id = :id");
        query.setParameter("id", id);
        GroupTest groupTest = (GroupTest) query.list().stream().findAny().orElse(null);
        session.getTransaction().commit();
        session.flush();
        session.close();

        return groupTest;
    }

    public void addGroup(GroupTest groupTest) {
        Session session = SessionFactoryUtil.getSession();
        session.beginTransaction();
        session.save(groupTest);
        session.getTransaction().commit();
        session.flush();
        session.close();

    }

    public void updateAllGroup(List<GroupTest> groupTests) {
        for (GroupTest groupTest : groupTests){
            Session session = SessionFactoryUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(groupTest);
            session.getTransaction().commit();
            session.flush();
            session.close();
        }
    }

    public List<Test> getTestsByGroupId(int groupId) {
        Session session = SessionFactoryUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM tests WHERE group_id =:id ORDER BY test_id");
        query.setParameter("id", groupId);
        List<Test> testList = query.list();
        session.getTransaction().commit();
        session.flush();
        session.close();

        return testList;
    }
}
