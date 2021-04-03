package ru.laskin.myWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.laskin.myWebApp.model.AttemptTest;
import ru.laskin.myWebApp.model.ResultTest;
import ru.laskin.myWebApp.service.AttemptTestService;
import ru.laskin.myWebApp.service.MainService;
import ru.laskin.myWebApp.service.ResultTestService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class TestController {

    private AttemptTestService attemptTestService;
    private ResultTestService resultTestService;
    private MainService mainService;

    public TestController(AttemptTestService attemptTestService,
                          ResultTestService resultTestService,
                          MainService mainService) {
        this.attemptTestService = attemptTestService;
        this.resultTestService = resultTestService;
        this.mainService = mainService;
    }

    @PostMapping("/testResult")
    public String testResult (@RequestParam("testId") int testId,
                              @RequestParam("userId") int userId,
                              @RequestParam("questionId") List<Integer> questionId,
                              @RequestParam("check") List<Integer> answerId,
                              HttpServletRequest request,
                              Model model){

        Timestamp timestamp = new Timestamp(new Date().getTime());
        AttemptTest attemptTest = new AttemptTest(timestamp, testId, userId);
        int attemptId = attemptTestService.saveAttemptTest(attemptTest);

        for (int i = 0; i < questionId.size(); i++) {
            if (questionId.get(i) == 0) continue;
            if (i <= answerId.size() - 1){
                resultTestService.saveResultTest(new ResultTest(attemptId, questionId.get(i), answerId.get(i)));
            }
            else {
                resultTestService.saveResultTest(new ResultTest(attemptId, questionId.get(i)));
            }
        }

        List<String> result = mainService.getResult(attemptId, timestamp, testId, userId);
        model.addAttribute("result", result);
        return "testResult";
    }
}
