package ru.laskin.myWebApp.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tests")
@Table(name = "tests", schema = "public", catalog = "postgres")
public class Test {
    private int testId;
    private String testName;
    private int groupId;
    private List<Question> questions;

    public Test(String testName, int groupId, List<Question> questions) {
        this.testName = testName;
        this.groupId = groupId;
        this.questions = questions;
    }

    public Test() {
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
    @Column(name = "group_id", nullable = false)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (testId != test.testId) return false;
        if (groupId != test.groupId) return false;
        if (testName != null ? !testName.equals(test.testName) : test.testName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + (testName != null ? testName.hashCode() : 0);
        result = 31 * result + groupId;
        return result;
    }
}
