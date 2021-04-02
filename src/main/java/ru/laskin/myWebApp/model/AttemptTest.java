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

    public AttemptTest(Timestamp dateTime, int testId, int userId) {
        this.dateTime = dateTime;
        this.testId = testId;
        this.userId = userId;
    }

    public AttemptTest() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttemptTest that = (AttemptTest) o;

        if (attemptId != that.attemptId) return false;
        if (testId != that.testId) return false;
        if (userId != that.userId) return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attemptId;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + testId;
        result = 31 * result + userId;
        return result;
    }
}
