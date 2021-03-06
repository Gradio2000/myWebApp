package ru.laskin.myWebApp.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "questions")
@Table(name = "questions")
public class Question {
    private int questionId;
    private String questionName;
    private Test test;
    private List<Answer> answers;

    public Question() {
    }

    public Question(int questionId, String questionName, List<Answer> answers) {
        this.questionId = questionId;
        this.questionName = questionName;
        this.answers = answers;
    }


    @OneToMany(
            targetEntity = Answer.class,
            cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "question_id")
    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Test.class)
    @JoinColumn(name = "test_id", referencedColumnName = "test_id")
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Id
    @Column(name = "question_id", nullable = false)
    @GeneratedValue(generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "questions_question_id_seq")
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
        return questionId == question.questionId && Objects.equals(questionName, question.questionName) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        int result = questionId;
        result = 31 * result + (questionName != null ? questionName.hashCode() : 0);
        return result;
    }
}
