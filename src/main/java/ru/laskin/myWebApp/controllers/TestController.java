package ru.laskin.myWebApp.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.laskin.myWebApp.model.AttemptTest;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;


@Controller
public class TestController {

    private final ResultTestService resultTestService;
    private final TestService testService;
    private final AttemptTestService attemptTestService;


    public TestController(ResultTestService resultTestService, TestService testService, AttemptTestService attemptTestService) {
        this.resultTestService = resultTestService;
        this.testService = testService;
        this.attemptTestService = attemptTestService;
    }

    @GetMapping("/getTest")
    public String testStart(@RequestParam String testId, @RequestParam(required = false) Integer attemptId,
                            Model model, HttpServletRequest request, HttpSession session){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);

        Test test = testService.getTestById(Integer.parseInt(testId));

        if (test.getQuesAmount() != null){
            //выбрать случайные вопросы в заданном в настройках теста количестве
            test = testService.getShuffleTest(test);
        }

        Map<String, String[]> map = request.getParameterMap();
        //записываем в БД новую попытку
        if (attemptId == null) {
            Timestamp timestamp = new Timestamp(new Date().getTime());
            AttemptTest attemptTest = new AttemptTest(timestamp, test.getTestId(), authUser.getUserId(), 0, test.getQuestions().size());
            attemptId = attemptTestService.saveAttemptTest(attemptTest);
        }

        //записываем в бд заданные юзеру вопросы
        testService.registerTest(attemptId, test);

        session.setAttribute("tests", test);
        model.addAttribute("users", authUser);
        model.addAttribute("attemptId", attemptId);
        return "testProcessing";
    }

    @PostMapping("/oper")
    public String saveUserAnswer(HttpServletRequest request,
                                 @RequestParam Integer questionId,
                                 @RequestParam Integer attemptId){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);

        resultTestService.saveResultTest(request, questionId, attemptId);
        return "testProcessing";
    }

}
