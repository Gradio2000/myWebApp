package ru.laskin.myWebApp.model;

import javax.persistence.*;

@Entity
@Table(name = "positions", schema = "public", catalog = "postgres")
public class Position {
    private int idPosition;
    private String position;

    public Position() {
    }

    @Id
    @Column(name = "id_position", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position1 = (Position) o;

        if (idPosition != position1.idPosition) return false;
        if (position != null ? !position.equals(position1.position) : position1.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPosition;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
