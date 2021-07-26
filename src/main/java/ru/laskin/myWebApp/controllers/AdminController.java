package ru.laskin.myWebApp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.laskin.myWebApp.model.Position;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.PositionService;
import ru.laskin.myWebApp.service.TestService;
import ru.laskin.myWebApp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private static final Logger log = Logger.getLogger(AdminController.class.getName());

    private final UserService userService;
    private final PositionService positionService;
    private final TestService testService;
    private final ExceptionController exceptionController;


    public AdminController(UserService userService, PositionService positionService, TestService testService,
                           ExceptionController exceptionController) {
        this.userService = userService;
        this.positionService = positionService;
        this.testService = testService;
        this.exceptionController = exceptionController;
    }

    @GetMapping("/allUsers")
    public String getAllUsers(Model model, HttpServletRequest request) {
        log.info("Вход");
        try {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("authUser", authUser.getName());
            request.setAttribute("user", authUser);
            model.addAttribute("users", userService.getAllUsers(authUser.getCompany().getIdCompany()));
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "list_users";
    }

    @GetMapping("/users/update")
    public String updateUser(HttpServletRequest request, Model model){
        log.info("Вход");
        try {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            request.setAttribute("user", authUser);

            int id = Integer.parseInt(request.getParameter("id"));
            User user = userService.getUserById(id);
            model.addAttribute("user", user);
            List<Position> positionSet = positionService.getAllPosition(user.getCompany().getIdCompany());
            model.addAttribute("posSet", positionSet);
            log.info("Выход");
        } catch (NumberFormatException e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return"updateForm";
    }

    @GetMapping("/users/delete")
    public String deleteUser(HttpServletRequest request, @RequestParam Integer id){
        log.info("Вход");
        try {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userService.deleteUser(id);
            request.setAttribute("user", authUser);
            log.info("Выход");
        } catch (NumberFormatException e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:/allUsers";
    }

    @PostMapping("/updateUser")
    public String formUser (@ModelAttribute User user, HttpServletRequest request){
        log.info("Вход");
        try {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            request.setAttribute("user", authUser);
            userService.updateUser(user);
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:/allUsers";
    }

    @GetMapping("/adminModule")
    public String openAdminModule(HttpServletRequest request){
        log.info("Вход");
        try {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            request.setAttribute("user", authUser);
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "adminModule";
    }

    @GetMapping("/allTests")
    public String showAllTests(Model model, HttpServletRequest request){
        log.info("Вход");
        try {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            request.setAttribute("user", authUser);

            model.addAttribute("allgrouptest", testService.getAllGroupTest(authUser.getCompany().getIdCompany()));
            model.addAttribute("test", new Test());
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "list_tests";
    }

    @GetMapping("/tests/update")
    public String showEditTestForm(@RequestParam Integer id, Model model, HttpServletRequest request, HttpSession session){
        log.info("Вход");
        try {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            request.setAttribute("user", authUser);

            model.addAttribute("test", testService.getTestById(id));
            session.setAttribute("questions", testService.getTestById(id).getQuestions());
            model.addAttribute("newAnswerList", new ArrayList<>());
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "edittest";
    }

    @PostMapping("/updateTest")
    public String updateTest(@ModelAttribute Test test, HttpServletRequest request, HttpSession session){
        try {
            log.info("Вход");
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            request.setAttribute("user", authUser);

            testService.updateTest(test, request, session);
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:/allTests";
    }

    @GetMapping("/tests/delete")
    public String deleteTest(@RequestParam Integer id, HttpServletRequest request){
        log.info("Вход");
        try {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            request.setAttribute("user", authUser);

            testService.deleteTestById(id);
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:/allTests";
    }

    @PostMapping("/addQuestion")
        public void addQuestion(@ModelAttribute Question question,
                                @RequestParam (name = "IDTest") Integer testId,
                                @RequestParam (name = "answerName") String[] answersName,
                                @RequestParam (name = "isRight") String[] rightAnswer,
                                HttpServletRequest request, HttpServletResponse response){
        log.info("Вход");
        try {
            testService.saveQuestion(question, answersName, rightAnswer, testId);
            response.sendRedirect("/tests/update?id=" + testId);
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
        }
    }
}
