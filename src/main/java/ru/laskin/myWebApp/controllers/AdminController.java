package ru.laskin.myWebApp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.laskin.myWebApp.model.*;
import ru.laskin.myWebApp.service.PositionService;
import ru.laskin.myWebApp.service.TestService;
import ru.laskin.myWebApp.service.UserService;
import ru.laskin.myWebApp.validation.DeletePositionValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private static Logger log = Logger.getLogger(AdminController.class.getName());

    private final UserService userService;
    private final PositionService positionService;
    private final TestService testService;
    private final DeletePositionValidation deletePositionValidation;
    private final ExceptionController exceptionController;


    public AdminController(UserService userService, PositionService positionService, TestService testService,
                           DeletePositionValidation deletePositionValidation, ExceptionController exceptionController) {
        this.userService = userService;
        this.positionService = positionService;
        this.testService = testService;
        this.deletePositionValidation = deletePositionValidation;
        this.exceptionController = exceptionController;
    }

    @GetMapping("/allUsers")
    public String getAllUsers(Model model, HttpServletRequest request) {
        log.info("Вход");
        try {
            model.addAttribute("users", userService.getAllUsers());
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("authUser", authUser.getName());
            request.setAttribute("user", authUser);
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
            List<Position> positionSet = positionService.getAllPosition();
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

            model.addAttribute("allgrouptest", testService.getAllGroupTest(authUser.getCompany_id()));
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

    @PostMapping("/addTest")
    public String addTest(@ModelAttribute Test test, @RequestParam String groupId, HttpServletRequest request){
        log.info("Вход");
        int id = 0;
        try {
            test.setGroupTest(testService.getGroupTestById(Integer.parseInt(groupId)));
            id = testService.saveTest(test);
            log.info("Выход");
        } catch (NumberFormatException e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:/tests/update?id=" + id;
    }

    @GetMapping("/groupTests")
    public String groupTests(Model model, HttpServletRequest request){
        log.info("Вход");
        try {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            request.setAttribute("user", authUser);

            model.addAttribute("groupTests", testService.getAllGroupTest(authUser.getCompany_id()));
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "allGroup";
    }

    @GetMapping("/group/delete")
    public String deleteGroup(@RequestParam Integer id, HttpServletRequest request){
        log.info("Вход");
        try {
            testService.deleteGroupTest(id);
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:/groupTests";
    }

    @PostMapping("/addGroup")
    public String addGroup(@ModelAttribute GroupTest groupTest, HttpServletRequest request){
        log.info("Вход");
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        groupTest.setCompanyId(authUser.getCompany_id());
        try {
            testService.addGroup(groupTest);
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:/groupTests";
    }

    @PostMapping("/updateGroup")
    public String updateGroup(@RequestParam(name = "grouptestId") Integer[] id,
                              @RequestParam(name = "name") String[] name,
                              @RequestParam(name = "companyId") Integer[] companyId,
                              HttpServletRequest request){
        log.info("Вход");
        try {
            testService.updateAllGroup(id, name, companyId);
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:/greeting";
    }

    @GetMapping("/allPosition")
    public String getAllPositions(Model model, HttpServletRequest request, HttpSession session){
        log.info("Вход");
        try {
            session.setAttribute("positions", positionService.getAllPosition());
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("authUser", authUser.getName());
            request.setAttribute("user", authUser);
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "list_positions";
    }

    @PostMapping("/editPosition")
    public String editPositions(HttpServletRequest request, HttpSession session){
        log.info("Вход");
        try {
            positionService.updateAllPosition(request, session);
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:/greeting";
    }

    @GetMapping("/deletePosition")
    public String deletePosition(@RequestParam String posId, HttpServletRequest request){
        log.info("Вход");
        try {
            Position position = positionService.getPositionById(Integer.parseInt(posId));
            if (deletePositionValidation.validate(position)){
                request.setAttribute("errorMessage", "Внимание! Эту должность удалять нельзя, т.к. за ней закреплен пользователь!");
                return "list_positions";
            }
            positionService.deletePosition(Integer.parseInt(posId));
            log.info("Выход");
        } catch (NumberFormatException e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:/allPosition";
    }

    @PostMapping("/addPosition")
    public String addPosition(@ModelAttribute Position pos, HttpServletRequest request){
        log.info("Вход");
        try {
            positionService.addPosition(pos);
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:/allPosition";
    }
}
