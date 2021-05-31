package ru.laskin.myWebApp.dao;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.AttemptTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AttemptTestDao {
    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AttemptTest> attemptTestyRowMapper = new BeanPropertyRowMapper<>(AttemptTest.class);

    public AttemptTestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
    //запись в таблицу с возвратом последнего id
    public Integer saveAttemptTest(AttemptTest attemptTest){
        String sql = "INSERT INTO attempttests (date_time, test_id, user_id, time_attempt) VALUES (?, ?, ?, ?) RETURNING attempt_id";
        Object[] objects = new Object[]{attemptTest.getDateTime(), attemptTest.getTestId(), attemptTest.getUserId(), attemptTest.getTimeOfAttempt()};
        List<AttemptTest> attemptTests = jdbcTemplate.query(sql, objects, attemptTestyRowMapper);
        return attemptTests.get(0).getAttemptId();
    }

    public void updateAttemptTest(AttemptTest attemptTest){
        String sql = "INSERT INTO attempttests (date_time, test_id, user_id, time_attempt) VALUES (?, ?, ?, ?)";
        Object[] objects = new Object[]{attemptTest.getDateTime(), attemptTest.getTestId(), attemptTest.getUserId(), attemptTest.getTimeOfAttempt()};
        jdbcTemplate.update(sql, objects);
    }

    public AttemptTest getAttemptById(int id){
        return jdbcTemplate.query("SELECT * FROM attempttests WHERE attempt_id = ?", attemptTestyRowMapper, id)
                .stream()
                .findAny().orElse(null);
    }

    public List<AttemptTest> getAllAttemptByUserId(Integer id) {
        return new ArrayList<>(jdbcTemplate.query("SELECT * FROM attempttests WHERE user_id = ? ORDER BY date_time DESC", attemptTestyRowMapper, id));
    }

}
