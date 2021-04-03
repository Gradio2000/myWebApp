package ru.laskin.myWebApp.model;

import javax.persistence.*;

@Entity
@Table(name = "resulttests", schema = "public", catalog = "postgres")
public class ResultTest {
    private int resulttestId;
    private int attemptId;
    private int questionId;
    private int answerId;

    public ResultTest() {
    }

    public ResultTest(int attemptId, int questionId, int answerId) {
        this.attemptId = attemptId;
        this.questionId = questionId;
        this.answerId = answerId;
    }

    public ResultTest(int attemptId, int questionId) {
        this.attemptId = attemptId;
        this.questionId = questionId;
    }

    @Id
    @Column(name = "resulttest_id", nullable = false)
    public int getResulttestId() {
        return resulttestId;
    }

    public void setResulttestId(int resulttestId) {
        this.resulttestId = resulttestId;
    }

    @Basic
    @Column(name = "attempt_id", nullable = false)
    public int getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(int attemptId) {
        this.attemptId = attemptId;
    }

    @Basic
    @Column(name = "question_id", nullable = false)
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "answer_id", nullable = false)
    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultTest that = (ResultTest) o;

        if (resulttestId != that.resulttestId) return false;
        if (attemptId != that.attemptId) return false;
        if (questionId != that.questionId) return false;
        if (answerId != that.answerId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = resulttestId;
        result = 31 * result + attemptId;
        result = 31 * result + questionId;
        result = 31 * result + answerId;
        return result;
    }
}
