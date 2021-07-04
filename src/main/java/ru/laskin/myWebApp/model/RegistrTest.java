package ru.laskin.myWebApp.model;

import javax.persistence.*;

@Entity(name = "registrTest")
@Table(name = "registr_test")
public class RegistrTest {
    private int registrId;
    private Integer attemptId;
    private Integer quesId;

    @Id
    @Column(name = "registr_id", nullable = false)
    public int getRegistrId() {
        return registrId;
    }

    public void setRegistrId(int registrId) {
        this.registrId = registrId;
    }

    @Basic
    @Column(name = "attempt_id", nullable = true)
    public Integer getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(Integer attemptId) {
        this.attemptId = attemptId;
    }

    @Basic
    @Column(name = "ques_id", nullable = true)
    public Integer getQuesId() {
        return quesId;
    }

    public void setQuesId(Integer quesId) {
        this.quesId = quesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistrTest that = (RegistrTest) o;

        if (registrId != that.registrId) return false;
        if (attemptId != null ? !attemptId.equals(that.attemptId) : that.attemptId != null) return false;
        if (quesId != null ? !quesId.equals(that.quesId) : that.quesId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = registrId;
        result = 31 * result + (attemptId != null ? attemptId.hashCode() : 0);
        result = 31 * result + (quesId != null ? quesId.hashCode() : 0);
        return result;
    }
}
