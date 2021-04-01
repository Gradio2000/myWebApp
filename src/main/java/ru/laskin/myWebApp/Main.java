package ru.laskin.myWebApp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.laskin.myWebApp.model.Answer;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.TestService;
import ru.laskin.myWebApp.service.UserService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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


    }

    public static void print(List<Answer> list){
        for (Answer answer : list){
            System.out.println(answer.getAnswerName());
        }
    }
    
}
