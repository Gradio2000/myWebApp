package ru.laskin.myWebApp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.Position;
import ru.laskin.myWebApp.utils.EntityFactoryUtil;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class PositionDao {

   private EntityManager em;

    public List<Position> getAllPosition(){
        em = EntityFactoryUtil.getEntityManager();
        List<Position> positionList = em.createQuery("select p from positions p").getResultList();
        em.close();
        return positionList;
    }

    public Position getPositionById(Integer pos_id) {
        em = EntityFactoryUtil.getEntityManager();
        Position position = em.find(Position.class, pos_id);
        em.close();
        return position;
    }
}
