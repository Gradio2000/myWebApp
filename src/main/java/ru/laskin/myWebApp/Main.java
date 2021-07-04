package ru.laskin.myWebApp;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.laskin.myWebApp.dao.*;
import ru.laskin.myWebApp.model.*;
import ru.laskin.myWebApp.service.AttemptTestService;
import ru.laskin.myWebApp.service.TestService;
import ru.laskin.myWebApp.service.UserService;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, DocumentException {

//        printGroup();
//        printGroupById(1);
//        printTest();
//        printTestById(10);
//        deletetest(10);
//        saveTest();
//        getTestByGroupId(1);
//        printUser();
//        getUserById(1);
//        saveuser();
//        getUserByLogin("login");
//        deleteUser(2);
//        updateUser();
//        changePassword(1, "ddd");
//        getUserByEmail("aa@aa.aa");
//        getAllAttempt();
//        getAllAttemptByUserId(1);
        getAttemptById(1);
//        updateAttemptTest();
//        saveAttemptTest();
//        getAllPosition();
//        saveQuestion();
//        getAllResultByAttempt();
//        saveResultTest();
    }

    private static void saveResultTest() {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        ResultTestDao resultTestDao = appCont.getBean(ResultTestDao.class);

        ResultTest resultTest = new ResultTest(1, 3, 3);
        resultTestDao.saveResultTest(resultTest);
    }

    private static void getAllResultByAttempt() {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        ResultTestDao resultTestDao = appCont.getBean(ResultTestDao.class);

        List<ResultTest> resultTestList = resultTestDao.getAllResultByAttempt(1);
        for (ResultTest resultTest : resultTestList){
            System.out.println(resultTest.getAnswerId());
        }
    }

    private static void saveQuestion() {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        QuestionHiberDao questionHiberDao = appCont.getBean(QuestionHiberDao.class);

        List<Answer> answerList = new ArrayList<>();
        Question question = new Question(1, "aaa", answerList);
        questionHiberDao.saveQuestion(question);
    }

    private static void getAllPosition() {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        PositionDao positionDao = appCont.getBean(PositionDao.class);

        List<Position> positionList = positionDao.getAllPosition();
        for (Position position : positionList){
            System.out.println(position.getPosition());
        }
    }


    private static void saveAttemptTest() {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        AttemptTestDao attemptTestDao = appCont.getBean(AttemptTestDao.class);

        AttemptTest attemptTest = new AttemptTest(new Timestamp(new Date().getTime()), 1, 1, 222, 333);
        int i = attemptTestDao.saveAttemptTest(attemptTest);
        System.out.println(i);
    }


    private static void updateAttemptTest() {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        AttemptTestDao attemptTestDao = appCont.getBean(AttemptTestDao.class);

        AttemptTest attemptTest = getAttemptById(1);
        attemptTestDao.updateAttemptTest(1, 111);
    }

    private static AttemptTest getAttemptById(int i) {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        AttemptTestDao attemptTestDao = appCont.getBean(AttemptTestDao.class);

        AttemptTest attemptTest = attemptTestDao.getAttemptById(i);

        return attemptTest;
    }

    private static void getAllAttemptByUserId(int i) {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        AttemptTestDao attemptTestDao = appCont.getBean(AttemptTestDao.class);

        List<AttemptTest> attemptTests = attemptTestDao.getAllAttemptByUserId(i);
        for (AttemptTest attemptTest : attemptTests){
            System.out.println(attemptTest.getDateTime());
        }

    }

    private static void getAllAttempt() {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        AttemptTestDao attemptTestDao = appCont.getBean(AttemptTestDao.class);

        List<AttemptTest> attemptTests = attemptTestDao.getAllAttempt();
        for (AttemptTest attemptTest : attemptTests){
            System.out.println(attemptTest.getDateTime());
        }
    }

    private static void getUserByEmail(String s) {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        UserHiberDao userHiberDao = appCont.getBean(UserHiberDao.class);

        System.out.println(userHiberDao.getUserByEmail(s).getName());
    }


    private static void changePassword(int i, String ddd) {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        UserHiberDao userHiberDao = appCont.getBean(UserHiberDao.class);

        userHiberDao.changePassword(i, ddd);
    }

    private static void updateUser() {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        UserHiberDao userHiberDao = appCont.getBean(UserHiberDao.class);
        User user = userHiberDao.getUserById(1);
        user.setName("Kfcrbyzy");
        userHiberDao.updateUser(user);
    }

    private static void deleteUser(int i) {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        UserHiberDao userHiberDao = appCont.getBean(UserHiberDao.class);
        userHiberDao.deleteUser(i);
    }

    private static void getUserByLogin(String login) {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        UserHiberDao userHiberDao = appCont.getBean(UserHiberDao.class);
        System.out.println(userHiberDao.getUserByLogin(login).getName());
    }

    private static User getUserById(int i) {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        UserHiberDao userHiberDao = appCont.getBean(UserHiberDao.class);
        User user = userHiberDao.getUserById(i);
        System.out.println(user.getName());
        return user;
    }

    private static void saveuser() {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        UserHiberDao userHiberDao = appCont.getBean(UserHiberDao.class);

        User user = getUserById(1);
        user.setName("wwww");
        user.setKey(UUID.fromString("36e88c7b-5bd2-4075-be86-f23eccf244bd"));
        user.setUserId(2);
        userHiberDao.saveUser(user);
    }

    private static void printUser() {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        UserHiberDao userHiberDao = appCont.getBean(UserHiberDao.class);

        List<User> userList = userHiberDao.getAllUsers();
        for (User user : userList){
            System.out.println(user.getName());
        }
    }

    private static void getTestByGroupId(int id) {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        TestHiberDao testHiberDao = appCont.getBean(TestHiberDao.class);

        List<Test> testList = testHiberDao.getTestsByGroupId(id);
        for (Test test : testList){
            System.out.println(test.getTestName());
        }
    }

    private static void printGroupById(int i) {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        TestHiberDao testHiberDao = appCont.getBean(TestHiberDao.class);

        GroupTest groupTest = testHiberDao.getGroupById(i);
        System.out.println(groupTest.getName());
        List<Test> list = groupTest.getTestList();
        for (Test test : list){
            System.out.println("\t" + test.getTestName());
        }
    }

    private static void deletetest(int id) {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        TestHiberDao testHiberDao = appCont.getBean(TestHiberDao.class);

        testHiberDao.deleteTestById(id);
    }

    private static void saveTest() {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        TestHiberDao testHiberDao = appCont.getBean(TestHiberDao.class);

        List<Question> questionList = new ArrayList<>();
        Test test = new Test("qqqqq", questionList);

        testHiberDao.saveTest(test);
    }

    public static void printTestById(int id){
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        TestHiberDao testHiberDao = appCont.getBean(TestHiberDao.class);
        Test test = testHiberDao.getTestById(id);
        System.out.println(test.getTestName());
    }

    public static void printGroup(){
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        TestHiberDao testHiberDao = appCont.getBean(TestHiberDao.class);

        List<GroupTest> groupTestList = testHiberDao.getAllGroup();
        for (GroupTest groupTest : groupTestList){
            System.out.println(groupTest.getName());
            List<Test> testList = groupTest.getTestList();
            for (Test test : testList){
                System.out.println("\t" + test.getTestName());
            }
        }
    }

    public static void printTest(){
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        TestHiberDao testHiberDao = appCont.getBean(TestHiberDao.class);

        List<Test> tests = testHiberDao.getAllTests();
        for (Test test : tests){
            System.out.println(test.getTestName());
            List<Question> questions = test.getQuestions();
            for (Question question : questions){
                System.out.println("\t" + question.getQuestionName());
                List<Answer> answers = question.getAnswers();
                for (Answer answer : answers){
                    System.out.println("\t\t" + answer.getAnswerName());
                }
            }
        }
    }
}
