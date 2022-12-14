package com.knigaliz.sample_service.repositories;

import com.knigaliz.sample_service.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("select r from Role r inner join r.users u where u.id = :id")
    List<Role> findAllByUserId(@Param("id") int id);
}
