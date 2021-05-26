package ru.laskin.myWebApp.model;


import java.util.Set;

public class Statistic {
    String date;
    Test test;
    Set<Integer> falseAnswerSet;
    int trueAnswer;
    String testResult;

    public Statistic(String date, Test test, Set<Integer> falseAnswerSet, int trueAnswer, String testResult) {
        this.date = date;
        this.test = test;
        this.falseAnswerSet = falseAnswerSet;
        this.trueAnswer = trueAnswer;
        this.testResult = testResult;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(int trueAnswer) {
        this.trueAnswer = trueAnswer;
    }
}
