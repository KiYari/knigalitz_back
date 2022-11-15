package com.example.springsecurityrest.controllers;

import com.example.springsecurityrest.dto.UserDto;
import com.example.springsecurityrest.entities.UserEntity;
import com.example.springsecurityrest.security.JwtUtil;
import com.example.springsecurityrest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public AuthController(UserService userService, JwtUtil jwtUtil, AuthenticationProvider authenticationProvider) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationProvider = authenticationProvider;
    }

    @PostMapping(value = "/auth", consumes = "application/json")
    public @ResponseBody Map<String, String> login(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        try {
            String username = userDto.getLogin();
            UserDetails userDetails = userService.loadUserByUsername(username);

            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userDetails, userDto.getPassword(), Collections.emptyList()));
            UserEntity user = userService.findByLogin(username);

            if(user == null) {
                throw new UsernameNotFoundException("User with username: " + username + "not found");
            }

            String token = jwtUtil.generateToken(userDto.getLogin());
            return Map.of("jwt-token", token);
        } catch (AuthenticationException e) {
            String error = "Invalid username or password";
            return Map.of("error", error);
        }

    }
}
