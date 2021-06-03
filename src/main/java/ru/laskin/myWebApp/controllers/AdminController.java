package ru.laskin.myWebApp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.laskin.myWebApp.model.*;
import ru.laskin.myWebApp.service.PositionService;
import ru.laskin.myWebApp.service.TestService;
import ru.laskin.myWebApp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final PositionService positionService;
    private final TestService testService;


    public AdminController(UserService userService, PositionService positionService, TestService testService) {
        this.userService = userService;
        this.positionService = positionService;
        this.testService = testService;
    }

    @GetMapping("/allUsers")
    public String getStart(Model model, HttpServletRequest request) {
        model.addAttribute("users", userService.getAllUsers());
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("authUser", authUser.getName());
        request.setAttribute("user", authUser);
        return "list_users";
    }

    @GetMapping("/users/update")
    public String updateUser(HttpServletRequest request, Model model){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);

        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        List<Position> positionSet = positionService.getAllPosition();
        model.addAttribute("posSet", positionSet);
        return"updateForm";
    }

    @GetMapping("/users/delete")
    public String deleteUser(HttpServletRequest request){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = Integer.parseInt(request.getParameter("id"));
        userService.deleteUser(id);
        request.setAttribute("user", authUser);
        return "redirect:/allUsers";
    }

    @PostMapping("/updateUser")
    public String formUser (@ModelAttribute User user, HttpServletRequest request){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);
            userService.updateUser(user);
            return "redirect:/allUsers";
    }

    @GetMapping("users/statistic")
    public String statisticOfUser(HttpServletRequest request, @RequestParam Integer id){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);
        testService.getStatistic(request, id);
        return "statistic";
    }

    @GetMapping("/adminModule")
    public String openAdminModule(HttpServletRequest request){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);
        return "adminModule";
    }

    @GetMapping("/allTests")
    public String showAllTests(Model model, HttpServletRequest request){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);

        model.addAttribute("alltests", testService.getAllTests());
        model.addAttribute("allgrouptest", testService.getAllGroupTest());
        model.addAttribute("test", new Test());
        return "list_tests";
    }

    @GetMapping("/tests/update")
    public String showEditTestForm(@RequestParam Integer id, Model model, HttpServletRequest request){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);

        model.addAttribute("test", testService.getTestById(id));
        model.addAttribute("questions", testService.getTestById(id).getQuestions());
        List<Answer> newAnswerList = new ArrayList<>();
        model.addAttribute("newAnswerList", newAnswerList);
        return "edittest";
    }

    /*  Этот контроллер собирает ListQuestion и ListAnswers для Test
        и отправляет Test в БД для обновления                            */
    @PostMapping("/updateTest")
    public String updateTest(@ModelAttribute Test test, HttpServletRequest request){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);

        testService.updateTest(test, request.getParameterMap());
        return "redirect:/allTests";
    }

    @GetMapping("/tests/delete")
    public String deleteTest(@RequestParam Integer id, HttpServletRequest request){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);

        testService.deleteTestById(id);
        return "redirect:/allTests";
    }

    @PostMapping("/addQuestion")
        public void addQuestion(@ModelAttribute Question question, HttpServletRequest request, HttpServletResponse response){

        //получаем из БД question по id
        int testId = Integer.parseInt(request.getParameter("IDTest"));
        question.setTest(testService.getTestById(testId));

        //получаем из post запроса массивы значений полей
        String[] answersName = request.getParameterValues("answerName");
        String[] rightAnswer = request.getParameterValues("isRight");

        testService.saveQuestion(question, answersName, rightAnswer);

        try {
            response.sendRedirect("/tests/update?id=" + testId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/addTest")
    public String addTest(@ModelAttribute Test test, @RequestParam String groupId){
        test.setGroupTest(testService.getGroupTestById(Integer.parseInt(groupId)));
        testService.saveTest(test);
        return "redirect:/allTests";
    }

    @GetMapping("/groupTests")
    public String groupTests(Model model, HttpServletRequest request){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);

        model.addAttribute("groupTests", testService.getAllGroupTest());
        return "allGroup";
    }

    @GetMapping("/group/delete")
    public String deleteGroup(@RequestParam Integer id){
        testService.deleteGroupTest(id);
        return "redirect:/groupTests";
    }

    @PostMapping("/addGroup")
    public String addGroup(@ModelAttribute GroupTest groupTest){
        testService.addGroup(groupTest);
        return "redirect:/groupTests";
    }

    @PostMapping("/updateGroup")
    public String updateGroup(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] id = parameterMap.get("grouptestId");
        String[] name = parameterMap.get("name");

        List<GroupTest> groupTests = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            GroupTest groupTest = new GroupTest();
            groupTest.setGroupTestId(Integer.parseInt(id[i]));
            groupTest.setName(name[i]);
            groupTest.setTestList(testService.getTestsByGroupId(Integer.parseInt(id[i])));


            groupTests.add(groupTest);
        }

        testService.updateAllGroup(groupTests);
        return "redirect:/greeting";
    }
}
