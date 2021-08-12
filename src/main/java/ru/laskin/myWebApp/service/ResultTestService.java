package ru.laskin.myWebApp.service;

import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.QuestionHiberDao;
import ru.laskin.myWebApp.dao.ResultTestDao;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.model.ResultTest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class ResultTestService {

    private ResultTestDao resultTestDao;
    private QuestionHiberDao questionHiberDao;

    public ResultTestService(ResultTestDao resultTestDao, QuestionHiberDao questionHiberDao) {
        this.resultTestDao = resultTestDao;
        this.questionHiberDao = questionHiberDao;
    }

    public List<ResultTest> getResultTest(Integer attemptId) {
        return resultTestDao.getAllResultByAttempt(attemptId);
    }

    public void saveResultTest(HttpServletRequest request, Integer questionId, Integer attemptId) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] checks = parameterMap.get("check");

        //Создаем ResultTestы
        if (checks != null){
            for (int i = 0; i < checks.length; i++) {
                ResultTest resultTest = new ResultTest(attemptId, questionId, Integer.parseInt(checks[i]));
                resultTestDao.saveResultTest(resultTest);
            }
        }
    }

    public List<Question> getRegistredQuestionByattempt(int attemptId) {
        return resultTestDao.getRegistredQuestionByattempt(attemptId);
    }
}
