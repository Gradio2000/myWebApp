package ru.laskin.myWebApp;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.laskin.myWebApp.dao.TestHiberDao;
import ru.laskin.myWebApp.model.Answer;
import ru.laskin.myWebApp.model.GroupTest;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.model.Test;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");

        TestHiberDao testHiberDao = appCont.getBean(TestHiberDao.class);

//        List<Question> questions = new ArrayList<>();
//        Question question = new Question("как дела?");
//        Answer answer = new Answer("отлично", true);
//        List<Answer> answers = new ArrayList<>();
//        answers.add(answer);
//        question.setAnswers(answers);
//        questions.add(question);
//
//        Test test = new Test( "asd", 1, questions);
//        testHiberDao.saveTest(test);
//        printTest();
        printGroup();

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
