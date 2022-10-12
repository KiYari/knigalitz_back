package com.knigaliz.controllers;


import com.knigaliz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<String> home() {
        HttpHeaders headers = new HttpHeaders(){{
            add("Access-Control-Allow_origin", "*");
        }};
        System.out.println(userService.findOne(1));
        return new ResponseEntity<>("{" + userService.findOne(1).toString() + "}", headers, HttpStatus.OK);
    }
}
