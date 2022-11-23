package com.example.springsecurityrest.controllers;

import com.example.springsecurityrest.util.exceptions.NoAccessToThisPageException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @GetMapping("/admin")
    public String adminPanel() {
        try {
            return "admin panel";
        } catch (Exception e) {
            throw new NoAccessToThisPageException("You dont have permissions to enter this page. Required role: ADMIN");
        }

    }
}
