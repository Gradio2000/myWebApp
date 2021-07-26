package ru.laskin.myWebApp.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity(name = "group_test")
@Table(name = "group_test")
public class GroupTest {
    private int groupTestId;
    private String name;
    private Company company;
//    private int companyId;
    private List<Test> testList;

    public GroupTest() {
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Company.class)
    @JoinColumn(name = "company_id", referencedColumnName = "id_company")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

//
//    @Column(name = "company_id")
//    public int getCompanyId() {
//        return companyId;
//    }
//
//    public void setCompanyId(int companyId) {
//        this.companyId = companyId;
//    }

    @OneToMany(targetEntity = Test.class,
               cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "group_id")
    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }


    @Id
    @Column(name = "grouptest_id", nullable = false)
    @GeneratedValue(generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "group_test_grouptest_id_seq")
    public int getGroupTestId() {
        return groupTestId;
    }

    public void setGroupTestId(int grouptestId) {
        this.groupTestId = grouptestId;
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

        if (groupTestId != groupTest.groupTestId) return false;
        if (name != null ? !name.equals(groupTest.name) : groupTest.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupTestId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
