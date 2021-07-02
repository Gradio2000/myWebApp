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
    @SequenceGenerator(name = "id", sequenceName = "attempttest_attempt_id_seq")
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

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttemptTest that = (AttemptTest) o;

        if (attemptId != that.attemptId) return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;
        if (timeAttempt != null ? !timeAttempt.equals(that.timeAttempt) : that.timeAttempt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attemptId;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (timeAttempt != null ? timeAttempt.hashCode() : 0);
        return result;
    }
}
