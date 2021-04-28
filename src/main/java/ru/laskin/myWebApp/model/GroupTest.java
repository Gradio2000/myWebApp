package ru.laskin.myWebApp.model;

import javax.persistence.*;

@Entity(name = "group_test")
@Table(name = "group_test", schema = "public", catalog = "postgres")
public class GroupTest {
    private int grouptestId;
    private String name;

    public GroupTest() {
    }

    @Id
    @Column(name = "grouptest_id", nullable = false)
    @GeneratedValue(generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "group_test_grouptest_id_seq")
    public int getGrouptestId() {
        return grouptestId;
    }

    public void setGrouptestId(int grouptestId) {
        this.grouptestId = grouptestId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupTest groupTest = (GroupTest) o;

        if (grouptestId != groupTest.grouptestId) return false;
        if (name != null ? !name.equals(groupTest.name) : groupTest.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = grouptestId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
