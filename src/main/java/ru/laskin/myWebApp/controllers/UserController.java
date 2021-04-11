package ru.laskin.myWebApp.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.laskin.myWebApp.Main;
import ru.laskin.myWebApp.model.Position;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.PositionService;
import ru.laskin.myWebApp.service.TestService;
import ru.laskin.myWebApp.service.UserService;
import ru.laskin.myWebApp.validation.UserDopRegistrationValidator;
import ru.laskin.myWebApp.validation.UserValidator;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Controller
public class UserController {

    private UserService userService;
    private PositionService positionService;
    private TestService testService;
    private UserValidator userValidator;
    private UserDopRegistrationValidator userDopRegistrationValidator;


    public UserController(UserService userService, PositionService positionService,
                          TestService testService, UserValidator userValidator,
                          UserDopRegistrationValidator userDopRegistrationValidator) {
        this.userService = userService;
        this.positionService = positionService;
        this.testService = testService;
        this.userValidator = userValidator;
        this.userDopRegistrationValidator = userDopRegistrationValidator;
    }

    @GetMapping("/greeting")
    public String greeting(Model model){

        //получаем авторизованного пользователя (принципала) из контекста безопасности
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Получаем из БД юзера, того, кто принципал (это нужно для того, что
        //у авторизированного пользователя не будут запрлнены поля имя и т.п.
        User user = userService.getUserById(authUser.getUserId());

        //если пользователь - админ, переходим на нужную страницу
        if (user.getAdminRole().equals("ADMIN")){
            return "/allUsers";
        }

        //если роль пользователя - USER,
        //добавляем авторизованного пользователя в модель представления
        model.addAttribute("user", user);

        //если у пользователя есть незаполненные поля - отправляем его дальше регистрироваться
        if (user.getName() == null || user.getName().equals("") || user.getEmail() == null || user.getEmail().equals("")) {
            //получаем из бд список должностей и передаем в модель представления
            List<Position> posSet = positionService.getAllPosition();
            model.addAttribute("posSet", posSet);
            return "greeting";
        }



        model.addAttribute("allTest", testService.getAllTests());
        model.addAttribute("test", new Test());
        return "testPage";
    }

    @GetMapping("/new_user")
    public String formUser(Model model){
        //передаем пустого пользователя, чтобы там его наполнить
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/new_user")
    public String formUser (HttpServletRequest request, @ModelAttribute User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()){
            return "registration";
        }

        //эти переменные нужны для автологина
        //пароль ещё не закодирован
        String login = user.getLogin();
        String pass = user.getPassword();

        userService.saveUser(user);

        //автологин, если регистрация прошла успешно
        try {
            request.login(login, pass);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return "redirect:greeting";
    }

    @PostMapping("/reUpdate")
    public String reUpdate(@ModelAttribute User user, BindingResult bindingResult, Model model){
        userDopRegistrationValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()){
            model.addAttribute("user", user);
            //получаем из бд список должностей и передаем в модель представления
            List<Position> posSet = positionService.getAllPosition();
            model.addAttribute("posSet", posSet);
            return "greeting";
        }

        //регистрируем пользователя
        userService.updateUser(user);
        //отправляем письмо
        UserService.sendEmail(user);

        return "confirmEmail";

    }

    @PostMapping("/startTest")
    public String testStart(@RequestParam("testId") String testId, Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("questions", testService.getAllQuestions());
        model.addAttribute("userId" , user.getUserId());
        model.addAttribute("testId", testId);

        return "testProcessing";
    }

    @GetMapping("/confirmEmail")
    public String confirmEmail(@RequestParam("userId") Integer userId, @RequestParam("key") String key, Model model){
        //проверка uuid пользователя
        if (userService.checkUuid(userId, key)){
            model.addAttribute("user", userService.getUserById(userId));
            return "confirmEmailSuccess";
        }
        else return "confirmEmailNotSuccess";
    }

}
