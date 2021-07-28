package ru.laskin.myWebApp.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;


@Entity(name = "users")
@Table(name = "users")
public class User {
    private int userId;
    private String name;
    private String login;
    private String password;
    private String email;
    private String adminRole;
    private Position position;
    private String confirmPassword;
    private Boolean registered;
    private UUID key;
    private Company company;

    public User() {
    }

    public User(int userId, String name, String login,
                String password, String email, String adminRole,
                Position position, String confirmPassword, Boolean registered,
                UUID key, Company company) {
        this.userId = userId;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.adminRole = adminRole;
        this.position = position;
        this.confirmPassword = confirmPassword;
        this.registered = registered;
        this.key = key;
        this.company = company;
    }

    @Transient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Column
    public Boolean isRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    @Column
    @Type(type = "pg-uuid")
    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
    }

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "users_user_id_seq")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "name", length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "login", nullable = false, length = -1)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password", nullable = false, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", length = -1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "admin_role", length = -1)
    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

//    @JsonBackReference
//    @JsonIgnore
    @ManyToOne(targetEntity = Position.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "pos_id", referencedColumnName = "id_position")
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    @Basic
    @Column(name = "registered")
    public Boolean getRegistered() {
        return registered;
    }

    @OneToOne(targetEntity = Company.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id_company")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (!Objects.equals(name, user.name)) return false;
        if (!Objects.equals(login, user.login)) return false;
        if (!Objects.equals(password, user.password)) return false;
        if (!Objects.equals(email, user.email)) return false;
        return Objects.equals(adminRole, user.adminRole);
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (adminRole != null ? adminRole.hashCode() : 0);
        return result;
    }
}
