package ru.laskin.myWebApp.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity(name = "companies")
@Table(name = "companies")
public class Company {
    private int idCompany;
    private String companyName;
    private List<GroupTest> groupTestList;
    private List<Position> positionList;

    @Id
    @Column(name = "id_company", nullable = false)
    @GeneratedValue(generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "companies_id_company_seq")
    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }


    @Basic
    @Column(name = "company_name", nullable = false, length = -1)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @OneToMany(targetEntity = GroupTest.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "company_id")
    public List<GroupTest> getGroupTestList() {
        return groupTestList;
    }

    public void setGroupTestList(List<GroupTest> groupTestList) {
        this.groupTestList = groupTestList;
    }

    @OneToMany(targetEntity = Position.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "company_id")
    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (idCompany != company.idCompany) return false;
        if (companyName != null ? !companyName.equals(company.companyName) : company.companyName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCompany;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        return result;
    }
}
