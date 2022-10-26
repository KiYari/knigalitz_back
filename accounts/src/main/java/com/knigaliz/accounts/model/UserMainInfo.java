package com.knigaliz.accounts.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "u_main_info")
public class UserMainInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int uid;

    @Column(name = "description")
    private String description;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;
}
