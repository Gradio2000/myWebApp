package ru.laskin.myWebApp.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.GroupTest;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.utils.EntityFactoryUtil;
import ru.laskin.myWebApp.utils.SessionFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class TestHiberDao {

    public EntityManager em;


    public List getAllTests(){
        em = EntityFactoryUtil.getEntityManagerLocal();
        return em.createQuery("select t from tests t order by testId", Test.class).getResultList();
    }


    public List<GroupTest> getAllGroup(){
        em = EntityFactoryUtil.getEntityManagerLocal();
        return em.createQuery("select t from group_test t order by groupTestId", GroupTest.class).getResultList();
    }

    public Test getTestById(int id){
        em = EntityFactoryUtil.getEntityManagerLocal();
        TypedQuery<Test> query = em.createQuery("select t from tests t where t.testId=:id", Test.class).setParameter("id", id);
        return query.getResultList().get(0);
    }

    public void updateTest(Test test){
        Session session = SessionFactoryUtil.getSession();
        session.beginTransaction();
        session.saveOrUpdate(test);
        session.getTransaction().commit();
        session.flush();
        session.close();

    }

    public Integer saveTest(Test test){
        Session session = SessionFactoryUtil.getSession();
        session.beginTransaction();
        session.save(test);
        session.getTransaction().commit();
        session.flush();
        session.close();
        return test.getTestId();
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
