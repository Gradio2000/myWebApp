package ru.laskin.myWebApp.service;

import org.codehaus.plexus.util.ExceptionUtils;
import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.controllers.ResultController;
import ru.laskin.myWebApp.dao.QuestionHiberDao;
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
public class TestService {
    private static final Logger logger = Logger.getLogger(ResultController.class.getName());

    private final TestHiberDao testHiberDao;
    private final QuestionHiberDao questionHiberDao;
    private final UserService userService;
    private final AttemptTestService attemptTestService;
    private final ResultTestService resultTestService;


    public TestService(TestHiberDao testHiberDao, QuestionHiberDao questionHiberDao,
                       UserService userService, AttemptTestService attemptTestService,
                       ResultTestService resultTestService) {
        this.testHiberDao = testHiberDao;
        this.questionHiberDao = questionHiberDao;
        this.userService = userService;
        this.attemptTestService = attemptTestService;
        this.resultTestService = resultTestService;
    }

    public List<Test> getAllTests() {
        return testHiberDao.getAllTests();
    }

    public Test getTestById(int testId) {
        return testHiberDao.getTestById(testId);
    }

    public void updateTest(Test test, HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] answersName = parameterMap.get("answer");
        String[] answerId = parameterMap.get("answerId");
        String[] isRight = parameterMap.get("isRight");
        String[] questionName = parameterMap.get("question");
        String[] questionId = parameterMap.get("questionId");
        String[] quesAnsId = parameterMap.get("quesAnsId");
        String[] newAnswer = parameterMap.get("newAnswer");
        String[] quesIdForNewAnswer = parameterMap.get("quesIdForNewAnswer");
        String[] groupId = parameterMap.get("groupId");
        String[] isRightForNewAnswer = parameterMap.get("isRightForNewAnswer");

        List<Question> questionList = new ArrayList<>();
        for (int i = 0; i < questionName.length; i++) {
            Question question = new Question();
//            question.setQuestionId(Integer.parseInt(questionId[i]));
            question.setQuestionName(questionName[i]);

            List<Answer> answerList = new ArrayList<>();
            if (answersName != null) {
                for (int j = 0; j < answersName.length; j++) {
                    Answer answer = new Answer();
                    if (j < answerId.length) {
                        answer.setAnswerId(Integer.parseInt(answerId[j]));
                    }
                    answer.setAnswerName(answersName[j]);
                    if (isRight != null && Arrays.asList(isRight).contains(String.valueOf(j))) {
                        answer.setRight(true);
                    }
                    if (j < quesAnsId.length && quesAnsId[j].equals(questionId[i])) {
                        answerList.add(answer);
                    }
                }
            }

            if (newAnswer != null) {
                for (int j = 0; j < newAnswer.length; j++) {
                    Answer answer = new Answer();
                    answer.setAnswerName(newAnswer[j]);
                    if (isRightForNewAnswer != null && Arrays.asList(isRightForNewAnswer).contains(String.valueOf(j))) {
                        answer.setRight(true);
                    }
                    if (questionId[i].equals(quesIdForNewAnswer[j])) {
                        answerList.add(answer);
                    }
                }
            }

            question.setAnswers(answerList);
            questionList.add(question);
        }

        test.setGroupTest(getGroupTestById(Integer.parseInt(groupId[0])));
        test.setQuestions(questionList);

        if (test.getCriteria() == null) {
            test.setCriteria(0.0);
        }
        if (test.getTime() == null) {
            test.setTime(0.0);
        }

        testHiberDao.updateTest(test);
    }

    public Integer saveTest(Test test) {
        if (test.getCriteria() == null) {
            test.setCriteria(0.0);
        }
        if (test.getTime() == null) {
            test.setTime(0.0);
        }
        return testHiberDao.saveTest(test);
    }

    public void deleteTestById(int id) {
        testHiberDao.deleteTestById(id);
    }

    public List<GroupTest> getAllGroupTest() {
        return testHiberDao.getAllGroup();
    }

    public void deleteGroupTest(Integer id) {
        testHiberDao.deleteGroupTest(id);
    }

    public void addGroup(GroupTest groupTest) {
        testHiberDao.addGroup(groupTest);
    }

    public void updateAllGroup(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] id = parameterMap.get("grouptestId");
        String[] name = parameterMap.get("name");

        List<GroupTest> groupTests = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            GroupTest groupTest = new GroupTest();
            groupTest.setGroupTestId(Integer.parseInt(id[i]));
            groupTest.setName(name[i]);
            groupTest.setTestList(getTestsByGroupId(Integer.parseInt(id[i])));
            groupTests.add(groupTest);
        }
        testHiberDao.updateAllGroup(groupTests);
    }

    public GroupTest getGroupTestById(int groupId) {
        return testHiberDao.getGroupById(groupId);
    }

    public List<Test> getTestsByGroupId(int groupId) {
        return testHiberDao.getTestsByGroupId(groupId);
    }

    public void saveQuestion(Question question, String[] answersName, String[] rightAnswer) {
        //собираем list answers
        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < answersName.length; i++) {
            Answer answer = new Answer();
            answer.setAnswerName(answersName[i]);

            //в цикле в answer добавляется отметка isRight
            for (String s : rightAnswer) {
                int count = Integer.parseInt(s);
                if (i == count) {
                    answer.setRight(true);
                }
            }
            answers.add(answer);
        }

        question.setAnswers(answers);
        questionHiberDao.saveQuestion(question);
    }

    //основной метод проверки ответов пользователя и вывода результата теста
    public Statistic mainCheck(Integer attemptId, Test test, Integer timeOfAttempt) throws IOException {
        logger.log(Level.INFO, "вход");

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

            List<ResultTest> resultTestList = resultTestService.getResultTest(attemptId);
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
            quesList = resultTestService.getRegistredQuestionByattempt(attemptTest.getAttemptId());
            throw new ArithmeticException(date);
//            logger.log(Level.INFO, "выход");
        } catch (Exception e) {
            FileHandler fh = new FileHandler("your_log.txt", false);   // true forces append mode
            SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
            logger.addHandler(fh);
            logger.log(Level.SEVERE, ExceptionUtils.getStackTrace(e));
        }
        return new Statistic(date, test, falseAnswerSet, trueAnswers, testResult, listOfUsersAnswers, result, time, quesList);
    }

    //метод для отображения детализации теста пользователя
    public void getStatistic(Integer id, HttpSession session) {
        User userforStatisic = userService.getUserById(id);
        List<AttemptTest> attemptTestList = attemptTestService.getAllAttemptByUserId(id);

        List<Statistic> statisticList = new ArrayList<>();
        List<Integer> listOfUsersAnswers;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        for (AttemptTest attemptTest : attemptTestList) {
            String date = attemptTest.getDateTime().toLocalDateTime().format(dateTimeFormatter);
            Test test = getTestById(attemptTest.getTestId());

            //Это список заданных вопросов для попытки
            List<Question> quesList = resultTestService.getRegistredQuestionByattempt(attemptTest.getAttemptId());

            List<ResultTest> resultTestList = resultTestService.getResultTest(attemptTest.getAttemptId());
            Map<Integer, List<Integer>> mapOfUserAnswers = getMapOfAnswers(resultTestList);
            Set<Integer> falseAnswerSet = getFalseAnswerSet(mapOfUserAnswers, quesList);
            listOfUsersAnswers = getListOfUsersAnswers(mapOfUserAnswers);
            int trueAnswer = quesList.size() - falseAnswerSet.size();
            String testResult;
            double result = 0;
            if (test.getCriteria() != null) {
                result = getResult(trueAnswer, quesList.size());
                testResult = getTestResult(result, test.getCriteria()) ?
                        "Тест пройден" : "Тест не пройден";
            } else testResult = "Не задан критерий в настройках теста";

            String time = attemptTestService.getTime(attemptTest.getTimeAttempt());

            statisticList.add(new Statistic(date, test, falseAnswerSet, trueAnswer,
                    testResult, listOfUsersAnswers, result, time, quesList));
        }

        session.setAttribute("userForStatistic", userforStatisic);
        session.setAttribute("statisticList", statisticList);
    }

    public Map<Integer, List<Integer>> getMapOfAnswers(List<ResultTest> resultTestList) {
        Map<Integer, List<Integer>> mapOfUserAnswers = new HashMap<>();
        for (ResultTest resultTest : resultTestList) {
            List<Integer> answersIdList = mapOfUserAnswers.get(resultTest.getQuestionId());
            if (answersIdList == null) answersIdList = new ArrayList<>();
            answersIdList.add(resultTest.getAnswerId());
            mapOfUserAnswers.put(resultTest.getQuestionId(), answersIdList);
        }
        return mapOfUserAnswers;
    }

    public Set<Integer> getFalseAnswerSet(Map<Integer, List<Integer>> mapOfUserAnswers, List<Question> questionList) {
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
        return falseAnswerSet;
    }

    public List<Integer> getListOfUsersAnswers(Map<Integer, List<Integer>> mapOfUserAnswers) {
        List<Integer> listOfUsersAnswers = new ArrayList<>();
        for (Integer key : mapOfUserAnswers.keySet()) {
            listOfUsersAnswers.addAll(mapOfUserAnswers.get(key));
        }
        return listOfUsersAnswers;
    }

    public Double getResult(int trueCountAnswers, int totalCountAnswers) {
        BigDecimal bigDecimal1 = new BigDecimal(trueCountAnswers);
        BigDecimal bigDecimal2 = new BigDecimal(totalCountAnswers);
        return bigDecimal2.compareTo(BigDecimal.ZERO) == 0 ? 0 : bigDecimal1.divide(bigDecimal2, 2, RoundingMode.DOWN).multiply(new BigDecimal("100")).doubleValue();
    }

    private boolean getTestResult(Double result, Double criteria) {
        return result >= criteria;
    }

    public Test getShuffleTest(Test test) {
        List<Question> questionList = test.getQuestions();
        Collections.shuffle(questionList);
        test.setQuestions(questionList.stream().limit(test.getQuesAmount()).collect(Collectors.toList()));
        return test;
    }

    public void updateTest(Test test) {
        testHiberDao.updateTest(test);
    }

    public void registerTest(int attemptId, Test test) {
        for (Question question : test.getQuestions()) {
            testHiberDao.registerTest(attemptId, question.getQuestionId());
        }

    }

    public Statistic recordAttemptAndCheckResults(Integer attemptId, HttpServletRequest request, HttpSession session) throws IOException {
        logger.log(Level.INFO, "вход");
        int timeOfAttempt = 0;
        Test test = null;
        try {
            timeOfAttempt = request.getParameter("timeOfAttempt").equals("") ?
                    0 : Integer.parseInt(request.getParameter("timeOfAttempt"));
            test = (Test) session.getAttribute("tests");
            attemptTestService.saveTimeOfAttempt(attemptId, timeOfAttempt);
            logger.log(Level.INFO, "выход");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, e.toString());
        }
        return mainCheck(attemptId, test, timeOfAttempt);
    }
}
