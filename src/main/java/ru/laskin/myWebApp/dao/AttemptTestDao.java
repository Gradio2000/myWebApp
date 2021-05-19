package ru.laskin.myWebApp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.AttemptTest;

import java.util.List;

@Component
public class AttemptTestDao {
    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AttemptTest> attemptTestyRowMapper = new BeanPropertyRowMapper<>(AttemptTest.class);

    public AttemptTestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
    //запись в таблицу с возвратом последнего id
    public Integer saveAttemptTest(AttemptTest attemptTest){
        String sql = "INSERT INTO attempttests (date_time, test_id, user_id) VALUES (?, ?, ?) RETURNING attempt_id";
        Object[] objects = new Object[]{attemptTest.getDateTime(), attemptTest.getTestId(), attemptTest.getUserId()};
        List<AttemptTest> attemptTests = jdbcTemplate.query(sql, objects, attemptTestyRowMapper);
        return attemptTests.get(0).getAttemptId();
    }

    public AttemptTest getAttemptById(int id){
        return jdbcTemplate.query("SELECT * FROM attempttests WHERE attempt_id = ?", attemptTestyRowMapper, id)
                .stream()
                .findAny().orElse(null);
    }
}
