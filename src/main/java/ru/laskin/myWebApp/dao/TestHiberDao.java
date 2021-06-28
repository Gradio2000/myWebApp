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
        return em.find(Test.class, id);
    }

    public void updateTest(Test test){
        em = EntityFactoryUtil.getEntityManagerLocal();
        Session session = em.unwrap(Session.class);
        session.beginTransaction();
        session.saveOrUpdate(test);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }

    public Integer saveTest(Test test){
        em = EntityFactoryUtil.getEntityManagerLocal();
        em.getTransaction().begin();
        em.persist(test);
        em.getTransaction().commit();
        return test.getTestId();
    }

    public void deleteTestById(int id){
        em = EntityFactoryUtil.getEntityManagerLocal();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from tests where testId =:id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public void deleteGroupTest(Integer id) {
        em = EntityFactoryUtil.getEntityManagerLocal();

    }

    public GroupTest getGroupById(Integer id) {
       em = EntityFactoryUtil.getEntityManagerLocal();
       return em.find(GroupTest.class, id);
    }

    public void addGroup(GroupTest groupTest) {
        em = EntityFactoryUtil.getEntityManagerLocal();
    }

    public void updateAllGroup(List<GroupTest> groupTests) {
        em = EntityFactoryUtil.getEntityManagerLocal();
        for (GroupTest groupTest : groupTests){
            em.merge(groupTest);
        }
    }

    public List<Test> getTestsByGroupId(int groupId) {
        em = EntityFactoryUtil.getEntityManagerLocal();
        Query query = em.createQuery("from tests where groupTest.groupTestId =:id ORDER BY testId");
        query.setParameter ("id", groupId);
        return query.getResultList();
    }
}
