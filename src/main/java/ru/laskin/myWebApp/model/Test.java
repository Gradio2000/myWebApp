package ru.laskin.myWebApp.model;

import javax.persistence.*;

@Entity
@Table(name = "tests", schema = "public", catalog = "postgres")
public class Test {
    private int testId;
    private String testName;
    private GroupTest groupTestByGroupId;

    public Test() {
    }

    public Test(int testId, String testName) {
        this.testId = testId;
        this.testName = testName;
    }

    @Id
    @Column(name = "test_id", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (testId != test.testId) return false;
        if (testName != null ? !testName.equals(test.testName) : test.testName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + (testName != null ? testName.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id_groupTest", nullable = false)
    public GroupTest getGroupTestByGroupId() {
        return groupTestByGroupId;
    }

    public void setGroupTestByGroupId(GroupTest groupTestByGroupId) {
        this.groupTestByGroupId = groupTestByGroupId;
    }
}
