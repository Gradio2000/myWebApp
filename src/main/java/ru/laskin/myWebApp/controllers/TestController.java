package ru.laskin.myWebApp.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.laskin.myWebApp.model.*;
import ru.laskin.myWebApp.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class TestController {

    private final ResultTestService resultTestService;
    private final TestService testService;
    private final UserService userService;
    private final AttemptTestService attemptTestService;

    public TestController(ResultTestService resultTestService, TestService testService,
                          UserService userService, AttemptTestService attemptTestService) {
        this.resultTestService = resultTestService;
        this.testService = testService;
        this.userService = userService;
        this.attemptTestService = attemptTestService;
    }

    @PostMapping("/oper")
    public String saveUserAnswer(HttpServletRequest request,
                                 @RequestParam Integer questionId,
                                 @RequestParam Integer attemptId){
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] checks = parameterMap.get("check");

        //Создаем ResultTestы
        if (checks != null){
            for (int i = 0; i < checks.length; i++) {
                ResultTest resultTest = new ResultTest(attemptId, questionId, Integer.parseInt(checks[i]));
                resultTestService.saveResultTest(resultTest);
            }
        }
        return "testProcessing";
    }

    @GetMapping("/finish")
    public String testFinish(@RequestParam Integer attemptId,
                             @RequestParam Integer testId,
                             @RequestParam Integer userId,
                             HttpServletRequest request){
        User user = userService.getUserById(userId);
        AttemptTest attemptTest = attemptTestService.getAttemptById(attemptId);

        List<ResultTest> resultTestList = resultTestService.getResultTest(attemptId);
        Map<Integer, List<Integer>> mapOfUserAnswers = new HashMap<>();
        for (ResultTest resultTest : resultTestList){
            List<Integer> answersIdList = mapOfUserAnswers.get(resultTest.getQuestionId());
            if (answersIdList == null) answersIdList = new ArrayList<>();
            answersIdList.add(resultTest.getAnswerId());
            mapOfUserAnswers.put(resultTest.getQuestionId(), answersIdList);
        }

        Test test = testService.getTestById(testId);
        List<Question> questionList = test.getQuestions();

        Set<Integer> falseAnswer = new HashSet<>();
        Set<Integer> trueAnswer = new HashSet<>();

        if (mapOfUserAnswers.size() != 0){
            for (Question question : questionList){
                List<Answer> answerList = question.getAnswers();
                for (Answer answer : answerList){
                    List<Integer> questionsIdList = mapOfUserAnswers.get(question.getQuestionId());
                    if (questionsIdList == null){
                        falseAnswer.add(question.getQuestionId());
                    }
                    else {
                        if (answer.isRight()){
                            if (!questionsIdList.contains(answer.getAnswerId())){
                                falseAnswer.add(question.getQuestionId());
                            }
                            else {
                                trueAnswer.add(question.getQuestionId());
                            }
                        }
                        else {
                            if (questionsIdList.contains(answer.getAnswerId())){
                                falseAnswer.add(question.getQuestionId());
                            }
                        }
                    }
                }
            }
        }
        else {
            for (Question question : questionList){
                falseAnswer.add(question.getQuestionId());
            }
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        request.setAttribute("data", attemptTest.getDateTime().toLocalDateTime().format(dateTimeFormatter));
        request.setAttribute("name", user.getName());
        request.setAttribute("position", user.getPosition());
        request.setAttribute("testName", test.getTestName());
        request.setAttribute("quesCount", test.getQuestions().size());
        request.setAttribute("falseAnswer", falseAnswer.size());
        request.setAttribute("trueAnswer", trueAnswer.size());

        return "testResult";
    }
}
