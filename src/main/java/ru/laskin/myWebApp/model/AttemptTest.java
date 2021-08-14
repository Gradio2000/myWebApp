package ru.laskin.myWebApp.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "attempttests")
@Table(name = "attempttests")
public class AttemptTest {
    private int attemptId;
    private int testId;
    private int userId;
    private Timestamp dateTime;
    private Integer timeAttempt;
    private int amountQues;
    private int amountFalseAnswer;
    private int amountTrueAnswer;
    private double result;
    private String testResult;


    public AttemptTest(Timestamp dateTime, int testId, int userId, int timeAttempt, int amountQues, String testResult) {
        this.dateTime = dateTime;
        this.testId = testId;
        this.userId = userId;
        this.timeAttempt = timeAttempt;
        this.amountQues = amountQues;
        this.testResult = testResult;
    }

    public AttemptTest() {
    }


    @Id
    @Column(name = "attempt_id", nullable = false)
    @GeneratedValue(generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "attempttests_attempt_id_seq")
    public int getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(int attemptId) {
        this.attemptId = attemptId;
    }

    @Basic
    @Column(name = "date_time", nullable = false)
    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Basic
    @Column(name = "time_attempt")
    public Integer getTimeAttempt() {
        return timeAttempt;
    }

    public void setTimeAttempt(Integer timeAttempt) {
        this.timeAttempt = timeAttempt;
    }

    @Basic
    @Column(name = "test_id")
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "amount_ques")
    public int getAmountQues() {
        return amountQues;
    }

    public void setAmountQues(int amountQues) {
        this.amountQues = amountQues;
    }

    @Column(name = "amount_false_answers")
    public int getAmountFalseAnswer() {
        return amountFalseAnswer;
    }

    public void setAmountFalseAnswer(int amountFalseAnswer) {
        this.amountFalseAnswer = amountFalseAnswer;
    }
    @Column(name = "amount_true_answers")
    public int getAmountTrueAnswer() {
        return amountTrueAnswer;
    }

    public void setAmountTrueAnswer(int amountTrueAnswer) {
        this.amountTrueAnswer = amountTrueAnswer;
    }

    @Column(name="result")
    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Column(name = "testresult")
    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttemptTest that = (AttemptTest) o;
        return attemptId == that.attemptId && testId == that.testId && userId == that.userId && Objects.equals(dateTime, that.dateTime) && Objects.equals(timeAttempt, that.timeAttempt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attemptId, testId, userId, dateTime, timeAttempt);
    }
}
