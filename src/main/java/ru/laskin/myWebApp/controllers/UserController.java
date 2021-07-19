package ru.laskin.myWebApp.controllers;

import org.codehaus.plexus.util.ExceptionUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(ResultController.class.getName());

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
        logger.info("Вход");
        //получаем авторизованного пользователя (принципала) из контекста безопасности
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("принципал " + authUser.getName());
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
            logger.info("продолжение регистрации " + user.getName());
            //получаем из бд список должностей и передаем в модель представления
            List<Position> listPosition = positionService.getAllPosition();
            model.addAttribute("listPosition", listPosition);
            logger.info("завершил регистрацию " + user.getName());
            return "greeting";
        }

        session.setAttribute("allTestGroup",testService.getAllGroupTest());
        logger.info("выход");
        return "testPage";
    }

    @GetMapping("/new_user")
    public String formUser(Model model){
        logger.info("вход");
        //передаем пустого пользователя, чтобы там его наполнить
        model.addAttribute("user", new User());
        logger.info("выход");
        return "registration";
    }

    @PostMapping("/new_user")
    public String formUser (HttpServletRequest request, @ModelAttribute User user, BindingResult bindingResult) {
        logger.info("вход");
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()){
            logger.info("вернулась ошибка регистрации пользователя");
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
            logger.severe("ошибка автологина. см. стек");
            logger.log(Level.SEVERE, ExceptionUtils.getStackTrace(e));
        }
        logger.info("выход");
        return "redirect:greeting";
    }

    @PostMapping("/reUpdate")
    public String reUpdate(@ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request){
        logger.info("вход");
        userDopRegistrationValidator.validate(user, bindingResult);
        String pos_id = request.getParameter("pos_id");

        Position position = positionService.getPositionById(Integer.valueOf(pos_id));
        user.setPosition(position);

        user.setEmail(user.getEmail().toLowerCase(Locale.ROOT));

        //регистрируем пользователя
        userService.updateUser(user);
        //отправляем письмо
        UserService.sendEmail(user, 1);
        logger.info("выход");
        return "confirmEmail";
    }

    @GetMapping("/confirmEmail")
    public String confirmEmail(@RequestParam("userId") Integer userId, @RequestParam("key") String key, Model model){
        logger.info("вход");
        if (userService.getUserById(userId) == null){
            logger.info("выход. userId == null");
            return "confirmEmailNotSuccess";
        }

        User user = userService.getUserById(userId);
        //был ли адрес подтвержден?
        if (user.isRegistered() == null){
            //проверка uuid пользователя
            if (userService.checkUuid(userId, key)){
                model.addAttribute("user", user);
                logger.info("выход. проверка uuid пользователя проведена");
                return "confirmEmailSuccess";
            }
            else {
                logger.info("выход. проверка uuid пользователя не успешно");
                return "confirmEmailNotSuccess";
            }
        }
        else {
            model.addAttribute("user", user);
            logger.info("выход");
            return "confirmEmailAlready";
        }
    }

    @GetMapping("/room")
    public String enteringRoom(Model model){
        logger.info("вход");
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserById(principal.getUserId());
        List<Position> positionList = positionService.getAllPosition();
        model.addAttribute("positionList", positionList);
        model.addAttribute("user", user);
        logger.info("выход");
        return "userRoom";
    }

    @PostMapping("editUser")
    public String editUser(@ModelAttribute User user, HttpServletRequest request){
        logger.info("вход");
        String pos_id = request.getParameter("pos_id");
        Position position = positionService.getPositionById(Integer.valueOf(pos_id));
        user.setPosition(position);
        userService.updateUser(user);
        logger.info("выход");
        return "redirect:/logout";
    }

    @GetMapping("changePassword")
    public String changePassword(HttpServletRequest request){
        logger.info("вход");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", user);
        logger.info("выход");
        return "changePassword";
    }

    @PostMapping("changePassword")
    public String changePassword(@RequestParam String password){
        logger.info("вход");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.changePassword(user.getUserId(), password);
        logger.info("выход");
        return "redirect:/room";
    }

    @GetMapping("/rememberPassword")
    public String rememberPassword(){
        logger.info("вход и выход");
        return "rememberPassword";
    }

    @GetMapping("/rememberPassProcess")
    public String sendInfoForRememberUserPassword(@RequestParam String email, HttpServletRequest request){
        logger.info("вход");
        User user = userService.getUserByEmail(email);
        if (user == null){
            String text = "Пользователь с email '" + email +"' не зарегистрирован в системе!";
            request.setAttribute("text", text);
        }
        else {
            if (user.isRegistered() == null){
                UserService.sendEmail(user, 1);
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
        logger.info("выход");
        return "info";
    }

    @GetMapping("/recovery")
    public String recoveryPassword(@RequestParam Integer userId, HttpSession session){
        logger.info("вход");
        User user = userService.getUserById(userId);
        session.setAttribute("user", user);
        logger.info("выход");
        return "changePasswordForUser";
    }

    @PostMapping("changePasswordForUser")
    public String changePasswordForUser(@RequestParam String password, HttpSession session){
        logger.info("вход");
        User user = (User) session.getAttribute("user");
        userService.changePassword(user.getUserId(), password);
        logger.info("выход");
        return "login";
    }
}
