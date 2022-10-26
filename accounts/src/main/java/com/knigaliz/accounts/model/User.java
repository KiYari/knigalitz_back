package com.knigaliz.accounts.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="login")
    @NotEmpty(message = "Login cannot be empty")
    @Size(min = 2, max = 32, message = "Login should be between 2...32")
    private String login;

    @Column(name="password")
    @NotEmpty(message = "Password cannot be empty")
    @Size(min=2, max=32, message = "Password should be between 2...32")
    private String password;

    @Column(name="email")
    @NotEmpty(message = "Email cannot be empty")
    @Email
    private String email;

    @Column(name="location_reg")
    private String locationReg;

    @Column(name = "time_reg")
    private long timeReg;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", locationReg='" + locationReg + '\'' +
                ", timeReg=" + timeReg +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocationReg() {
        return locationReg;
    }

    public void setLocationReg(String locationReg) {
        this.locationReg = locationReg;
    }

    public long getTimeReg() {
        return timeReg;
    }

    public void setTimeReg(long timeReg) {
        this.timeReg = timeReg;
    }
}
