package ru.laskin.myWebApp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "questions", schema = "public", catalog = "postgres")
public class Question {
    private int questionId;
    private String questionName;
    private Set<Answer> answers;

    public Question() {
    }

    public Question(int questionId, String questionName) {
        this.questionId = questionId;
        this.questionName = questionName;
    }

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
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
        if (questionName != null ? !questionName.equals(question.questionName) : question.questionName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = questionId;
        result = 31 * result + (questionName != null ? questionName.hashCode() : 0);
        return result;
    }
}
