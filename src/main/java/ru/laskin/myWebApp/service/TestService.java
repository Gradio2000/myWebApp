package ru.laskin.myWebApp.service;

import org.codehaus.plexus.util.ExceptionUtils;
import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.controllers.userModule.ResultController;
import ru.laskin.myWebApp.dao.CompanyDao;
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
    private static final Logger log = Logger.getLogger(ResultController.class.getName());

    private final TestHiberDao testHiberDao;
    private final QuestionHiberDao questionHiberDao;
    private final UserService userService;
    private final AttemptTestService attemptTestService;
    private final ResultTestService resultTestService;
    private CompanyDao companyDao;


    public TestService(TestHiberDao testHiberDao, QuestionHiberDao questionHiberDao,
                       UserService userService, AttemptTestService attemptTestService,
                       ResultTestService resultTestService, CompanyDao companyDao) {
        this.testHiberDao = testHiberDao;
        this.questionHiberDao = questionHiberDao;
        this.userService = userService;
        this.attemptTestService = attemptTestService;
        this.resultTestService = resultTestService;
        this.companyDao = companyDao;
    }

    public List<Test> getAllTests() {
        log.info("Вход и выход");
        return testHiberDao.getAllTests();
    }

    public Test getTestById(int testId) {
        log.info("Вход и выход");
        return testHiberDao.getTestById(testId);
    }

    /*  Этот метод собирает ListQuestion и ListAnswers для Test
    и отправляет Test в БД для обновления                            */
    public void updateTest(Test test, HttpServletRequest request, HttpSession session) {
        log.info("Вход");
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
                question.setQuestionId(Integer.parseInt(questionId[i]));
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

                //Проверяем, есть ли изменение в вопросах теста.
                //Если есть - обнуляем id и при обновлении в БД у него будет новый id
                List<Question> etalonQuestions = (List<Question>) session.getAttribute("questions");
                Question etalonQues = etalonQuestions.stream()
                        .filter(s -> s.getQuestionId() == question.getQuestionId())
                        .findAny().orElse(null);
                if (!question.equals(etalonQues)){
                    question.setQuestionId(0);
                }

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
            log.info("Выход");
    }

    public Integer saveTest(Test test) {
        log.info("Вход");
        if (test.getCriteria() == null) {
            test.setCriteria(0.0);
        }
        if (test.getTime() == null) {
            test.setTime(0.0);
        }
        log.info("Выход");
        return testHiberDao.saveTest(test);
    }

    public void deleteTestById(int id) {
        log.info("Вход и выход");
        testHiberDao.deleteTestById(id);
    }

    public List<GroupTest> getAllGroupTest(int company_id) {
        log.info("Вход и выход");
        return testHiberDao.getAllGroup(company_id);
    }

    public void deleteGroupTest(Integer id) {
        log.info("Вход и выход");
        testHiberDao.deleteGroupTest(id);
    }

    public void addGroup(GroupTest groupTest) {
        log.info("Вход и выход");
        testHiberDao.addGroup(groupTest);
    }

    public void updateAllGroup(Integer[] id, String[] name, Integer[] companyId) {
        log.info("Вход");
        List<GroupTest> groupTests = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            GroupTest groupTest = new GroupTest();
            groupTest.setGroupTestId(id[i]);
            groupTest.setName(name[i]);
            groupTest.setTestList(getTestsByGroupId(id[i]));
            groupTest.setCompany(companyDao.getCompanyById(companyId[i]));
            groupTests.add(groupTest);
        }
        testHiberDao.updateAllGroup(groupTests);
        log.info("Выход");
    }

    public GroupTest getGroupTestById(int groupId) {
        log.info("Вход и выход");
        return testHiberDao.getGroupById(groupId);
    }

    public List<Test> getTestsByGroupId(int groupId) {
        log.info("Вход и выход");
        return testHiberDao.getTestsByGroupId(groupId);
    }

    public void saveQuestion(Question question, String[] answersName, String[] rightAnswer, int testId) {
        log.info("Вход");
        //получаем из БД question по id
        question.setTest(getTestById(testId));

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
        log.info("Выход");
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
        User userforStatisic = userService.getUserById(id);
        //это все его попытки сдачт тестов
        List<AttemptTest> attemptTestList = attemptTestService.getAllAttemptByUserId(id);



        /* получим список всех тестов, который проходил наш пользователь
        и преобразуем в Map для удобства последующего поиска */
        Set<Test> tests = new HashSet<>(testHiberDao.getAllTestsOfUser(id));
        Map<Integer, Test> testHashMap = new HashMap<>();
        for (Test test : tests){
            testHashMap.put(test.getTestId(), test);
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        //сюда будем складывать результаты сдачи тестов и отправим в представление
        List<NewStatistic> newStatisticList = new ArrayList<>();

        for (AttemptTest attemptTest : attemptTestList) {
            String date = attemptTest.getDateTime().toLocalDateTime().format(dateTimeFormatter);
            String testName = testHashMap.get(attemptTest.getTestId()).getTestName();
            int amountFalseAnswers = attemptTest.getAmountFalseAnswer();
            int amountTrueAnswer = attemptTest.getAmountTrueAnswer();
            String testResult = attemptTest.getTestResult();
            int time = attemptTest.getTimeAttempt();
            int amountQues = attemptTest.getAmountQues();
            double result = attemptTest.getResult();
            double criteria = testHashMap.get(attemptTest.getTestId()).getCriteria();
            int attemptId = attemptTest.getAttemptId();

            newStatisticList.add(new NewStatistic(date, testName, amountFalseAnswers,
                    amountTrueAnswer, testResult, time, amountQues, result, criteria, attemptId));


//            //Все это для детализации
//            //Это список заданных вопросов для попытки
//            List<Integer> listOfUsersAnswers;
//            List<Question> quesList = resultTestService.getRegistredQuestionByattempt(attemptTest.getAttemptId());
//
//            List<ResultTest> resultTestList = resultTestService.getResultTest(attemptTest.getAttemptId());
//            Map<Integer, List<Integer>> mapOfUserAnswers = getMapOfAnswers(resultTestList);
//            Set<Integer> falseAnswerSet = getFalseAnswerSet(mapOfUserAnswers, quesList);
//            listOfUsersAnswers = getListOfUsersAnswers(mapOfUserAnswers);
//            int trueAnswer = quesList.size() - falseAnswerSet.size();
//            String testResult;
//            double result = 0;
//            if (test.getCriteria() != null) {
//                result = getResult(trueAnswer, quesList.size());
//                testResult = getTestResult(result, test.getCriteria()) ?
//                        "Тест пройден" : "Тест не пройден";
//            } else testResult = "Не задан критерий в настройках теста";
//
//            String time = attemptTestService.getTime(attemptTest.getTimeAttempt());
//
//            statisticList.add(new Statistic(date, test, falseAnswerSet, trueAnswer,
//                    testResult, listOfUsersAnswers, result, time, quesList));
        }

        request.setAttribute("newStatisticList", newStatisticList);
        request.setAttribute("userForStatistic", userforStatisic);

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

    public Test getShuffleTest(Test test) {
        log.info("Вход");
        List<Question> questionList = test.getQuestions();
        Collections.shuffle(questionList);
        test.setQuestions(questionList.stream().limit(test.getQuesAmount()).collect(Collectors.toList()));
        log.info("Выход");
        return test;
    }

    public void updateTest(Test test) {
        log.info("Вход и выход");
        testHiberDao.updateTest(test);
    }

    public void registerTest(int attemptId, Test test) {
        log.info("Вход");
        for (Question question : test.getQuestions()) {
            testHiberDao.registerTest(attemptId, question.getQuestionId());
        }
        log.info("Выход");
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
}
