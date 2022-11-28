package com.knigaliz.accounts.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @Column(name = "id")
    int id;

    @Column(name = "role")
    String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    List<UserEntity> users;

    @Override
    public String toString() {
        return role;
    }
}
