package com.knigaliz.accounts.controllers;

import com.knigaliz.accounts.entities.UserEntity;
import com.knigaliz.accounts.services.UserService;
import com.knigaliz.accounts.util.exceptions.NoAccessToThisPageException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
        return user.getRoles().toString();
    }
}
