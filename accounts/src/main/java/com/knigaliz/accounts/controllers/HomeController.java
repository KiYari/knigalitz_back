package com.knigaliz.accounts.controllers;

import com.knigaliz.accounts.model.User;
import com.knigaliz.accounts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {
    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> home() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
}
