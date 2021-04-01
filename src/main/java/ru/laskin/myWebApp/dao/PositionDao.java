package ru.laskin.myWebApp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.Position;

import java.util.List;

@Component
public class PositionDao {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<Position> positionRowMapper = new BeanPropertyRowMapper(Position.class);

    public PositionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Position> getAllPosition(){
        return jdbcTemplate.query("SELECT * FROM positions", positionRowMapper);
    }
}
