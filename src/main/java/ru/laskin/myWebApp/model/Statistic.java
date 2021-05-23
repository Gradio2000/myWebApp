package ru.laskin.myWebApp.model;


public class Statistic {
    String date;
    Test test;

    public Statistic(String date, Test test) {
        this.date = date;
        this.test = test;
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
}
