package ru.laskin.myWebApp.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.AttemptTestService;
import ru.laskin.myWebApp.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@SessionAttributes("time")
public class ResultController {

    private final TestService testService;
    private final AttemptTestService attemptTestService;

    public ResultController( TestService testService, AttemptTestService attemptTestService) {
        this.testService = testService;
        this.attemptTestService = attemptTestService;
    }

    @RequestMapping(value = "/finish", method = RequestMethod.GET)
    public String testFinish(@RequestParam Integer attemptId,
                             @RequestParam Integer testId,
                             @RequestParam Integer userId,
                             @RequestParam Integer timeOfAttempt,
                             HttpServletRequest request){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);

        attemptTestService.saveTimeOfAttempt(attemptId, timeOfAttempt);
        testService.mainCheck(request, attemptId, testId, userId);
        request.setAttribute("time", attemptTestService.getTime(timeOfAttempt));

        return "testResult";
    }

    @RequestMapping(value = "detailResult", method = RequestMethod.GET)
    public String detailResult(HttpServletRequest request, @ModelAttribute String time){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);
        request.getSession().getAttribute("time");
        return "detailResult";
    }

}


