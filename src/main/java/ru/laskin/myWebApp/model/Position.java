package ru.laskin.myWebApp.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "positions")
@Table(name = "positions")
public class Position {
    private int idPosition;
    private String position;
    private List<User> userList;
    String checkDelete;

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

    @Transient
    public String getCheckDelete() {
        return checkDelete;
    }

    public void setCheckDelete(String checkDelete) {
        this.checkDelete = checkDelete;
    }
}
