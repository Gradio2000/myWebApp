package ru.laskin.myWebApp.model;

import javax.persistence.*;

@Entity
@Table(name = "answers", schema = "public", catalog = "postgres")
public class Answer {
    private int answerId;
    private String answerName;
    private boolean isRight;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Id
    @Column(name = "answer_id", nullable = false)
    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    @Basic
    @Column(name = "answer_name", nullable = false, length = -1)
    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    @Basic
    @Column(name = "is_right", nullable = false)
    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (answerId != answer.answerId) return false;
        if (answerName != null ? !answerName.equals(answer.answerName) : answer.answerName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = answerId;
        result = 31 * result + (answerName != null ? answerName.hashCode() : 0);
        return result;
    }

}
