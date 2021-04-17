package ru.laskin.myWebApp.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity(name = "questions")
@Table(name = "questions", schema = "public", catalog = "postgres")
public class Question {
    private int questionId;
    private String questionName;
    private Test test;
    private List<Answer> answers;

    @OneToMany(targetEntity = Answer.class, cascade = CascadeType.ALL, mappedBy = "question")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Test.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id")
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
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
