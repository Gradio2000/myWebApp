package ru.laskin.myWebApp.controllers.userModule;

import org.codehaus.plexus.util.ExceptionUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.laskin.myWebApp.controllers.ExceptionController;
import ru.laskin.myWebApp.model.Company;
import ru.laskin.myWebApp.model.Position;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.CompanyService;
import ru.laskin.myWebApp.service.PositionService;
import ru.laskin.myWebApp.service.TestService;
import ru.laskin.myWebApp.service.UserService;
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
    private final ExceptionController exceptionController;
    private final CompanyService companyService;


    public UserThemselfController(UserService userService, PositionService positionService,
                                  TestService testService, UserValidator userValidator,
                                  ExceptionController exceptionController, CompanyService companyService) {
        this.userService = userService;
        this.positionService = positionService;
        this.testService = testService;
        this.userValidator = userValidator;
        this.exceptionController = exceptionController;
        this.companyService = companyService;
    }

    @GetMapping("/greeting")
    public String greeting(Model model, HttpSession session, HttpServletRequest request){
        log.info("Вход");
        try {
            //получаем авторизованного пользователя (принципала) из контекста безопасности
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("принципал " + authUser.getName());

            //Получаем из БД юзера, того, кто принципал (это нужно для того, что
            //у авторизированного пользователя не будут заполнены поля имя и т.п.
            User user = userService.getUserById(authUser.getUserId());

            //если роль пользователя - USER,
            //добавляем авторизованного пользователя в модель представления
            model.addAttribute("user", user);

            //если у пользователя есть незаполненные поля - отправляем его дальше регистрироваться
            if (user.getName() == null || user.getName().equals("") || user.getEmail() == null || user.getEmail().equals("")) {
                log.info("продолжение регистрации " + user.getName());
                //получаем из бд список компаний и передаем в модель представления
                List<Company> companyList = companyService.getAllCompanies();
                model.addAttribute("companyList", companyList);
                log.info(user.getName() + "завершает регистрацию");
                return "greeting";
            }

            //если пользователь - админ,
            if (user.getAdminRole().equals("ADMIN")){
                return "adminModule";
            }

            session.setAttribute("allTestGroup",testService.getAllGroupTest(user.getCompany().getIdCompany()));
            log.info("выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "testPage";
    }

    @GetMapping("/new_user")
    public String newUser(){
        log.info("вход");
        log.info("выход");
        return "registration";
    }

    @PostMapping("/new_user")
    public String newUser(Model model, HttpServletRequest request,
                          @RequestParam (required = false) String admin,
                          @RequestParam String login,
                          @RequestParam String password,
                          @RequestParam String confirmPassword,
                          @RequestParam (required = false) String companyName) {
        log.info("вход");

        try {
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
                Company company = null;
                if (companyName != null){
                 company = companyService.saveCompany(companyName);
                }
                user.setCompany(company);
                assert company != null;
                Position position = positionService.getAllPosition(company.getIdCompany()).get(0);
                user.setPosition(position);
                userService.saveUser(user);
                model.addAttribute("user", user);
                return "greetingAdmin";
            }
            else {
                user.setAdminRole("USER");
                userService.saveUser(user);
            }

            //автологин, если регистрация прошла успешно
            try {
                request.login(login, password);
            } catch (ServletException e) {
                log.severe("ошибка автологина. см. стек");
                log.log(Level.SEVERE, ExceptionUtils.getStackTrace(e));
            }
            log.info("выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:greeting";
    }

    @PostMapping("/reUpdate")
    public String reUpdate(@ModelAttribute User user, BindingResult bindingResult, //пытался убрать binding - выдает ошибку 400
                           @RequestParam Integer pos_id,
                           @RequestParam Integer company_id,
                           HttpServletRequest request){
        log.info("вход");

        try {
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
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "confirmEmail";
    }

    @GetMapping("/confirmEmail")
    public String confirmEmail(@RequestParam Integer userId, @RequestParam String key, Model model){
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
    public String enteringRoom(Model model, HttpServletRequest request){
        log.info("вход");
        try {
            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.getUserById(principal.getUserId());
            List<Position> positionList = positionService.getAllPositionWithoutAdminRole(user.getCompany().getIdCompany());
            model.addAttribute("positionList", positionList);
            model.addAttribute("user", user);
            log.info("выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "userRoom";
    }

    @PostMapping("editUser")
    public String editUser(@ModelAttribute User user,
                           @RequestParam Integer pos_id,
                           @RequestParam Integer company_id,
                           HttpServletRequest request){
        log.info("вход");
        try {
            Position position = positionService.getPositionById(pos_id);
            Company company = companyService.getCompanyById(company_id);
            user.setPosition(position);
            user.setCompany(company);
            userService.updateUser(user);
            log.info("выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:/logout";
    }

    @GetMapping("changePassword")
    public String changePassword(HttpServletRequest request){
        try {
            log.info("вход");
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            request.setAttribute("user", user);
            log.info("выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "changePassword";
    }

    @PostMapping("changePassword")
    public String changePassword(@RequestParam String password, HttpServletRequest request){
        log.info("вход");
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userService.changePassword(user.getUserId(), password);
            log.info("выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
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
        try {
            String text = userService.prepareEmailTextForUser(email);
            request.setAttribute("text", text);
            log.info("выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "info";
    }

    @GetMapping("/recovery")
    public String recoveryPassword(@RequestParam Integer userId, @RequestParam String key, HttpServletRequest request){
        log.info("вход");
        try {
            request.setAttribute("userId", userId);
            request.setAttribute("key", key);
            log.info("выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "changePasswordForUser";
    }

    @PostMapping("/recovery")
    public String changePasswordForUser(@RequestParam String password,
                                        @RequestParam Integer userId,
                                        @RequestParam UUID key,
                                        HttpServletRequest request){
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
