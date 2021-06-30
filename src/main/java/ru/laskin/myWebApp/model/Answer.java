package ru.laskin.myWebApp.model;

import javax.persistence.*;

@Entity(name = "answers")
@Table(name = "answers")
public class Answer {
    private int answerId;
    private String answerName;
    private boolean isRight;
    private Question question;

    public Answer() {
    }

    public Answer(String answerName, boolean isRight) {
        this.answerName = answerName;
        this.isRight = isRight;
    }

    public Answer(int answerId, String answerName, boolean isRight) {
        this.answerId = answerId;
        this.answerName = answerName;
        this.isRight = isRight;
    }

    @ManyToOne(targetEntity = Question.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Id
    @Column(name = "answer_id", nullable = false)
    @GeneratedValue(generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "answers_answer_id_seq")
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
        if (isRight != answer.isRight) return false;
        if (answerName != null ? !answerName.equals(answer.answerName) : answer.answerName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = answerId;
        result = 31 * result + (answerName != null ? answerName.hashCode() : 0);
        result = 31 * result + (isRight ? 1 : 0);
        return result;
    }
}
