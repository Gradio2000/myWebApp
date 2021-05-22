package ru.laskin.myWebApp.service;

import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.QuestionHiberDao;
import ru.laskin.myWebApp.dao.TestHiberDao;
import ru.laskin.myWebApp.model.*;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TestService {

    private final TestHiberDao testHiberDao;
    private final QuestionHiberDao questionHiberDao;
    private final UserService userService;
    private final AttemptTestService attemptTestService;
    private final ResultTestService resultTestService;


    public TestService(TestHiberDao testHiberDao, QuestionHiberDao questionHiberDao,
                       UserService userService, AttemptTestService attemptTestService,
                       ResultTestService resultTestService) {
        this.testHiberDao = testHiberDao;
        this.questionHiberDao = questionHiberDao;
        this.userService = userService;
        this.attemptTestService = attemptTestService;
        this.resultTestService = resultTestService;
    }

    public List<Test> getAllTests(){
        return testHiberDao.getAllTests();
    }

    public Test getTestById(int testId){
        return testHiberDao.getTestById(testId);
    }

    public void updateTest(Test test, Map <String, String[]> parameterMap){
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

        test.setGroupTest(getGroupTestById(Integer.parseInt(groupId[0])));
        test.setQuestions(questionList);
        testHiberDao.updateTest(test);
    }

    public void saveTest(Test test){
        testHiberDao.saveTest(test);
    }

    public void deleteTestById(int id){
        testHiberDao.deleteTestById(id);
    }

    public List<GroupTest> getAllGroupTest(){
        return testHiberDao.getAllGroup();
    }

    public void deleteGroupTest(Integer id) {
        testHiberDao.deleteGroupTest(id);
    }

    public void addGroup(GroupTest groupTest) {
        testHiberDao.addGroup(groupTest);
    }

    public void updateAllGroup(List<GroupTest> groupTests) {
        testHiberDao.updateAllGroup(groupTests);
    }

    public GroupTest getGroupTestById(int groupId) {
        return testHiberDao.getGroupById(groupId);
    }

    public List<Test> getTestsByGroupId(int groupId) {
        return testHiberDao.getTestsByGroupId(groupId);
    }

    public void saveQuestion(Question question, String[] answersName, String[] rightAnswer) {
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
        questionHiberDao.saveQuestion(question);
    }

    public void mainCheck(HttpServletRequest request, Integer attemptId, Integer testId, Integer userId) {
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

        Test test = getTestById(testId);
        List<Question> questionList = test.getQuestions();

        Set<Integer> falseAnswerSet = new HashSet<>();

        if (mapOfUserAnswers.size() != 0){
            for (Question question : questionList){
                List<Answer> answerList = question.getAnswers();
                for (Answer answer : answerList){
                    List<Integer> questionsIdList = mapOfUserAnswers.get(question.getQuestionId());
                    if (questionsIdList == null){
                        falseAnswerSet.add(question.getQuestionId());
                    }
                    else {
                        if (answer.isRight()){
                            if (!questionsIdList.contains(answer.getAnswerId())){
                                falseAnswerSet.add(question.getQuestionId());
                                break;
                            }
                        }
                        else {
                            if (questionsIdList.contains(answer.getAnswerId())){
                                falseAnswerSet.add(question.getQuestionId());
                            }
                        }
                    }
                }
            }
        }
        else {
            for (Question question : questionList){
                falseAnswerSet.add(question.getQuestionId());
            }
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        request.setAttribute("data", attemptTest.getDateTime().toLocalDateTime().format(dateTimeFormatter));
        request.setAttribute("users", user);
        request.setAttribute("tests", test);
        request.setAttribute("questionList", questionList);
        request.setAttribute("falseAnswerSet", falseAnswerSet);
        request.setAttribute("trueAnswer", questionList.size() - falseAnswerSet.size());

        List<Integer> listOfUsersAnswers = new ArrayList<>();
        for (Integer key : mapOfUserAnswers.keySet()){
            listOfUsersAnswers.addAll(mapOfUserAnswers.get(key));
        }

        request.setAttribute("listOfUsersAnswers", listOfUsersAnswers);
    }
}
