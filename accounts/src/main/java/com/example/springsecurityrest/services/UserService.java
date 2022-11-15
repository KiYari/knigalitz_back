package com.example.springsecurityrest.services;

import com.example.springsecurityrest.entities.UserEntity;
import com.example.springsecurityrest.repositories.UserRepository;
import com.example.springsecurityrest.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        UserEntity user = userRepository.findByLogin(username);
        if(user == null) throw new UsernameNotFoundException(String.format("No '%s' such user", username));
        return new UserDetailsImpl(userRepository.findByLogin(username));
    }

    public UserEntity findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
