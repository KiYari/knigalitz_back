package com.knigaliz.accounts.services;

import com.knigaliz.accounts.model.User;
import com.knigaliz.accounts.model.UserMainInfo;
import com.knigaliz.accounts.repositories.UserMainInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMainInfoService {
    private final UserMainInfoRepository userMainInfoRepository;

    @Autowired
    public UserMainInfoService(UserMainInfoRepository userMainInfoRepository) {
        this.userMainInfoRepository = userMainInfoRepository;
    }

    public UserMainInfo getInfoByUserId(int id) {
        return userMainInfoRepository.findById(id).orElse(null);
    }

    public UserMainInfo getInfoByUser(User user) {
        return userMainInfoRepository.findById(user.getId()).orElse(null);
    }
}
