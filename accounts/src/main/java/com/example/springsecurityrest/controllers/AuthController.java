package com.example.springsecurityrest.controllers;

import com.example.springsecurityrest.dto.AuthDto;
import com.example.springsecurityrest.dto.Dto;
import com.example.springsecurityrest.dto.UserDto;
import com.example.springsecurityrest.entities.UserEntity;
import com.example.springsecurityrest.security.JwtUtil;
import com.example.springsecurityrest.services.UserService;
import com.example.springsecurityrest.util.exceptions.UnexpectedLoginException;
import com.example.springsecurityrest.util.exceptions.UserNotCreatedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationProvider authenticationProvider;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(UserService userService, JwtUtil jwtUtil, AuthenticationProvider authenticationProvider, ModelMapper modelMapper) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationProvider = authenticationProvider;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/auth", consumes = "application/json")
    public @ResponseBody Map<String, String> login(@RequestBody AuthDto authDto) {
        try{
            return Map.of("jwt-token", authenticateAndGetToken(authDto));
        } catch (Exception e) {
            handleException(e);
            throw new UnexpectedLoginException("Unexpected exception occured during login process");
        }

    }

    @PostMapping(value = "/register", consumes = "application/json")
    public @ResponseBody Map<String, String> register(@RequestBody UserDto userDto) {
        try {
            UserEntity user = userService.createAndRetriveUser(toUser(userDto));

            return new HashMap<>(){{
                put("jwt-token", authenticateAndGetToken(userDto));
                put("user-data", user.toString());
            }};
        } catch (Exception e) {
            handleException(e);
            throw new UserNotCreatedException("Something went wrong when creating a user");
        }

    }

    private UserEntity toUser(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    private String authenticateAndGetToken(Dto dto) {
        String username = dto.getLogin();
        authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(username, dto.getPassword(), Collections.emptyList())
        );

        return jwtUtil.generateToken(username);
    }

    private void handleException(Exception e) {
        System.out.println(Arrays.toString(e.getStackTrace()));
    }
}
