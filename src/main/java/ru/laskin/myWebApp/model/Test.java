package ru.laskin.myWebApp.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tests")
@Table(name = "tests", schema = "public", catalog = "postgres")
public class Test {
    private int testId;
    private String testName;
    private GroupTest groupTest;
    private Integer way1;
    private Integer way2;
    private List<Question> questions;

    public Test() {
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = GroupTest.class)
    @JoinColumn(name = "group_id", referencedColumnName = "grouptest_id")
    public GroupTest getGroupTest() {
        return groupTest;
    }

    public void setGroupTest(GroupTest groupTest) {
        this.groupTest = groupTest;
    }

    @Column(name = "way1")
    public Integer getWay1() {
        return way1;
    }

    public void setWay1(Integer way1) {
        this.way1 = way1;
    }

    @Column(name = "way2")
    public Integer getWay2() {
        return way2;
    }

    public void setWay2(Integer way2) {
        this.way2 = way2;
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

}
