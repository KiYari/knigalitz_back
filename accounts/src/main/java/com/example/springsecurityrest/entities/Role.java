package com.example.springsecurityrest.entities;

import lombok.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Entity
@Data
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @Column(name = "id")
    int id;

    @Column(name = "role")
    String role;

    @ManyToMany(mappedBy = "roles")
    List<UserEntity> users;

}
