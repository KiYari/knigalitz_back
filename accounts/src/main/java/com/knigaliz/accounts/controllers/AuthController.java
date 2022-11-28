package com.knigaliz.accounts.controllers;

import com.knigaliz.accounts.dto.AuthDto;
import com.knigaliz.accounts.dto.UserDto;
import com.knigaliz.accounts.entities.UserEntity;
import com.knigaliz.accounts.security.JwtUtil;
import com.knigaliz.accounts.services.UserService;
import com.knigaliz.accounts.util.exceptions.UnexpectedLoginException;
import com.knigaliz.accounts.util.exceptions.UserNotCreatedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
            return Map.of("jwt-token", JwtUtil.authenticateAndGetToken(authDto));
        } catch (Exception e) {
            handleException(e);
            System.out.println(e);
            throw new UnexpectedLoginException("Unexpected exception occured during login process");
        }

    }

    @PostMapping(value = "/register", consumes = "application/json")
    public @ResponseBody Map<String, String> register(@RequestBody UserDto userDto) {
        try {
            UserEntity user = userService.createAndRetriveUser(toUser(userDto));

            return new HashMap<>(){{
                put("jwt-token", JwtUtil.authenticateAndGetToken(userDto));
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

    private void handleException(Exception e) {
        System.out.println(Arrays.toString(e.getStackTrace()));
    }
}
