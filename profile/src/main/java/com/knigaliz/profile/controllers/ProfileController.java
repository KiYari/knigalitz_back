package com.knigaliz.profile.controllers;

import com.knigaliz.profile.entities.UserEntity;
import com.knigaliz.profile.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ProfileController {
    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profileShow(Principal principal) {
        UserEntity user = userService.findByLogin(principal.getName());

        return user.getInfo().toString();
    }
}
