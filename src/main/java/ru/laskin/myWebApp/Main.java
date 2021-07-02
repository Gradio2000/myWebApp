package ru.laskin.myWebApp;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.laskin.myWebApp.dao.AttemptTestDao;
import ru.laskin.myWebApp.dao.TestHiberDao;
import ru.laskin.myWebApp.dao.UserHiberDao;
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
        getUserByEmail("aa@aa.aa");
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
