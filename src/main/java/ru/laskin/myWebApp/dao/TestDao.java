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

    private BeanPropertyRowMapper<Question> questionRowMapper = new BeanPropertyRowMapper(Question.class);
    private BeanPropertyRowMapper<Answer> answerRowMapper = new BeanPropertyRowMapper<>(Answer.class);
    private BeanPropertyRowMapper<Test> testRowMapper = new BeanPropertyRowMapper<>(Test.class);

    public TestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Test> getAllTests(){
        return jdbcTemplate.query("SELECT * FROM tests", testRowMapper);
    }

    public List<Question> getAllQuestions(){
        return jdbcTemplate.query("SELECT * FROM questions", questionRowMapper)
                .stream()
                .map(this::setAnswersToQuestion)
                .collect(Collectors.toList());
    }

    public Question getQuestionById(int id){
        return setAnswersToQuestion(jdbcTemplate.query("SELECT * FROM questions WHERE question_id=?", questionRowMapper, id)
                .stream()
                .findAny().orElse(null));
    }


    public Question setAnswersToQuestion(Question question){
        if (question != null){
            List<Answer> answers = jdbcTemplate.query("SELECT * FROM answers WHERE question_ID = ?",
                    answerRowMapper, question.getQuestionId());
            question.setAnswers(answers);
        }
       return question;
    }
}
