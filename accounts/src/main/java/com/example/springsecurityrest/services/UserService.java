package com.example.springsecurityrest.services;

import com.example.springsecurityrest.entities.UserEntity;
import com.example.springsecurityrest.repositories.UserRepository;
import com.example.springsecurityrest.security.UserDetailsImpl;
import com.example.springsecurityrest.util.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserEntity user = findByLogin(username);
        return new UserDetailsImpl(user);
    }

    public UserEntity findByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(() ->
                new UserNotFoundException(String.format("There is no user %s present in database", login)));
    }

    public UserEntity createAndRetriveUser(UserEntity user) {
        user.setLocationReg("Vladivostok");
        user.setTimeReg(System.currentTimeMillis());
        user.setRoles(Collections.emptyList());
        userRepository.save(user);

        return user;
    }
}
