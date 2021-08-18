package ru.laskin.myWebApp.service;

import org.codehaus.plexus.util.ExceptionUtils;
import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.controllers.userModule.ResultController;
import ru.laskin.myWebApp.dao.ResultTestDao;
import ru.laskin.myWebApp.dao.TestHiberDao;
import ru.laskin.myWebApp.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

@Service
public class ResultTestService {
    private static final Logger log = Logger.getLogger(ResultController.class.getName());
    private final ResultTestDao resultTestDao;
    private final UserService userService;
    private final AttemptTestService attemptTestService;
    private final TestHiberDao testHiberDao;

    public ResultTestService(ResultTestDao resultTestDao, UserService userService,
                             AttemptTestService attemptTestService, TestHiberDao testHiberDao) {
        this.resultTestDao = resultTestDao;
        this.userService = userService;
        this.attemptTestService = attemptTestService;
        this.testHiberDao = testHiberDao;
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

    //основной метод проверки ответов пользователя и вывода результата теста после его прохождения
    public Statistic mainCheck(Integer attemptId, Test test, Integer timeOfAttempt) throws IOException {
        log.log(Level.INFO, "вход");

        String date = null;
        Set<Integer> falseAnswerSet = null;
        int trueAnswers = 0;
        double result = 0;
        String testResult = null;
        List<Integer> listOfUsersAnswers = null;
        String time = null;
        List<Question> quesList = null;

        try {
            AttemptTest attemptTest = attemptTestService.getAttemptById(attemptId);

            List<ResultTest> resultTestList = getResultTest(attemptId);
            Map<Integer, List<Integer>> mapOfUserAnswers = getMapOfAnswers(resultTestList);

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            date = attemptTest.getDateTime().toLocalDateTime().format(dateTimeFormatter);

            List<Question> questionList = test.getQuestions();
            falseAnswerSet = getFalseAnswerSet(mapOfUserAnswers, questionList);
            trueAnswers = questionList.size() - falseAnswerSet.size();
            result = getResult(trueAnswers, questionList.size());
            testResult = getTestResult(result, test.getCriteria()) ? "Тест пройден" : "Тест не пройден";
            listOfUsersAnswers = getListOfUsersAnswers(mapOfUserAnswers);
            time = attemptTestService.getTime(timeOfAttempt);
            quesList = getRegistredQuestionByattempt(attemptTest.getAttemptId());

            //Ок. А теперь кое-что запишем в бд, чтоб админ мог использовать
            attemptTest.setAmountQues(quesList.size());
            attemptTest.setAmountFalseAnswer(falseAnswerSet.size());
            attemptTest.setAmountTrueAnswer(trueAnswers);
            attemptTest.setResult(result);
            attemptTest.setTestResult(testResult);
            attemptTestService.updateAttempt(attemptTest);


        } catch (Exception e) {
            FileHandler fh = new FileHandler("your_log.txt", false);   // true forces append mode
            SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
            log.addHandler(fh);
            log.log(Level.SEVERE, ExceptionUtils.getStackTrace(e));
        }

        log.info("Выход");
        return new Statistic(date, test, falseAnswerSet, trueAnswers, testResult, listOfUsersAnswers, result, time, quesList);
    }

    //метод для отображения детализации теста пользователя
    public void getStatistic(Integer id, HttpServletRequest request) {
        Date date1 = new Date();
        log.info("Вход " + date1);

        //это пользователь
        User userForStatistic = userService.getUserById(id);

        //это все его попытки сдачи тестов
        List<AttemptTest> attemptTestList = attemptTestService.getAllAttemptByUserId(id);

        /* получим map всех тестов без дубликатов, который проходил наш пользователь */
        Map<Integer, Test> testHashMap = testHiberDao.getAllTestsOfUser(id).stream()
                .distinct()
                .collect(Collectors.toMap(Test::getTestId, test -> test));

        //сюда будем складывать результаты сдачи тестов и отправим в представление
        List<NewStatistic> newStatisticList = new ArrayList<>();

        for (AttemptTest attemptTest : attemptTestList) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            String date = attemptTest.getDateTime().toLocalDateTime().format(dateTimeFormatter);
            Test test = testHashMap.get(attemptTest.getTestId());
            String testName = test.getTestName();
            int amountFalseAnswers = attemptTest.getAmountFalseAnswer();
            int amountTrueAnswer = attemptTest.getAmountTrueAnswer();
            String testResult = attemptTest.getTestResult();
            String time = attemptTestService.getTime(attemptTest.getTimeAttempt());
            int amountQues = attemptTest.getAmountQues();
            double result = attemptTest.getResult();
            double criteria = testHashMap.get(attemptTest.getTestId()).getCriteria();
            int attemptId = attemptTest.getAttemptId();


            newStatisticList.add(new NewStatistic(date, testName, amountFalseAnswers,
                    amountTrueAnswer, testResult, time, amountQues, result, criteria, attemptId,
                    test));
        }

        request.setAttribute("newStatisticList", newStatisticList);
        request.setAttribute("userForStatistic", userForStatistic);

        Date date2 = new Date();
        log.info("Выход. " + date2 + " Затрачено " + (date2.getTime() - date1.getTime())/1000.0 + " сек");
    }

    public Map<Integer, List<Integer>> getMapOfAnswers(List<ResultTest> resultTestList) {
        log.info("Вход");
        Map<Integer, List<Integer>> mapOfUserAnswers = new HashMap<>();
        for (ResultTest resultTest : resultTestList) {
            List<Integer> answersIdList = mapOfUserAnswers.get(resultTest.getQuestionId());
            if (answersIdList == null) answersIdList = new ArrayList<>();
            answersIdList.add(resultTest.getAnswerId());
            mapOfUserAnswers.put(resultTest.getQuestionId(), answersIdList);
        }
        log.info("Выход");
        return mapOfUserAnswers;
    }

    public Set<Integer> getFalseAnswerSet(Map<Integer, List<Integer>> mapOfUserAnswers, List<Question> questionList) {
        log.info("Вход");
        Set<Integer> falseAnswerSet = new HashSet<>();
        if (mapOfUserAnswers.size() != 0) {
            for (Question question : questionList) {
                List<Answer> answerList = question.getAnswers();
                for (Answer answer : answerList) {
                    List<Integer> questionsIdList = mapOfUserAnswers.get(question.getQuestionId());
                    if (questionsIdList == null) {
                        falseAnswerSet.add(question.getQuestionId());
                    } else {
                        if (answer.isRight()) {
                            if (!questionsIdList.contains(answer.getAnswerId())) {
                                falseAnswerSet.add(question.getQuestionId());
                                break;
                            }
                        } else {
                            if (questionsIdList.contains(answer.getAnswerId())) {
                                falseAnswerSet.add(question.getQuestionId());
                            }
                        }
                    }
                }
            }
        } else {
            for (Question question : questionList) {
                falseAnswerSet.add(question.getQuestionId());
                falseAnswerSet.add(question.getQuestionId());
            }
        }
        log.info("Выход");
        return falseAnswerSet;
    }

    public List<Integer> getListOfUsersAnswers(Map<Integer, List<Integer>> mapOfUserAnswers) {
        log.info("Вход");
        List<Integer> listOfUsersAnswers = new ArrayList<>();
        for (Integer key : mapOfUserAnswers.keySet()) {
            listOfUsersAnswers.addAll(mapOfUserAnswers.get(key));
        }
        log.info("Выход");
        return listOfUsersAnswers;
    }

    public Double getResult(int trueCountAnswers, int totalCountAnswers) {
        log.info("Вход");
        BigDecimal bigDecimal1 = new BigDecimal(trueCountAnswers);
        BigDecimal bigDecimal2 = new BigDecimal(totalCountAnswers);
        log.info("Выход");
        return bigDecimal2.compareTo(BigDecimal.ZERO) == 0 ? 0 : bigDecimal1.divide(bigDecimal2, 2, RoundingMode.DOWN).multiply(new BigDecimal("100")).doubleValue();
    }

    private boolean getTestResult(Double result, Double criteria) {
        return result >= criteria;
    }

    public Statistic recordAttemptAndCheckResults(Integer attemptId, HttpServletRequest request, HttpSession session) throws IOException {
        log.log(Level.INFO, "Вход");
        int timeOfAttempt = 0;
        Test test = null;
        try {
            timeOfAttempt = request.getParameter("timeOfAttempt").equals("") ?
                    0 : Integer.parseInt(request.getParameter("timeOfAttempt"));
            test = (Test) session.getAttribute("tests");
            attemptTestService.saveTimeOfAttempt(attemptId, timeOfAttempt);
            log.log(Level.INFO, "выход");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            log.log(Level.SEVERE, e.toString());
        }
        log.info("Выход");
        return mainCheck(attemptId, test, timeOfAttempt);
    }

    public Statistic getDetailResultForAdmin(int attemptId) {

        AttemptTest attemptTest = attemptTestService.getAttemptById(attemptId);
        List<ResultTest> resultTestList = getResultTest(attemptId);
        Map<Integer, List<Integer>> mapOfUserAnswers = getMapOfAnswers(resultTestList);

        List<Integer> listOfUsersAnswers = getListOfUsersAnswers(mapOfUserAnswers);
        List<Question> quesList = getRegistredQuestionByattempt(attemptTest.getAttemptId());

        Set<Integer> falseAnswerSet = getFalseAnswerSet(mapOfUserAnswers, quesList);

        return new Statistic(falseAnswerSet, listOfUsersAnswers, quesList);
    }

    public ResultTestDao getResultTestDao() {
        return resultTestDao;
    }

    public UserService getUserService() {
        return userService;
    }

    public AttemptTestService getAttemptTestService() {
        return attemptTestService;
    }


    public TestHiberDao getTestHiberDao() {
        return testHiberDao;
    }
}
