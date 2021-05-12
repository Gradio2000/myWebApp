package ru.laskin.myWebApp.service;

import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.QuestionHiberDao;
import ru.laskin.myWebApp.dao.TestHiberDao;
import ru.laskin.myWebApp.model.Answer;
import ru.laskin.myWebApp.model.GroupTest;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.model.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class TestService {

    private final TestHiberDao testHiberDao;
    private final QuestionHiberDao questionHiberDao;


    public TestService(TestHiberDao testHiberDao, QuestionHiberDao questionHiberDao) {
        this.testHiberDao = testHiberDao;
        this.questionHiberDao = questionHiberDao;
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
}
