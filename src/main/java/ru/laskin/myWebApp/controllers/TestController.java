package ru.laskin.myWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.laskin.myWebApp.service.*;

import javax.servlet.http.HttpServletRequest;


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

    @PostMapping("/oper")
    public String saveUserAnswer(HttpServletRequest request,
                                 @RequestParam Integer questionId,
                                 @RequestParam Integer attemptId){
        resultTestService.saveResultTest(request, questionId, attemptId);
        return "testProcessing";
    }

    @GetMapping("/finish")
    public String testFinish(@RequestParam Integer attemptId,
                             @RequestParam Integer testId,
                             @RequestParam Integer userId,
                             @RequestParam Integer timeOfAttempt,
                             HttpServletRequest request){
        attemptTestService.saveTimeOfAttempt(attemptId, timeOfAttempt);
        testService.mainCheck(request, attemptId, testId, userId);
        return "testResult";
    }
}
