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
        return em.createQuery("select t from tests t order by testId", Test.class).getResultList();
    }


    public List<GroupTest> getAllGroup(){
        em = EntityFactoryUtil.getEntityManager();
        return em.createQuery("select t from group_test t order by groupTestId", GroupTest.class).getResultList();
    }

    public Test getTestById(int id){
        em = EntityFactoryUtil.getEntityManager();
        return em.find(Test.class, id);
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
        return test.getTestId();
    }

    public void deleteTestById(int id){
        em = EntityFactoryUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from tests where testId =:id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public void deleteGroupTest(Integer id) {
        em = EntityFactoryUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from group_test where groupTestId =:id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public GroupTest getGroupById(Integer id) {
       em = EntityFactoryUtil.getEntityManager();
       return em.find(GroupTest.class, id);
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
        return query.getResultList();
    }
}
