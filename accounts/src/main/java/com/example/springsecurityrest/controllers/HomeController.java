package com.example.springsecurityrest.controllers;

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

        return "authorized: " + principal.getName();
    }
}
