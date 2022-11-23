package com.example.springsecurityrest.controllers;

import com.example.springsecurityrest.entities.Role;
import com.example.springsecurityrest.entities.UserEntity;
import com.example.springsecurityrest.services.UserService;
import com.example.springsecurityrest.util.exceptions.NoAccessToThisPageException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class HomeController {
    @Autowired
    private final UserService userService;
    @GetMapping("/")
    public String home() {
        return "home for all";
    }

    @GetMapping("/authorized")
    public String auth(Principal principal) {
        try {
            return "authorized: " + principal.getName();
        } catch (AccessDeniedException e) {
            throw new NoAccessToThisPageException("You dont have access to this page");
        }

    }

    @GetMapping("/roles")
    public String roles(Principal principal) {
        UserEntity user = userService.findByLogin(principal.getName());
        List<Role> roles = userService.findUserRolesById(user.getId());
        return user.toString();
    }
}
