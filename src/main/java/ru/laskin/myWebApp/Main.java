package ru.laskin.myWebApp;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.laskin.myWebApp.controllers.UserController;import ru.laskin.myWebApp.model.*;
import ru.laskin.myWebApp.service.*;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
//        System.out.println("Bean definition names: " + Arrays.toString(appCont.getBeanDefinitionNames()));
//
        UserService service = appCont.getBean("userService", UserService.class);
       List<User> list = service.getAllUsers();
       for (User user : list){
           System.out.println(user.getUserId());
       }
//        System.out.println(user.getUserId() + " " + user.getLogin() + " " + user.getPassword());
//
//        TestService testService = appCont.getBean("testService", TestService.class);
//        List<Question> list = testService.getAllQuestions();
//
//
//        Timestamp timestamp = new Timestamp(new Date().getTime());
//        System.out.println(timestamp);
//
//        AttemptTestService attemptTestService = appCont.getBean("attemptTestService", AttemptTestService.class);
//        System.out.println("id = " + attemptTestService.saveAttemptTest(new AttemptTest(timestamp, 1,1)));
//
//        ResultTestService resultTestService = appCont.getBean(ResultTestService.class);
//        resultTestService.saveResultTest(new ResultTest(1, 1, 1));
//
//        Date date = new Date(timestamp.getTime());
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
//        String localDate = localDateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//        String localTime = localDateTime.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm"));
//
//        System.out.println(localDate + " " + localTime);

//        System.out.println(UUID.randomUUID());


        GroupTestService groupTestService = appCont.getBean(GroupTestService.class);

        List<GroupTest> listT = groupTestService.getAllGroupTest();
        for (GroupTest groupTest : listT){
            System.out.println(groupTest.getName());
            System.out.println(groupTest.getGrouptestId());
        }
    }



}
