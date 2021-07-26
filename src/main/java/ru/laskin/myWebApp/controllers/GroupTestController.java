package ru.laskin.myWebApp.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.laskin.myWebApp.dao.CompanyDao;
import ru.laskin.myWebApp.model.GroupTest;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.TestService;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
public class GroupTestController {
    private static final Logger log = Logger.getLogger(AdminController.class.getName());

    private final TestService testService;
    private final ExceptionController exceptionController;
    private final CompanyDao companyDao;

    public GroupTestController(TestService testService, ExceptionController exceptionController, CompanyDao companyDao) {
        this.testService = testService;
        this.exceptionController = exceptionController;
        this.companyDao = companyDao;
    }

    @PostMapping("/addTest")
    public String addTest(@ModelAttribute Test test, @RequestParam String groupId, HttpServletRequest request){
        log.info("Вход");
        int id;
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

            model.addAttribute("groupTests", testService.getAllGroupTest(authUser.getCompany().getIdCompany()));
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
        groupTest.setCompany(companyDao.getCompanyById(authUser.getCompany().getIdCompany()));
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
}
