package ru.laskin.myWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
public class TestController {

    private final ResultTestService resultTestService;
    private final TestService testService;


    public TestController(ResultTestService resultTestService, TestService testService,
                          UserService userService, AttemptTestService attemptTestService) {
        this.resultTestService = resultTestService;
        this.testService = testService;
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
                             HttpServletRequest request){
        testService.mainCheck(request, attemptId, testId, userId);
        return "testResult";
    }
}
