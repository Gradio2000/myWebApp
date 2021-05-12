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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService service;
    private final PositionService positionService;
    private final TestService testService;

    public AdminController(UserService service, PositionService positionService, TestService testService) {
        this.service = service;
        this.positionService = positionService;
        this.testService = testService;
    }

    @GetMapping("/allUsers")
    public String getStart(Model model) {
        model.addAttribute("users", service.getAllUsers());
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("authUser", authUser.getName());
        return "list_users";
    }

    @GetMapping("/users/update")
    public String updateUser(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        User user = service.getUserById(id);
        model.addAttribute("user", user);
        List<Position> positionSet = positionService.getAllPosition();
        model.addAttribute("posSet", positionSet);
        return"updateForm";
    }

    @GetMapping("/users/delete")
    public String deleteUser(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        service.deleteUser(id);
        return "redirect:/allUsers";
    }

    @PostMapping("/updateUser")
    public String formUser (@ModelAttribute User user){
            service.updateUser(user);
            return "redirect:/allUsers";
    }

    @GetMapping("/adminModule")
    public String openAdminModule(){
        return "adminModule";
    }

    @GetMapping("/allTests")
    public String showAllTests(Model model){
        model.addAttribute("alltests", testService.getAllTests());
        model.addAttribute("allgrouptest", testService.getAllGroupTest());
        model.addAttribute("test", new Test());
        return "list_tests";
    }

    @GetMapping("/tests/update")
    public String showEditTestForm(@RequestParam Integer id, Model model){
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
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] answersName = parameterMap.get("answer");
        String[] answerId = parameterMap.get("answerId");
        String[] isRight = parameterMap.get("isRight");
        String[] questionName = parameterMap.get("question");
        String[] questionId = parameterMap.get("questionId");
        String[] quesAnsId = parameterMap.get("quesAnsId");
        String[] newAnswer = parameterMap.get("newAnswer");
        String[] quesIdForNewAnswer = parameterMap.get("quesIdForNewAnswer");
        String[] groupId = parameterMap.get("groupId");
        String[] isRightForNewAnswer = parameterMap.get("isRightForNewAnswer");


        List<Question> questionList = new ArrayList<>();
        for (int i = 0; i < questionName.length; i++) {
            Question question = new Question();
            question.setQuestionId(Integer.parseInt(questionId[i]));
            question.setQuestionName(questionName[i]);

            List<Answer> answerList = new ArrayList<>();
            if (answersName != null){
                for (int j = 0; j < answersName.length; j++) {
                    Answer answer = new Answer();
                    if (j < answerId.length) {
                        answer.setAnswerId(Integer.parseInt(answerId[j]));
                    }
                    answer.setAnswerName(answersName[j]);
                    if (isRight != null && Arrays.asList(isRight).contains(String.valueOf(j))){
                        answer.setRight(true);
                    }
                    if (j < quesAnsId.length && quesAnsId[j].equals(questionId[i])){
                        answerList.add(answer);
                    }
                }
            }


            if (newAnswer != null){
                for (int j = 0; j < newAnswer.length; j++) {
                    Answer answer = new Answer();
                    answer.setAnswerName(newAnswer[j]);
                    if (isRightForNewAnswer != null && Arrays.asList(isRightForNewAnswer).contains(String.valueOf(j))){
                        answer.setRight(true);
                    }
                    if (questionId[i].equals(quesIdForNewAnswer[j])){
                        answerList.add(answer);
                    }
                }
            }

            question.setAnswers(answerList);
            questionList.add(question);
        }

//        test.setGroupId(Integer.parseInt(groupId[0]));
        test.setGroupTest(testService.getGroupTestById(Integer.parseInt(groupId[0])));
        test.setQuestions(questionList);

        testService.updateTest(test);
        return "redirect:/allTests";
    }

    @GetMapping("/tests/delete")
    public String deleteTest(@RequestParam Integer id){
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

        //собираем list answers
        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < answersName.length; i++) {
            Answer answer = new Answer();
            answer.setAnswerName(answersName[i]);

                //в цикле в answer добавляется отметка isRight
            for (String s : rightAnswer) {
                int count = Integer.parseInt(s);
                if (i == count) {
                    answer.setRight(true);
                }
            }

            answers.add(answer);
        }

        question.setAnswers(answers);
        testService.saveQuestion(question);

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
    public String groupTests(Model model){
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
            groupTest.setTestList(testService.getTestsByGropId(Integer.parseInt(id[i])));


            groupTests.add(groupTest);
        }

        testService.updateAllGroup(groupTests);
        return "redirect:/greeting";
    }
}
