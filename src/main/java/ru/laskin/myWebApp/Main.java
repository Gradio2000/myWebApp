package ru.laskin.myWebApp;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xhtmlrenderer.pdf.ITextRenderer;
import ru.laskin.myWebApp.dao.*;
import ru.laskin.myWebApp.model.*;


import java.awt.print.PrinterJob;
import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, DocumentException, com.lowagie.text.DocumentException {

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
        deletePosition(6);
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

    private static void createPDF() throws IOException, DocumentException, com.lowagie.text.DocumentException {
//        Document doc = new Document(PageSize.A4);
//        FileOutputStream outputStream = new FileOutputStream("/Users/aleksejlaskin/Documents/1/1.pdf");
//        PdfWriter.getInstance(doc, outputStream);
//        doc.open();
//        HTMLWorker hw = new HTMLWorker(doc);
//        hw.parse(new StringReader("/Users/aleksejlaskin/IdeaProjects/myWebApp/src/main/webapp/WEB-INF/jsp/testResult.jsp"));
//        doc.close();



    }

    public static void generatePdfFromHtml() throws IOException, DocumentException, com.lowagie.text.DocumentException {
//        String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf.pdf";
        String html = "\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"<html>\\n\" +\n" +
                "                \"<head>\\n\" +\n" +
                "                \"    <title>Детализация</title>\\n\" +\n" +
                "                \"</head>\\n\" +\n" +
                "                \"<style>\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"    table{\\n\" +\n" +
                "                \"        border-collapse: collapse;\\n\" +\n" +
                "                \"    }\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"    tr{\\n\" +\n" +
                "                \"        height: 10mm;\\n\" +\n" +
                "                \"    }\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"    td{\\n\" +\n" +
                "                \"        text-align: justify;\\n\" +\n" +
                "                \"        padding: 1mm;\\n\" +\n" +
                "                \"    }\\n\" +\n" +
                "                \"    .cont{\\n\" +\n" +
                "                \"        /*height:297mm;*/\\n\" +\n" +
                "                \"        width:240mm;\\n\" +\n" +
                "                \"        margin-left: auto;\\n\" +\n" +
                "                \"        margin-right: auto;\\n\" +\n" +
                "                \"        background-color: #fbfbfb;\\n\" +\n" +
                "                \"    }\\n\" +\n" +
                "                \"    .list{\\n\" +\n" +
                "                \"        /*width:220mm;*/\\n\" +\n" +
                "                \"        padding: 10mm;\\n\" +\n" +
                "                \"        margin-left: auto;\\n\" +\n" +
                "                \"        margin-right: auto;\\n\" +\n" +
                "                \"        background-color: whitesmoke;\\n\" +\n" +
                "                \"    }\\n\" +\n" +
                "                \"    .bigth{\\n\" +\n" +
                "                \"        width: 170mm;\\n\" +\n" +
                "                \"    }\\n\" +\n" +
                "                \"    .smallth{\\n\" +\n" +
                "                \"        width: 22mm;\\n\" +\n" +
                "                \"        text-align: center;\\n\" +\n" +
                "                \"    }\\n\" +\n" +
                "                \"</style>\\n\" +\n" +
                "                \"<body>\\n\" +\n" +
                "                \"<div class=\\\"wrapper\\\" style=\\\"background-color: #d6d6d6\\\">\\n\" +\n" +
                "                \"    <div class=\\\"cont\\\">\\n\" +\n" +
                "                \"        <div class=\\\"list\\\">\\n\" +\n" +
                "                \"            <h3 align=\\\"center\\\">Результаты тестирования</h3>\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"            <table>\\n\" +\n" +
                "                \"                <tr>\\n\" +\n" +
                "                \"                    <td style=\\\"width: 70mm\\\">Тестируемый</td>\\n\" +\n" +
                "                \"                    <td>vvvv vvvv vvvv1</td>\\n\" +\n" +
                "                \"                </tr>\\n\" +\n" +
                "                \"                <tr>\\n\" +\n" +
                "                \"                    <td>Должность</td>\\n\" +\n" +
                "                \"                    <td>Заместитель начальника банка</td>\\n\" +\n" +
                "                \"                </tr>\\n\" +\n" +
                "                \"                <tr>\\n\" +\n" +
                "                \"                    <td>Дата и время</td>\\n\" +\n" +
                "                \"                    <td>13.07.2021 09:13</td>\\n\" +\n" +
                "                \"                </tr>\\n\" +\n" +
                "                \"                <tr>\\n\" +\n" +
                "                \"                    <td>Наименование теста</td>\\n\" +\n" +
                "                \"                    <td>Для ревизии</td>\\n\" +\n" +
                "                \"                </tr>\\n\" +\n" +
                "                \"                <tr>\\n\" +\n" +
                "                \"                    <td>Затраченное время</td>\\n\" +\n" +
                "                \"                    <td class=\\\"mytd\\\">Не учитывалось</td>\\n\" +\n" +
                "                \"                </tr>\\n\" +\n" +
                "                \"                <tr>\\n\" +\n" +
                "                \"                    <td>Количество заданных вопросов</td>\\n\" +\n" +
                "                \"                    <td class=\\\"mytd\\\">1</td>\\n\" +\n" +
                "                \"                </tr>\\n\" +\n" +
                "                \"                <tr>\\n\" +\n" +
                "                \"                    <td>Количество правильных ответов</td>\\n\" +\n" +
                "                \"                    <td class=\\\"mytd\\\">0</td>\\n\" +\n" +
                "                \"                </tr>\\n\" +\n" +
                "                \"                <tr>\\n\" +\n" +
                "                \"                    <td>Количество неправильных ответов</td>\\n\" +\n" +
                "                \"                    <td class=\\\"mytd\\\">1</td>\\n\" +\n" +
                "                \"                </tr>\\n\" +\n" +
                "                \"                <tr>\\n\" +\n" +
                "                \"                    <td>Критерий прохождения теста</td>\\n\" +\n" +
                "                \"                    <td class=\\\"mytd\\\">30.0%</td>\\n\" +\n" +
                "                \"                </tr>\\n\" +\n" +
                "                \"                <tr>\\n\" +\n" +
                "                \"                    <td>Результат</td>\\n\" +\n" +
                "                \"                    <td class=\\\"mytd\\\">0.0%</td>\\n\" +\n" +
                "                \"                </tr>\\n\" +\n" +
                "                \"            </table>\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"                \\n\" +\n" +
                "                \"                    <table style=\\\"margin-top: 15mm\\\">\\n\" +\n" +
                "                \"                        <tr>\\n\" +\n" +
                "                \"                            <td style=\\\"width: 70mm\\\"><h4/>Вопрос № 1:</td>\\n\" +\n" +
                "                \"                            <td>\\n\" +\n" +
                "                \"                                <h4>\\n\" +\n" +
                "                \"                                    \\n\" +\n" +
                "                \"                                        <p class=\\\"false\\\">Неправильный ответ</p>\\n\" +\n" +
                "                \"                                    \\n\" +\n" +
                "                \"                                    \\n\" +\n" +
                "                \"                                </h4>\\n\" +\n" +
                "                \"                            </td>\\n\" +\n" +
                "                \"                        </tr>\\n\" +\n" +
                "                \"                        <table>\\n\" +\n" +
                "                \"                            <tr>\\n\" +\n" +
                "                \"                                <td><h4>Вопрос 4</h4></td>\\n\" +\n" +
                "                \"                            </tr>\\n\" +\n" +
                "                \"                        </table>\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"                    </table>\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"                    <table style=\\\"margin-top: 5mm\\\" border=\\\"1\\\">\\n\" +\n" +
                "                \"                        <tr>\\n\" +\n" +
                "                \"                            <th class=\\\"bigth\\\">Варианты ответов</th>\\n\" +\n" +
                "                \"                            <th class=\\\"smallth\\\">Ваши ответы</th>\\n\" +\n" +
                "                \"                            <th class=\\\"smallth\\\">Правильные ответы</th>\\n\" +\n" +
                "                \"                        </tr>\\n\" +\n" +
                "                \"                        \\n\" +\n" +
                "                \"                            <tr>\\n\" +\n" +
                "                \"                                <td class=\\\"bigth\\\">Ответ на вопрос 4</td>\\n\" +\n" +
                "                \"                                <td class=\\\"smallth\\\">\\n\" +\n" +
                "                \"                                    \\n\" +\n" +
                "                \"                                </td>\\n\" +\n" +
                "                \"                                <td class=\\\"smallth\\\">\\n\" +\n" +
                "                \"                                    \\n\" +\n" +
                "                \"                                </td>\\n\" +\n" +
                "                \"                            </tr>\\n\" +\n" +
                "                \"                        \\n\" +\n" +
                "                \"                            <tr>\\n\" +\n" +
                "                \"                                <td class=\\\"bigth\\\">Ответ на вопрос 4</td>\\n\" +\n" +
                "                \"                                <td class=\\\"smallth\\\">\\n\" +\n" +
                "                \"                                    \\n\" +\n" +
                "                \"                                </td>\\n\" +\n" +
                "                \"                                <td class=\\\"smallth\\\">\\n\" +\n" +
                "                \"                                    \\n\" +\n" +
                "                \"                                        <p>V</p>\\n\" +\n" +
                "                \"                                    \\n\" +\n" +
                "                \"                                </td>\\n\" +\n" +
                "                \"                            </tr>\\n\" +\n" +
                "                \"                        \\n\" +\n" +
                "                \"                            <tr>\\n\" +\n" +
                "                \"                                <td class=\\\"bigth\\\">Ответ на вопрос 4</td>\\n\" +\n" +
                "                \"                                <td class=\\\"smallth\\\">\\n\" +\n" +
                "                \"                                    \\n\" +\n" +
                "                \"                                </td>\\n\" +\n" +
                "                \"                                <td class=\\\"smallth\\\">\\n\" +\n" +
                "                \"                                    \\n\" +\n" +
                "                \"                                </td>\\n\" +\n" +
                "                \"                            </tr>\\n\" +\n" +
                "                \"                        \\n\" +\n" +
                "                \"                    </table>\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"                \\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"        </div>\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"    </div>\\n\" +\n" +
                "                \"</div>\\n\" +\n" +
                "                \"</body>\\n\" +\n" +
                "                \"</html>\\n";
        String outputFolder = "/Users/aleksejlaskin/Documents/1/1.pdf";
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
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
