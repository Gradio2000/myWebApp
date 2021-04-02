package ru.laskin.myWebApp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.AttemptTest;

@Component
public class AttemptTestDao {
    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AttemptTest> attemptTestyRowMapper = new BeanPropertyRowMapper<>(AttemptTest.class);

    public AttemptTestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAttemptTest(AttemptTest attemptTest){
        jdbcTemplate.update("INSERT INTO attempttests (date_time, test_id, user_id) VALUES (?, ?, ?)",
                attemptTest.getDateTime(),
                attemptTest.getTestId(),
                attemptTest.getUserId());
    }
}
