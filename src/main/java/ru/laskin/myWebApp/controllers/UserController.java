package ru.laskin.myWebApp.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.laskin.myWebApp.model.Position;
import ru.laskin.myWebApp.model.User;

import ru.laskin.myWebApp.service.PositionService;
import ru.laskin.myWebApp.service.TestService;
import ru.laskin.myWebApp.service.UserService;
import ru.laskin.myWebApp.validation.UserDopRegistrationValidator;
import ru.laskin.myWebApp.validation.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final PositionService positionService;
    private final TestService testService;
    private final UserValidator userValidator;
    private final UserDopRegistrationValidator userDopRegistrationValidator;


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
    public String greeting(Model model, HttpServletRequest request, HttpSession session){

        //получаем авторизованного пользователя (принципала) из контекста безопасности
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Получаем из БД юзера, того, кто принципал (это нужно для того, что
        //у авторизированного пользователя не будут заполнены поля имя и т.п.
        User user = userService.getUserById(authUser.getUserId());

        //если пользователь - админ, переходим на нужную страницу
        if (user.getAdminRole().equals("ADMIN")){
            request.setAttribute("user", user);
            return "adminModule";
        }

        //если роль пользователя - USER,
        //добавляем авторизованного пользователя в модель представления
        model.addAttribute("user", user);

        //если у пользователя есть незаполненные поля - отправляем его дальше регистрироваться
        if (user.getName() == null || user.getName().equals("") || user.getEmail() == null || user.getEmail().equals("")) {
            //получаем из бд список должностей и передаем в модель представления
            List<Position> listPosition = positionService.getAllPosition();
            model.addAttribute("listPosition", listPosition);
            return "greeting";
        }

        session.setAttribute("allTestGroup",testService.getAllGroupTest());
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
    public String reUpdate(@ModelAttribute User user, BindingResult bindingResult, Model model, HttpServletRequest request){
        userDopRegistrationValidator.validate(user, bindingResult);

        String pos_id = request.getParameter("pos_id");

        Position position = positionService.getPositionById(Integer.valueOf(pos_id));
        user.setPosition(position);

        //регистрируем пользователя
        userService.updateUser(user);
        //отправляем письмо
        UserService.sendEmail(user, 1);

        return "confirmEmail";

    }

    @GetMapping("/confirmEmail")
    public String confirmEmail(@RequestParam("userId") Integer userId, @RequestParam("key") String key, Model model){

        if (userService.getUserById(userId) == null){
            return "confirmEmailNotSuccess";
        }

        User user = userService.getUserById(userId);
        //был ли адрес подтвержден?
        if (user.isRegistered() == null){
            //проверка uuid пользователя
            if (userService.checkUuid(userId, key)){
                model.addAttribute("user", user);
                return "confirmEmailSuccess";
            }
            else {
                return "confirmEmailNotSuccess";
            }
        }
        else {
            model.addAttribute("user", user);
            return "confirmEmailAlready";
        }
    }

    @GetMapping("/room")
    public String enteringRoom(Model model){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserById(principal.getUserId());
        List<Position> positionList = positionService.getAllPosition();
        model.addAttribute("positionList", positionList);
        model.addAttribute("user", user);
        return "userRoom";
    }

    @PostMapping("editUser")
    public String editUser(@ModelAttribute User user, HttpServletRequest request){
        String pos_id = request.getParameter("pos_id");
        Position position = positionService.getPositionById(Integer.valueOf(pos_id));
        user.setPosition(position);
        userService.updateUser(user);
        return "redirect:/logout";
    }

    @GetMapping("changePassword")
    public String changePassword(HttpServletRequest request){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", user);
        return "changePassword";
    }

    @PostMapping("changePassword")
    public String changePassword(@RequestParam String password){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.changePassword(user.getUserId(), password);
        return "redirect:/room";
    }

    @GetMapping("/rememberPassword")
    public String rememberPassword(){
        return "rememberPassword";
    }

    @GetMapping("/rememberPassProcess")
    public String sendInfoForRememberUserPassword(@RequestParam String email, HttpServletRequest request){
        User user = userService.getUserByEmail(email);
        if (user == null){
            String text = "Пользователь с email '" + email +"' не зарегистрирован!";
            request.setAttribute("text", text);
        }
        else {
            if (user.isRegistered() == null){
                String text = "Пользователь с email '" + email +"' найден, но email не подтвержден! " +
                        "Мы повторно отправили Вам письмо для подтверждения email. Перейдите по ссылке в письме " +
                        "и после того, как подтвердите email повторно пройдите процедуру восстановления пароля!";
                request.setAttribute("text", text);

            }
            else {
                UserService.sendEmail(user, 2);
                String text = "На адрес Вашей электронной почты отправлено письмо, содержащее дальнейшие инструкции";
                request.setAttribute("text", text);
            }
        }

        return "info";
    }

    @GetMapping("/recovery")
    public String recoveryPassword(@RequestParam Integer userId, HttpSession session){
        User user = userService.getUserById(userId);
        session.setAttribute("user", user);
        return "changePasswordForUser";
    }

    @PostMapping("changePasswordForUser")
    public String changePasswordForUser(@RequestParam String password, HttpSession session){
        User user = (User) session.getAttribute("user");
        userService.changePassword(user.getUserId(), password);
        return "login";
    }
}
