package ru.laskin.myWebApp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.ResultTest;

import java.util.List;

@Component
public class ResultTestDao {
    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<ResultTest> resultTestRowMapper = new BeanPropertyRowMapper(ResultTest.class);

    public ResultTestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveResultTest(ResultTest resultTest){
        jdbcTemplate.update("INSERT INTO resulttests (attempt_id, question_id, answer_id) VALUES (?,?,?)",
                resultTest.getAttemptId(),
                resultTest.getQuestionId(),
                resultTest.getAnswerId()
        );
    }

    public List<ResultTest> getAllResultByAttempt(int attemptId){
        return jdbcTemplate.query("SELECT * FROM resulttests WHERE attempt_id = ?", resultTestRowMapper, attemptId);
    }
}
