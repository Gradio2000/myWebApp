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


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, DocumentException {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
//        TestHiberDao testHiberDao = appCont.getBean(TestHiberDao.class);

//        UserService userService = appCont.getBean(UserService.class);

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

//        User user = userService.getUserByEmail("laskin@gmail.com");
//        System.out.println(user.getName());


//            String fileName = "/Users/aleksejlaskin/Documents/Книга2.csv";
//            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
//
//            String line;
//            Question question;
//            Answer answer;
//            List<Question> questionList = new ArrayList<>();
//            List<Answer> answerList;
//
//            int i = 0;
//            while ((line = reader.readLine()) != null) {
//                String[] lineMass = line.split(";");
//
//                //проверяем первую ячейку
//                if (!lineMass[0].equals("")){
//                    question = new Question();
//                    question.setQuestionName(lineMass[0]);
//                    answerList = new ArrayList<>();
//                    i++;
//                }
//                else {
//                    if (questionList.size() != 0){
//                        question = questionList.remove(i - 1);
//                        answerList = question.getAnswers();
//                    }
//                    else {
//                        question = new Question();
//                        answerList = new ArrayList<>();
//                    }
//
//
//                }
//
//                answer = new Answer();
//
//                //проверяем вторую ячейку
//                if (!lineMass[1].equals("")){
//                    answer.setAnswerName(lineMass[1]);
//                }
//
//                //проверяем третью ячейку
//                if (lineMass.length == 3) {
//                    answer.setRight(true);
//                }
//
//                answerList.add(answer);
//                question.setAnswers(answerList);
//                questionList.add(question);
//            }
//            reader.close();
//
//            for(Question question1 : questionList){
//                System.out.println(question1.getQuestionName());
//                for (Answer answer1 : question1.getAnswers()){
//                    System.out.println(answer1.getAnswerName() + " " + answer1.isRight());
//                }
//            }
        Map<String,String> jdbcUrlSettings = new HashMap<>();
        String jdbcDbUrl = System.getenv("JDBC_DATABASE_URL");
        if (null != jdbcDbUrl) {
            jdbcUrlSettings.put("hibernate.connection.url", System.getenv("JDBC_DATABASE_URL"));
        }
        System.out.println(jdbcUrlSettings.get("hibernate.connection.url"));
//        printGroup();
//        printTest();
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
