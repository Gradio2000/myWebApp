package ru.laskin.myWebApp.model;


import java.util.List;
import java.util.Set;

public class NewStatistic {
    private String date;
    private String testName;
    private int amountFalseAnswers;
    private int amountTrueAnswer;
    private String testResult;
    private String time;
    private int amountQues;
    private double result;
    private double criteria;
    private int attemptId;
    private Test test;
    private Set<Integer> falseAnswerSet;
    private List<Integer> listOfUsersAnswers;
    private List<Question> quesList;


    public NewStatistic(String date, String testName, int amountFalseAnswers,
                        int amountTrueAnswer, String testResult, String time, int amountQues,
                        double result, double criteria, int attemptId) {
        this.date = date;
        this.testName = testName;
        this.amountFalseAnswers = amountFalseAnswers;
        this.amountTrueAnswer = amountTrueAnswer;
        this.testResult = testResult;
        this.time = time;
        this.amountQues = amountQues;
        this.result = result;
        this.criteria = criteria;
        this.attemptId = attemptId;
    }

    public NewStatistic(String date, String testName, int amountFalseAnswers,
                        int amountTrueAnswer, String testResult, String time, int amountQues,
                        double result, double criteria, int attemptId, Test test) {
        this.date = date;
        this.testName = testName;
        this.amountFalseAnswers = amountFalseAnswers;
        this.amountTrueAnswer = amountTrueAnswer;
        this.testResult = testResult;
        this.time = time;
        this.amountQues = amountQues;
        this.result = result;
        this.criteria = criteria;
        this.attemptId = attemptId;
        this.test = test;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getAmountFalseAnswers() {
        return amountFalseAnswers;
    }

    public void setAmountFalseAnswers(int amountFalseAnswers) {
        this.amountFalseAnswers = amountFalseAnswers;
    }

    public int getAmountTrueAnswer() {
        return amountTrueAnswer;
    }

    public void setAmountTrueAnswer(int amountTrueAnswer) {
        this.amountTrueAnswer = amountTrueAnswer;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAmountQues() {
        return amountQues;
    }

    public void setAmountQues(int amountQues) {
        this.amountQues = amountQues;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getCriteria() {
        return criteria;
    }

    public void setCriteria(double criteria) {
        this.criteria = criteria;
    }

    public int getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(int attemptId) {
        this.attemptId = attemptId;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Set<Integer> getFalseAnswerSet() {
        return falseAnswerSet;
    }

    public void setFalseAnswerSet(Set<Integer> falseAnswerSet) {
        this.falseAnswerSet = falseAnswerSet;
    }

    public List<Integer> getListOfUsersAnswers() {
        return listOfUsersAnswers;
    }

    public void setListOfUsersAnswers(List<Integer> listOfUsersAnswers) {
        this.listOfUsersAnswers = listOfUsersAnswers;
    }

    public List<Question> getQuesList() {
        return quesList;
    }

    public void setQuesList(List<Question> quesList) {
        this.quesList = quesList;
    }
}
