package com.knigaliz.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="login")
    private String login;

    @Column(name="email")
    private String email;

    @Column(name="location_reg")
    private String locationReg;

    @Column(name="time_reg")
    private Date timeReg;

    @OneToOne(mappedBy = "user")
    @Cascade({org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.PERSIST})
    private UserMainInfo userMainInfo;

    @OneToMany(mappedBy = "user")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.REMOVE,
            org.hibernate.annotations.CascadeType.PERSIST})
    private List<Post> posts;

    public UserMainInfo getUserMainInfo() {
        return userMainInfo;
    }

    public void setUserMainInfo(UserMainInfo userMainInfo) {
        this.userMainInfo = userMainInfo;
    }

    public int getId() {
        return id;
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

    public Date getTimeReg() {
        return timeReg;
    }

    public void setTimeReg(Date timeReg) {
        this.timeReg = timeReg;
    }

    @Override
    public String toString() {
        return  "\"id\": \"" + id +
                "\", \"login\": \"" + login +
                "\", \"email\": \"" + email +
                "\", \"locationReg\": \"" + locationReg +
                "\", \"timeReg\": \"" + timeReg +
                '\"';
    }
}
