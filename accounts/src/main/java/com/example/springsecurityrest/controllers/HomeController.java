package com.example.springsecurityrest.controllers;

import com.example.springsecurityrest.util.exceptions.NoAccessToThisPageException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {
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
}
