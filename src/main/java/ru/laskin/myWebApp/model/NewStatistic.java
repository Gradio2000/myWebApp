package ru.laskin.myWebApp.model;


public class NewStatistic {
    private String date;
    private String testName;
    private int amountFalseAnswers;
    private int amountTrueAnswer;
    private String testResult;
    private int time;
    private int amountQues;
    private double result;
    private double criteria;

    public NewStatistic(String date, String testName, int amountFalseAnswers, int amountTrueAnswer, String testResult,
                        int time, int amountQues, double result, double criteria) {
        this.date = date;
        this.testName = testName;
        this.amountFalseAnswers = amountFalseAnswers;
        this.amountTrueAnswer = amountTrueAnswer;
        this.testResult = testResult;
        this.time = time;
        this.amountQues = amountQues;
        this.result = result;
        this.criteria = criteria;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
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
}
