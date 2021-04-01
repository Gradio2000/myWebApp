package ru.laskin.myWebApp.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "questions", schema = "public", catalog = "postgres")
public class Question {
    private int questionId;
    private String questionName;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<Answer> answers;

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Id
    @Column(name = "question_id", nullable = false)
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "question_name", nullable = false, length = -1)
    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (questionId != question.questionId) return false;
        return Objects.equals(questionName, question.questionName);
    }

    @Override
    public int hashCode() {
        int result = questionId;
        result = 31 * result + (questionName != null ? questionName.hashCode() : 0);
        return result;
    }
}
