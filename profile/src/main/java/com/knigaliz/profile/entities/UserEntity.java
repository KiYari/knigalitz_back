package com.knigaliz.profile.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "location_reg")
    private String locationReg;

    @Column(name = "time_reg")
    private long timeReg;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private MainInfo info;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"  ),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    List<Role> roles;
}
