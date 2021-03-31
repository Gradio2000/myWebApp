package ru.laskin.myWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.PositionService;
import ru.laskin.myWebApp.service.TestService;
import ru.laskin.myWebApp.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private TestService testService;

    @GetMapping("/greeting")
    public String greeting(Model model){

        //получаем авторизованного пользователя (принципала) из контекста безопасности
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //если авторизованный пользователь - админ, переходим на нужную страницу
        if (authUser.getAdminRole().equals("ADMIN")){
            return "/allUsers";
        }
        //если авторизованный пользователь - USER,
        //добавляем авторизованного пользователя в модель представления
        model.addAttribute("authUser", authUser);

        //получаем из бд список должностей и передаем в модель представления
        Set<String> posSet = positionService.getAllPosition();
        model.addAttribute("posSet", posSet);

        return "greeting";
    }

    @GetMapping("/new_user")
    public String formUser(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/new_user")
    public String formUser (@ModelAttribute User user) throws SQLException {
        userService.saveUser(user);
        return "login";
    }

    @PostMapping("/reUpdate")
    public String reUpdate(@ModelAttribute User user, Model model){
        userService.updateUser(user);

        model.addAttribute("allTest", testService.getAllTests());
        return "testPage";
    }

    @PostMapping("/startTest")
    public String testStart(@RequestParam("testSelected") String testSelected,
                            Model model){
        List<Question> questionList = testService.getAllQuestions();
        model.addAttribute("questions", testService.getAllQuestions());
        System.out.println(questionList.get(0).getAnswers());
        return "/testProcessing";
    }
}
