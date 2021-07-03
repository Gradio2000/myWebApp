package ru.laskin.myWebApp.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.utils.EntityFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserHiberDao {

    public EntityManager em;

    public List<User> getAllUsers () {
        return em.createQuery("select u from users u group by userId", User.class).getResultList();
    }

    public void saveUser(User user) {
        em = EntityFactoryUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public User getUserById(int id) {
        em = EntityFactoryUtil.getEntityManager();
        return em.find(User.class, id);
    }

    public User getUserByLogin(String login){
        em = EntityFactoryUtil.getEntityManager();
        return (User) em.createQuery("select u from users u where login =:login").setParameter("login", login)
                .getResultList()
                .stream()
                .findAny().orElse(null);
    }

    public void deleteUser(int id){
        em = EntityFactoryUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from users where userId =:id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public void updateUser(User user) {
        em = EntityFactoryUtil.getEntityManager();
        Session session = em.unwrap(Session.class);
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }

    public void changePassword(int id, String pass) {
        em = EntityFactoryUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("update users u set u.password = :pass where userId = :id");
        query.setParameter("pass", pass);
        query.setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public User getUserByEmail(String email) {
        em = EntityFactoryUtil.getEntityManager();
        return (User) em.createQuery("select u from users u where email = :email")
                .setParameter("email", email)
                .getSingleResult();
    }
}
