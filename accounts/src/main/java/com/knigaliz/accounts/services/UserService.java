package com.knigaliz.accounts.services;

import com.knigaliz.accounts.model.User;
import com.knigaliz.accounts.model.UserMainInfo;
import com.knigaliz.accounts.repositories.UserMainInfoRepository;
import com.knigaliz.accounts.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMainInfoRepository userMainInfoRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserMainInfoRepository userMainInfoRepository) {
        this.userRepository = userRepository;
        this.userMainInfoRepository = userMainInfoRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserMainInfo getUserInfo(int id) {
        return userMainInfoRepository.findById(id).orElse(null);
    }

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
