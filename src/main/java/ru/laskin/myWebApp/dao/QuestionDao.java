package ru.laskin.myWebApp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.Answer;
import ru.laskin.myWebApp.model.Question;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionDao {
    private final JdbcTemplate jdbcTemplate;
    private final BeanPropertyRowMapper<Question> questionRowMapper = new BeanPropertyRowMapper(Question.class);
    private final BeanPropertyRowMapper<Answer> answerRowMapper = new BeanPropertyRowMapper<>(Answer.class);

    public QuestionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Question getQuestionById(int id){
        return setAnswersToQuestion(jdbcTemplate.query("SELECT * FROM questions WHERE question_id=?", questionRowMapper, id)
                .stream()
                .findAny().orElse(null));
    }

    public List<Question> getAllQuestions(){
        return jdbcTemplate.query("SELECT * FROM questions", questionRowMapper)
                .stream()
                .filter(question -> question.getQuestionId() != 0)
                .map(this::setAnswersToQuestion)
                .collect(Collectors.toList());
    }


    public Question setAnswersToQuestion(Question question){
        if (question != null){
            List<Answer> answers = jdbcTemplate.query("SELECT * FROM answers WHERE question_ID = ?",
                    answerRowMapper, question.getQuestionId());
            question.setAnswers(answers);
        }
        return question;
    }

    public List<Question> getAllQuestionByTestId(int testId){
        return jdbcTemplate.query("SELECT * FROM questions WHERE test_id=?", questionRowMapper, testId);
    }

    public void updateQuestion(Question question){
        jdbcTemplate.update("UPDATE questions SET question_name=? WHERE question_id=?",
                question.getQuestionName(),
                question.getQuestionId()
        );
    }
}
