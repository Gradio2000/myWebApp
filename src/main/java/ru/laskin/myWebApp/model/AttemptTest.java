package ru.laskin.myWebApp.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "attempttests")
public class AttemptTest {
    private int attemptId;
    private Timestamp dateTime;
    private int testId;
    private int userId;
    private int timeOfAttempt;

    public AttemptTest(int attemptId, Timestamp dateTime, int testId, int userId, int timeOfAttempt) {
        this.attemptId = attemptId;
        this.dateTime = dateTime;
        this.testId = testId;
        this.userId = userId;
        this.timeOfAttempt = timeOfAttempt;
    }

    public AttemptTest() {
    }


    public AttemptTest(Timestamp dateTime, int testId, int userId) {
        this.dateTime = dateTime;
        this.testId = testId;
        this.userId = userId;
    }

    @Id
    @Column(name = "attempt_id", nullable = false)
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
    @Column(name = "test_id", nullable = false)
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "time_attempt")
    public int getTimeOfAttempt() {
        return timeOfAttempt;
    }

    public void setTimeOfAttempt(int timeOfAttempt) {
        this.timeOfAttempt = timeOfAttempt;
    }
}
