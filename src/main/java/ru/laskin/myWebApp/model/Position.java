package ru.laskin.myWebApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "positions")
@Table(name = "positions")
public class Position {
    private int idPosition;
    private String position;
    private List<User> userList;
    private String checkDelete;
    private Integer companyId;

    public Position() {
    }

    @Id
    @Column(name = "id_position", nullable = false)
    @GeneratedValue(generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "positions_id_position_seq")
    public int getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(int idPosition) {
        this.idPosition = idPosition;
    }

    @Basic
    @Column(name = "position", nullable = false, length = -1)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    @JsonIgnore
    @OneToMany(
            targetEntity = User.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "pos_id")
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Column(name = "company_id")
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Transient
    public String getCheckDelete() {
        return checkDelete;
    }

    public void setCheckDelete(String checkDelete) {
        this.checkDelete = checkDelete;
    }


}
