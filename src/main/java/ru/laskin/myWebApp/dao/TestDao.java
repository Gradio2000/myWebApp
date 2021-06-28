package ru.laskin.myWebApp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.Answer;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.model.Test;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestDao {

    private JdbcTemplate jdbcTemplate;

    private BeanPropertyRowMapper<Test> testRowMapper = new BeanPropertyRowMapper<>(Test.class);

    public TestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Test> getAllTests(){
        return jdbcTemplate.query("SELECT * FROM tests", testRowMapper);
    }


}
