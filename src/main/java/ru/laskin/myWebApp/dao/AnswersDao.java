package ru.laskin.myWebApp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.Answer;

import java.util.List;

@Component
public class AnswersDao {
    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<Answer> answerRowMapper = new BeanPropertyRowMapper<>(Answer.class);

    public AnswersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Answer> getAllAnswers(){
        return jdbcTemplate.query("SELECT * FROM answers WHERE is_right = true", answerRowMapper);
    }
}
