package com.knigaliz.posts.services;

import com.knigaliz.posts.entities.Role;
import com.knigaliz.posts.entities.UserEntity;
import com.knigaliz.posts.repositories.RoleRepository;
import com.knigaliz.posts.repositories.UserRepository;
import com.knigaliz.posts.security.UserDetailsImpl;
import com.knigaliz.posts.util.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    public List<Role> findUserRolesById(int id) {
        try{
            System.out.println(roleRepository.findById(1)+" " + roleRepository.findById(2));
            return roleRepository.findAllByUserId(id);
        } catch (Exception e) {
            throw new UserNotFoundException("No Roles found by this User");
        }

    }

    public UserEntity createAndRetriveUser(UserEntity user) {
        user.setLocationReg("Vladivostok");
        user.setTimeReg(System.currentTimeMillis());
        System.out.println(user.getId());
        UserEntity user1 = userRepository.save(user);

        Role role = roleRepository.getById(1);
        user.setRoles(new ArrayList<>(){{
            add(role);
        }});

        return user;
    }
}
