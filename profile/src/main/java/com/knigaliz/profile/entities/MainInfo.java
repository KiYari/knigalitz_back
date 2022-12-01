package com.knigaliz.profile.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "u_main_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MainInfo {
    @Id
    @Column(name = "user_id")
    private int user_id;

    @Column(name = "description")
    private String description;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Override
    public String toString() {
        return "MainInfo{" +
                "user_id=" + user_id +
                ", description='" + description + '\'' +
                ", birthday=" + birthday +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
