package com.rajman.demo.data;

import javax.persistence.*;

@Entity
@Table(name = "user_roles", uniqueConstraints = @UniqueConstraint(columnNames = {"role", "user_id"}))
public class UserRole {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users userR;

    @Enumerated(EnumType.STRING)
    private Roles role;

    public UserRole() {
    }

    public UserRole(Roles role, Users user) {
        this.role = role;
        this.userR = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Users getUser() {
        return userR;
    }

    public void setUser(Users user) {
        this.userR = user;
    }

    public Users getUserR() {
        return userR;
    }

    public void setUserR(Users userR) {
        this.userR = userR;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}

