package com.knigaliz.repositories;


import com.knigaliz.models.User;
import com.knigaliz.models.UserMainInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface UserMainInfoRepository extends JpaRepository<UserMainInfo, User> {
}
