package ru.laskin.myWebApp.controllers;

import jakarta.validation.Valid;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.laskin.myWebApp.model.Position;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.sequrity.AuthProvider;
import ru.laskin.myWebApp.service.PositionService;
import ru.laskin.myWebApp.service.TestService;
import ru.laskin.myWebApp.service.UserService;
import ru.laskin.myWebApp.validation.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    private PositionService positionService;
    private TestService testService;
    private UserValidator userValidator;

    private AuthProvider authProvider;


    public UserController(UserService userService, PositionService positionService,
                          TestService testService, UserValidator userValidator,
                          AuthProvider authProvider) {
        this.userService = userService;
        this.positionService = positionService;
        this.testService = testService;
        this.userValidator = userValidator;
        this.authProvider = authProvider;
    }

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
        model.addAttribute("user", authUser);
        //если у пользователя есть незаполненные поля - отправляем его дальше регистрироваться
        if (authUser.getName() == null || authUser.getName().equals("") || authUser.getEmail() == null || authUser.getEmail().equals("")) {
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
    public String reUpdate(@ModelAttribute User user, Model model){
        userService.updateUser(user);
        model.addAttribute("allTest", testService.getAllTests());
        model.addAttribute("test", new Test());
        model.addAttribute("user", user);
        return "testPage";
    }

    @PostMapping("/startTest")
    public String testStart(@RequestParam("testId") String testId,
                            Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("questions", testService.getAllQuestions());
        model.addAttribute("userId" , user.getUserId());
        model.addAttribute("testId", testId);

        return "testProcessing";
    }
}
