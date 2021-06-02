package ru.laskin.myWebApp.model;


import java.util.List;
import java.util.Set;

public class Statistic {
    String date;
    Test test;
    Set<Integer> falseAnswerSet;
    int trueAnswer;
    String testResult;
    List<Integer> listOfUserAnswer;
    double result;
    String time;

    public Statistic(String date, Test test, Set<Integer> falseAnswerSet, int trueAnswer,
                     String testResult, List<Integer> listOfUserAnswer, double result, String time) {
        this.date = date;
        this.test = test;
        this.falseAnswerSet = falseAnswerSet;
        this.trueAnswer = trueAnswer;
        this.testResult = testResult;
        this.listOfUserAnswer = listOfUserAnswer;
        this.result = result;
        this.time = time;
    }

    public List<Integer> getListOfUserAnswer() {
        return listOfUserAnswer;
    }

    public void setListOfUserAnswer(List<Integer> listOfUserAnswer) {
        this.listOfUserAnswer = listOfUserAnswer;
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

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
