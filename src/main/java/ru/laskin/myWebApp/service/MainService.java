package ru.laskin.myWebApp.service;

import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.*;
import ru.laskin.myWebApp.model.Answer;
import ru.laskin.myWebApp.model.AttemptTest;
import ru.laskin.myWebApp.model.ResultTest;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.utils.TimeUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {

    private ResultTestDao resultTestDao;
    private TestDao testDao;
    private UserDao userDao;
    private AnswersDao answersDao;
    private TimeUtils timeUtils;

    public MainService(ResultTestDao resultTestDao, TestDao testDao,
                       UserDao userDao, AnswersDao answersDao, TimeUtils timeUtils) {
        this.resultTestDao = resultTestDao;
        this.testDao = testDao;
        this.userDao = userDao;
        this.answersDao = answersDao;
        this.timeUtils = timeUtils;
    }

    //Этот метод формирует список из БД для отображения результата теста для пользователя
    public List<String> getResult(int attemptTestId, Timestamp timestamp, int testId, int userId){
        User user = userDao.getUserById(userId);
        String dateAndTime = timeUtils.timestampToDate(timestamp);
        String userName = user.getName();
        String userPosition = user.getPosition();
        String testName = testDao.getTestById(testId).getTestName();

        List<ResultTest> resultTests = resultTestDao.getAllResultByAttempt(attemptTestId);
        String countQuestions = String.valueOf(resultTests.size());

        List<Integer> answerTrueList = answersDao.getAllAnswers()
                .stream()
                .map(Answer::getAnswerId)
                .collect(Collectors.toList());

        int count = 0;
        for (int i = 0; i < resultTests.size(); i++) {
            if (answerTrueList.contains(resultTests.get(i).getAnswerId())){
                count++;
            }
        }
        String answerTrueCount = String.valueOf(count);

        List<String> stringList = new ArrayList<>();
        stringList.add(dateAndTime);
        stringList.add(userName);
        stringList.add(userPosition);
        stringList.add(testName);
        stringList.add(countQuestions);
        stringList.add(answerTrueCount);

        return stringList;
    }
}
