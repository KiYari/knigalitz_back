package com.knigaliz.services;


import com.knigaliz.models.User;
import com.knigaliz.models.UserMainInfo;
import com.knigaliz.repositories.UserMainInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserMainInfoService {
    private final UserMainInfoRepository userMainInfoRepository;

    @Autowired
    public UserMainInfoService(UserMainInfoRepository userMainInfoRepository) {
        this.userMainInfoRepository = userMainInfoRepository;
    }

    public UserMainInfo findById(User user) {
        Optional<UserMainInfo> userMainInfo = userMainInfoRepository.findById(user);
        return userMainInfo.orElse(null);
    }

    @Transactional
    public void save(UserMainInfo userMainInfo) {
        userMainInfoRepository.save(userMainInfo);
    }

    @Transactional
    public void update(User user, UserMainInfo userMainInfo) {
        userMainInfo.setUser(user);
        userMainInfoRepository.save(userMainInfo);
    }

    @Transactional
    public void delete(User user) {
        userMainInfoRepository.deleteById(user);
    }

    @Transactional
    public void delete(UserMainInfo userMainInfo) {
        userMainInfoRepository.delete(userMainInfo);
    }
}
