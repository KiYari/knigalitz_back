package com.knigaliz.accounts.repositories;

import com.knigaliz.accounts.model.UserMainInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMainInfoRepository extends JpaRepository<UserMainInfo, Integer> {
}
