package ru.laskin.myWebApp.controllers;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.laskin.myWebApp.model.AttemptTest;
import ru.laskin.myWebApp.service.AttemptTestService;

import java.sql.Timestamp;
import java.util.Date;

@Controller
public class TestController {

    AttemptTestService attemptTestService;

    public TestController(AttemptTestService attemptTestService) {
        this.attemptTestService = attemptTestService;
    }

    @PostMapping("/testResult")
    public String testResult (@RequestParam("testId") int testId,
                              @RequestParam("userId") int userId,
                              Model model){
        Timestamp timestamp = new Timestamp(new Date().getTime());
        AttemptTest attemptTest = new AttemptTest(timestamp, testId, userId);
        attemptTestService.saveAttemptTest(attemptTest);
        return "testPage";
    }
}
