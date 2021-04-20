package ru.laskin.myWebApp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.laskin.myWebApp.model.*;
import ru.laskin.myWebApp.service.PositionService;
import ru.laskin.myWebApp.service.TestService;
import ru.laskin.myWebApp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private UserService service;
    private PositionService positionService;
    private TestService testService;

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
    public String formUser (@ModelAttribute User user,
                            HttpServletRequest request,
                            Model model) throws SQLException {
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
        return "list_tests";
    }

    @GetMapping("/tests/update")
    public String showEditTestForm(@RequestParam Integer id, Model model){
        model.addAttribute("test", testService.getTestById(id));
        model.addAttribute("questions", testService.getTestById(id).getQuestions());
        return "edittest";
    }

    /*  Этот контроллер собирает ListQuestion и ListAnswers для Test
        и отправляет Test в БД для обновлнея                            */
    @PostMapping("/updateTest")
    public String updateTest(@ModelAttribute Test test, HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] answersName = parameterMap.get("answer");
        String[] answerId = parameterMap.get("answerId");
        String[] isRight = parameterMap.get("isRight");
        String[] questionName = parameterMap.get("question");
        String[] questionId = parameterMap.get("questionId");
        String[] quesAnsId = parameterMap.get("quesAnsId");

        List<Question> questionList = new ArrayList<>();
        for (int i = 0; i < questionId.length; i++) {
            List<Answer> answerList = new ArrayList<>();
            for (int x = 0; x < answersName.length; x++) {
                boolean right = false;
                if (isRight[x].equals("isRight")){
                    right = true;
                }
                if (quesAnsId[x].equals(questionId[i])){
                    Answer answer = new Answer(Integer.parseInt(answerId[x]), answersName[x], right);
                    answerList.add(answer);
                }
            }
            Question question = new Question(Integer.parseInt(questionId[i]), questionName[i], answerList);
            questionList.add(question);
        }

        test.setQuestions(questionList);

        testService.updateTest(test);
        return "redirect:/allTests";
    }

    @GetMapping("/tests/delete")
    public String deleteTest(@RequestParam Integer id){
        testService.deleteTestById(id);
        return "redirect:/allTests";
    }
}
