package ru.laskin.myWebApp.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.GroupTest;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.utils.EntityFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
public class TestHiberDao {

    public EntityManager em;

    public List<Test> getAllTests(){
        em = EntityFactoryUtil.getEntityManager();
        List<Test> testList = em.createQuery("select t from tests t where deleted=false order by testId", Test.class).getResultList();
        em.close();
        return testList;
    }


    public List<GroupTest> getAllGroup(){
        em = EntityFactoryUtil.getEntityManager();
        List<GroupTest> groupTestList = em.createQuery("select g from group_test g order by groupTestId", GroupTest.class).getResultList();
        em.close();
        return groupTestList;
    }

    public Test getTestById(int id){
        em = EntityFactoryUtil.getEntityManager();
        Test test = em.find(Test.class, id);
        em.close();
        return test;
    }

    public void updateTest(Test test){
        em = EntityFactoryUtil.getEntityManager();
        Session session = em.unwrap(Session.class);
        session.beginTransaction();
        session.saveOrUpdate(test);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }

    public Integer saveTest(Test test){
        em = EntityFactoryUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(test);
        em.getTransaction().commit();
        em.close();
        return test.getTestId();
    }


    public void deleteTestById(int id){
        em = EntityFactoryUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createNativeQuery("UPDATE postgres.public.tests SET group_id=null, deleted=true WHERE test_id = ?")
                .setParameter(1, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void deleteGroupTest(Integer id) {
        em = EntityFactoryUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from group_test where groupTestId =:id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public GroupTest getGroupById(Integer id) {
       em = EntityFactoryUtil.getEntityManager();
       GroupTest groupTest = em.find(GroupTest.class, id);
       em.close();
       return groupTest;
    }

    public void addGroup(GroupTest groupTest) {
        em = EntityFactoryUtil.getEntityManager();
        Session session = em.unwrap(Session.class);
        session.beginTransaction();
        session.save(groupTest);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }

    public void updateAllGroup(List<GroupTest> groupTests) {
        em = EntityFactoryUtil.getEntityManager();
        Session session = em.unwrap(Session.class);
        for (GroupTest groupTest : groupTests){
            session.getTransaction().begin();
            session.merge(groupTest);
            session.getTransaction().commit();
        }
        session.close();
    }

    public List<Test> getTestsByGroupId(int groupId) {
        em = EntityFactoryUtil.getEntityManager();
        Query query = em.createQuery("from tests where groupTest.groupTestId =:id ORDER BY testId");
        query.setParameter ("id", groupId);
        List<Test> testList = query.getResultList();
        em.close();
        return testList;
    }

    public void registerTest(int attemptId, int questionId) {
        em = EntityFactoryUtil.getEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("insert into registr_test (attempt_id, ques_id) values (?, ?)")
                .setParameter(1, attemptId)
                .setParameter(2, questionId)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
