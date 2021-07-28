package ru.laskin.myWebApp.controllers.userModule;

import org.codehaus.plexus.util.ExceptionUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.laskin.myWebApp.controllers.ExceptionController;
import ru.laskin.myWebApp.model.Company;
import ru.laskin.myWebApp.model.Position;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.CompanyService;
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
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class UserThemselfController {
    private static final Logger log = Logger.getLogger(ResultController.class.getName());

    private final UserService userService;
    private final PositionService positionService;
    private final TestService testService;
    private final UserValidator userValidator;
    private final UserDopRegistrationValidator userDopRegistrationValidator;
    private final ExceptionController exceptionController;
    private final CompanyService companyService;


    public UserThemselfController(UserService userService, PositionService positionService,
                                  TestService testService, UserValidator userValidator,
                                  UserDopRegistrationValidator userDopRegistrationValidator,
                                  ExceptionController exceptionController, CompanyService companyService) {
        this.userService = userService;
        this.positionService = positionService;
        this.testService = testService;
        this.userValidator = userValidator;
        this.userDopRegistrationValidator = userDopRegistrationValidator;
        this.exceptionController = exceptionController;
        this.companyService = companyService;
    }

    @GetMapping("/greeting")
    public String greeting(Model model, HttpServletRequest request, HttpSession session){
        log.info("Вход");
        //получаем авторизованного пользователя (принципала) из контекста безопасности
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("принципал " + authUser.getName());

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
            log.info("продолжение регистрации " + user.getName());
            //получаем из бд список компаний и передаем в модель представления
//            List<Position> listPosition = positionService.getAllPosition(user.getCompany().getIdCompany());
            List<Company> companyList = companyService.getAllCompanies();
            model.addAttribute("companyList", companyList);
            log.info("завершил регистрацию " + user.getName());
            return "greeting";
        }

        session.setAttribute("allTestGroup",testService.getAllGroupTest(user.getCompany().getIdCompany()));
        log.info("выход");
        return "testPage";
    }

    @GetMapping("/new_user")
    public String newUser(Model model){
        log.info("вход");
        //передаем пустого пользователя, чтобы там его наполнить
//        model.addAttribute("user", new User());
        log.info("выход");
        return "registration";
    }

    @PostMapping("/new_user")
    public String newUser(HttpServletRequest request,
                          @RequestParam (required = false) String admin,
                          @RequestParam String login,
                          @RequestParam String password,
                          @RequestParam String confirmPassword,
                          @RequestParam String companyName) {
        log.info("вход");
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setConfirmPassword(confirmPassword);

        Map<String, String> errorMap = userValidator.validate(user);
        if (!errorMap.isEmpty()){
            log.info("вернулась ошибка регистрации пользователя");
            request.setAttribute("loginError", errorMap.get("loginError"));
            request.setAttribute("confirmPassword", errorMap.get("confirmPassword"));
            return "registration";
        }


        //проверяем нового пользователя на admin
        if (admin != null && admin.equals("on")){
            user.setAdminRole("ADMIN");
        }
        else {
            user.setAdminRole("USER");
        }

        userService.saveUser(user);

        //автологин, если регистрация прошла успешно
        try {
            request.login(login, password);
        } catch (ServletException e) {
            log.severe("ошибка автологина. см. стек");
            log.log(Level.SEVERE, ExceptionUtils.getStackTrace(e));
        }
        log.info("выход");
        return "redirect:greeting";
    }

    @PostMapping("/reUpdate")
    public String reUpdate(@ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request,
                           @RequestParam Integer pos_id,
                           @RequestParam Integer company_id){
        log.info("вход");
        userDopRegistrationValidator.validate(user, bindingResult);

        Position position = positionService.getPositionById(pos_id);
        user.setPosition(position);

        Company company = companyService.getCompanyById(company_id);
        user.setCompany(company);

        user.setEmail(user.getEmail().toLowerCase(Locale.ROOT));

        //регистрируем пользователя
        userService.updateUser(user);
        //отправляем письмо
        UserService.sendEmail(user, 1);
        log.info("выход");
        return "confirmEmail";
    }

    @GetMapping("/confirmEmail")
    public String confirmEmail(@RequestParam("userId") Integer userId, @RequestParam("key") String key, Model model){
        log.info("вход");
        if (userService.getUserById(userId) == null){
            log.info("выход. userId == null");
            return "confirmEmailNotSuccess";
        }

        User user = userService.getUserById(userId);
        //был ли адрес подтвержден?
        if (user.isRegistered() == null){
            //проверка uuid пользователя
            if (userService.checkUuid(userId, key)){
                model.addAttribute("user", user);
                log.info("выход. проверка uuid пользователя проведена");
                return "confirmEmailSuccess";
            }
            else {
                log.info("выход. проверка uuid пользователя не успешно");
                return "confirmEmailNotSuccess";
            }
        }
        else {
            model.addAttribute("user", user);
            log.info("выход");
            return "confirmEmailAlready";
        }
    }

    @GetMapping("/room")
    public String enteringRoom(Model model){
        log.info("вход");
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserById(principal.getUserId());
        List<Position> positionList = positionService.getAllPosition(user.getCompany().getIdCompany());
        model.addAttribute("positionList", positionList);
        model.addAttribute("user", user);
        log.info("выход");
        return "userRoom";
    }

    @PostMapping("editUser")
    public String editUser(@ModelAttribute User user, HttpServletRequest request){
        log.info("вход");
        String pos_id = request.getParameter("pos_id");
        Position position = positionService.getPositionById(Integer.valueOf(pos_id));
        user.setPosition(position);
        userService.updateUser(user);
        log.info("выход");
        return "redirect:/logout";
    }

    @GetMapping("changePassword")
    public String changePassword(HttpServletRequest request){
        log.info("вход");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", user);
        log.info("выход");
        return "changePassword";
    }

    @PostMapping("changePassword")
    public String changePassword(@RequestParam String password){
        log.info("вход");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.changePassword(user.getUserId(), password);
        log.info("выход");
        return "redirect:/room";
    }

    @GetMapping("/rememberPassword")
    public String rememberPassword(){
        log.info("вход и выход");
        return "rememberPassword";
    }

    @GetMapping("/rememberPassProcess")
    public String sendInfoForRememberUserPassword(@RequestParam String email, HttpServletRequest request){
        log.info("вход");
        String text = userService.prepareEmailTextForUser(email);
        request.setAttribute("text", text);
        log.info("выход");
        return "info";
    }

    @GetMapping("/recovery")
    public String recoveryPassword(@RequestParam Integer userId, @RequestParam String key, HttpServletRequest request){
        log.info("вход");
        request.setAttribute("userId", userId);
        request.setAttribute("key", key);
        log.info("выход");
        return "changePasswordForUser";
    }

    @PostMapping("/recovery")
    public String changePasswordForUser(@RequestParam String password,
                                        @RequestParam Integer userId,
                                        @RequestParam UUID key,
                                        HttpServletRequest request){

        Map<String, String[]> map = request.getParameterMap();
        log.info("вход");
        try {
            User user = userService.getUserById(userId);
            if (user.getKey().equals(key)){
                userService.changePassword(user.getUserId(), password);
                log.info("выход");
            }
            else throw new Exception("Ошибка смены пароля. Проверка ID пользователя и его KEY не успешна.");

        } catch (Exception e) {
            exceptionController.printException(request,  log, e);
            return "exception";
        }
        return "login";
    }

}
