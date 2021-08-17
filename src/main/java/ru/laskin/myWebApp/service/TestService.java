package ru.laskin.myWebApp.service;

import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.controllers.userModule.ResultController;
import ru.laskin.myWebApp.dao.CompanyDao;
import ru.laskin.myWebApp.dao.QuestionHiberDao;
import ru.laskin.myWebApp.dao.TestHiberDao;
import ru.laskin.myWebApp.model.Answer;
import ru.laskin.myWebApp.model.GroupTest;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.model.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TestService {
    private static final Logger log = Logger.getLogger(ResultController.class.getName());

    private final TestHiberDao testHiberDao;
    private final QuestionHiberDao questionHiberDao;
    private final CompanyDao companyDao;


    public TestService(TestHiberDao testHiberDao, QuestionHiberDao questionHiberDao, CompanyDao companyDao) {
        this.testHiberDao = testHiberDao;
        this.questionHiberDao = questionHiberDao;
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

    public TestHiberDao getTestHiberDao() {
        return testHiberDao;
    }

    public QuestionHiberDao getQuestionHiberDao() {
        return questionHiberDao;
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }
}
