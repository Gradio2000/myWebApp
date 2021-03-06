package ru.laskin.myWebApp.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "tests")
@Table(name = "tests")
public class Test {
    private int testId;
    private String testName;
    private GroupTest groupTest;
    private Double criteria;
    private Double time;
    private List<Question> questions;
    private Integer quesAmount;
    private boolean deleted;
    private boolean quesMix;

    public Test() {
    }

    public Test(String testName, List<Question> questions) {
        this.testName = testName;
        this.questions = questions;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = GroupTest.class)
    @JoinColumn(name = "group_id", referencedColumnName = "grouptest_id")
    public GroupTest getGroupTest() {
        return groupTest;
    }

    public void setGroupTest(GroupTest groupTest) {
        this.groupTest = groupTest;
    }

    @Column(name = "criteria")
    public Double getCriteria() {
        return criteria;
    }

    public void setCriteria(Double way1) {
        this.criteria = way1;
    }

    @Column(name = "time")
    public Double getTime() {
        return time;
    }

    public void setTime(Double way2) {
        this.time = way2;
    }

    @OneToMany(
            targetEntity = Question.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id")
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Id
    @Column(name = "test_id", nullable = false)
    @GeneratedValue(generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "tests_test_id_seq")
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "test_name", nullable = false, length = -1)
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Basic
    @Column(name = "ques_amount")
    public Integer getQuesAmount() {
        return quesAmount;
    }

    public void setQuesAmount(Integer quesAmount) {
        this.quesAmount = quesAmount;
    }


    @Column(name = "deleted")
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Column(name = "ques_mix")
    public boolean isQuesMix() {
        return quesMix;
    }

    public void setQuesMix(boolean quesMix) {
        this.quesMix = quesMix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return deleted == test.deleted && quesMix == test.quesMix && testName.equals(test.testName) && groupTest.equals(test.groupTest) && criteria.equals(test.criteria) && time.equals(test.time) && Objects.equals(questions, test.questions) && quesAmount.equals(test.quesAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testName, groupTest, criteria, time, questions, quesAmount, deleted, quesMix);
    }
}
