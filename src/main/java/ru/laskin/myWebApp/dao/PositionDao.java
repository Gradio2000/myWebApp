package ru.laskin.myWebApp.dao;

import org.hibernate.Session;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.Position;
import ru.laskin.myWebApp.utils.EntityFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

@Component
public class PositionDao {

   private EntityManager em;

    public List<Position> getAllPosition(){
        em = EntityFactoryUtil.getEntityManager();
        List<Position> positionList = em.createQuery("select p from positions p order by idPosition").getResultList();
        em.close();
        return positionList;
    }

    public Position getPositionById(Integer pos_id) {
        em = EntityFactoryUtil.getEntityManager();
        Position position = em.find(Position.class, pos_id);
        em.close();
        return position;
    }

    public void updatePosition(Position position) {
        em = EntityFactoryUtil.getEntityManager();
        Session session = em.unwrap(Session.class);
        session.beginTransaction();
        session.saveOrUpdate(position);
        session.getTransaction().commit();
        em.clear();
        session.flush();
        session.close();
    }

    public void deletePosition(int id) {
        em = EntityFactoryUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from positions where idPosition=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void addPosition(Position position) {
        em = EntityFactoryUtil.getEntityManager();
        Session session = em.unwrap(Session.class);
        session.beginTransaction();
        session.save(position);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
}
