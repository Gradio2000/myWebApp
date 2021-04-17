package ru.laskin.myWebApp;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.laskin.myWebApp.dao.TestHiberDao;
import ru.laskin.myWebApp.model.Answer;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.model.Test;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");

        TestHiberDao testHiberDao = appCont.getBean(TestHiberDao.class);
//        List<Test> allTests = testHiberDao.getAllTests();
//        for (Test test : allTests){
//            System.out.println(test.getTestName());
//            List<Question> questions = test.getQuestions();
//            for (Question question : questions){
//                System.out.println(question.getQuestionName());
//                List<Answer> answers = question.getAnswers();
//                for (Answer answer : answers){
//                    System.out.println(answer.getAnswerName());
//                }
//            }
//        }

        Test test = testHiberDao.getTestById(2);
        List<Question> questions = test.getQuestions();
        Question question = questions.get(0);
        System.out.println(question.getQuestionName());
        question.setQuestionName("Новый вопрос");
        questions.add(question);
        System.out.println(test.getTestName());
        test.setTestName("xxx");
        testHiberDao.updateTest(test);
    }
}
