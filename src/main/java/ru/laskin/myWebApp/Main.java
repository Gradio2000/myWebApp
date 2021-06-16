package ru.laskin.myWebApp;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.laskin.myWebApp.dao.AttemptTestDao;
import ru.laskin.myWebApp.dao.TestHiberDao;
import ru.laskin.myWebApp.model.*;
import ru.laskin.myWebApp.service.AttemptTestService;
import ru.laskin.myWebApp.service.TestService;
import ru.laskin.myWebApp.service.UserService;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, DocumentException {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
//        TestHiberDao testHiberDao = appCont.getBean(TestHiberDao.class);
        UserService userService = appCont.getBean(UserService.class);

//        Document document = new Document();
//        // Создаем writer для записи в pdf
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("pdf.pdf"));
//        // Открываем для чтения html страничку
//        document.open();
//        // Парсим её и записываем в PDF
//        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream("src/main/webapp/WEB-INF/jsp/userResult.jsp"));
//        document.close();
//
//        System.out.println( "Ваш PDF файл - Создан!" );

//        TestService testService = appCont.getBean(TestService.class);
//        Test test = testHiberDao.getTestById(14);
//        System.out.println(test.getQuestions());
//        testService.getShuffleTest(test);
//        System.out.println(test.getQuestions());
//        testService.getShuffleTest(test);
//        System.out.println(test.getQuestions());

//        String hours = "0" + (int) Math.floor((70 / (60.0 * 60)) % 24);
//        System.out.println(hours);

        User user = userService.getUserByEmail("laskin@gmail.com");
        System.out.println(user.getName());

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
