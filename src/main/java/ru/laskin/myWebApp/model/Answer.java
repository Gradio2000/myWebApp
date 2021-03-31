package ru.laskin.myWebApp.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "answers", schema = "public", catalog = "postgres")
public class Answer {
    private int answerId;
    private String answerName;
    private Question question;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quest_id", referencedColumnName = "question_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
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
