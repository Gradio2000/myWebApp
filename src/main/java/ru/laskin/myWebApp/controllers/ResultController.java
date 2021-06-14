package ru.laskin.myWebApp.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.stringtemplate.v4.ST;
import ru.laskin.myWebApp.model.GroupTest;
import ru.laskin.myWebApp.model.Statistic;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.AttemptTestService;
import ru.laskin.myWebApp.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Controller
public class ResultController {

    private final TestService testService;
    private final AttemptTestService attemptTestService;

    public ResultController( TestService testService, AttemptTestService attemptTestService) {
        this.testService = testService;
        this.attemptTestService = attemptTestService;
    }

    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    public String testFinish(@RequestParam Integer attemptId,
                             @RequestParam Integer timeOfAttempt,
                             HttpServletRequest request,
                             HttpSession session){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);

        Test test = (Test) session.getAttribute("tests");
        attemptTestService.saveTimeOfAttempt(attemptId, timeOfAttempt);
        Statistic statistic = testService.mainCheck(attemptId, test.getTestId(), timeOfAttempt);
        session.setAttribute("statistic", statistic);
        return "testResult";
    }

    @RequestMapping(value = "detailResult", method = RequestMethod.POST)
    public String detailResult(HttpSession session, HttpServletRequest request, Model model){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);

        Statistic statistic = (Statistic) session.getAttribute("statistic");
        model.addAttribute(statistic);
        return "detailResult";
    }

    @PostMapping("detailResultForAdmin")
    public String detailResultForAdmin(Model model, HttpSession session, HttpServletRequest request){
        List<Statistic> statisticList = (List<Statistic>) session.getAttribute("statisticList");
        User user = (User) session.getAttribute("userForStatistic");
        Map<String, String[]> map = request.getParameterMap();
        String statisticId = map.get("statisticId")[0];
        Statistic statistic = statisticList.get(Integer.parseInt(statisticId));
        model.addAttribute(statistic);
        model.addAttribute(user);
        return "detailResult";
    }
}


