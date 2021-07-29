package ru.laskin.myWebApp;


import com.itextpdf.text.DocumentException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.laskin.myWebApp.dao.*;
import ru.laskin.myWebApp.model.*;

import java.awt.print.PrinterJob;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Main {


    public static void main(String[] args) throws SQLException, IOException, DocumentException {

//        getCompany(1);
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
//        getAttemptById(1);
//        updateAttemptTest();
//        saveAttemptTest();
//        getAllPosition();
//        saveQuestion();
//        getAllResultByAttempt();
//        saveResultTest();
//        generatePdfFromHtml();
//        createPDF();
//        getPdf();
//        deletePosition(6);
        saveCompany();
    }

    private static void saveCompany() {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        CompanyDao companyDao = appCont.getBean(CompanyDao.class);
        Company company = new Company();
        company.setCompanyName("aaaaaa");
        companyDao.saveCompany(company);
    }

    private static void getCompany(int i) {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        CompanyDao companyDao = appCont.getBean(CompanyDao.class);

        List<Company> companyList = companyDao.getAllCompany();
        for (Company company : companyList){
            System.out.println(company.getCompanyName());
            List<GroupTest> groupTestList = company.getGroupTestList();
            for (GroupTest groupTest : groupTestList){
                System.out.println(groupTest.getName());
            }
        }
    }

    private static void deletePosition(int i) {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        PositionDao positionDao = appCont.getBean(PositionDao.class);
        positionDao.deletePosition(i);
    }

    public static void getPdf(){
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.printDialog();
        printerJob.getPrintService();

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

    private static void getAllPosition(){

        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        PositionDao positionDao = appCont.getBean(PositionDao.class);

        List<Position> positionList = positionDao.getAllPosition(1);
        for (Position position : positionList) {
            System.out.println(position.getUserList().size());
        }
//        logr.log(Level.SEVERE, "A fake error occurred mate!");

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
        System.out.println(attemptTest.getUserId());

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
        System.out.println(user.getPosition().getPosition());
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

        List<User> userList = userHiberDao.getAllUsers(1);
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

        List<GroupTest> groupTestList = testHiberDao.getAllGroup(1);

        GroupTest groupTest = groupTestList.get(0);
        List<Test> testList = groupTest.getTestList();
        Test test = testList.get(0);
        List<Question> questionList = test.getQuestions();
        Question question = questionList.remove(0);
        System.out.println(question.getQuestionId() + " = " + question.getQuestionName());
        question.setQuestionId(0);
        System.out.println(question.getQuestionId() + " = " + question.getQuestionName());
        questionList.add(0, question);

        testHiberDao.updateTest(test);
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

        List<GroupTest> groupTestList = testHiberDao.getAllGroup(1);
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
