package ru.laskin.myWebApp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.laskin.myWebApp.dao.AnswersDao;
import ru.laskin.myWebApp.dao.AttemptTestDao;
import ru.laskin.myWebApp.model.*;
import ru.laskin.myWebApp.service.AttemptTestService;
import ru.laskin.myWebApp.service.ResultTestService;
import ru.laskin.myWebApp.service.TestService;
import ru.laskin.myWebApp.service.UserService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCont.getBeanDefinitionNames()));

        UserService service = appCont.getBean("userService", UserService.class);
        User user = service.getUserBylogin("qqq");
        System.out.println(user.getUserId() + " " + user.getLogin() + " " + user.getPassword());

        TestService testService = appCont.getBean("testService", TestService.class);
        List<Question> list = testService.getAllQuestions();

        for (Question question : list){
            System.out.println(question.getQuestionName());
            print(question.getAnswers());
        }

        Timestamp timestamp = new Timestamp(new Date().getTime());
        System.out.println(timestamp);

        AttemptTestService attemptTestService = appCont.getBean("attemptTestService", AttemptTestService.class);
        System.out.println("id = " + attemptTestService.saveAttemptTest(new AttemptTest(timestamp, 1,1)));

        ResultTestService resultTestService = appCont.getBean(ResultTestService.class);
        resultTestService.saveResultTest(new ResultTest(1, 1, 1));

        AnswersDao answersDao = appCont.getBean(AnswersDao.class);

    }

    public static void print(List<Answer> list){
        for (Answer answer : list){
            System.out.println(answer.getAnswerName());
        }
    }
    
}
