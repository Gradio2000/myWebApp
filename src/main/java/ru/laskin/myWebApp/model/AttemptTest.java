package ru.laskin.myWebApp.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "attempttests")
@Table(name = "attempttests")
public class AttemptTest {
    private int attemptId;
    private int testId;
    private int userId;
    private Timestamp dateTime;
    private Integer timeAttempt;

    public AttemptTest(Timestamp dateTime, int testId, int userId, int timeAttempt) {
        this.dateTime = dateTime;
        this.testId = testId;
        this.userId = userId;
        this.timeAttempt = timeAttempt;
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

}
