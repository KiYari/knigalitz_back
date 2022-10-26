package com.knigaliz.accounts.controllers;

import com.knigaliz.accounts.dto.UserDTO;
import com.knigaliz.accounts.model.User;
import com.knigaliz.accounts.model.UserMainInfo;
import com.knigaliz.accounts.security.UserDetails;
import com.knigaliz.accounts.services.UserMainInfoService;
import com.knigaliz.accounts.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class UserController {
    private final UserService userService;
    private final UserMainInfoService userMainInfoService;
    private final ModelMapper modelMapper;

    private HttpHeaders headers = new HttpHeaders() {{
        add("Access-Control-Allow-Origin", "*");
    }};

    @Autowired
    public UserController(UserService userService, UserMainInfoService userMainInfoService, ModelMapper modelMapper) {
        this.userService = userService;
        this.userMainInfoService = userMainInfoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/showuserinfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(userDetails.getUser());
        return "check console";
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> all() {
        return new ResponseEntity<>(userService.findAll(), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.getUserById(id), headers, HttpStatus.OK);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<UserMainInfo> infoFindById(@PathVariable("id") int id) {
        return new ResponseEntity<>(userMainInfoService.getInfoByUserId(id), headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createAccount(UserDTO userDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) throwException(bindingResult, new NullPointerException());
        userService.save(toUser(userDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteAccountById(int id, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) throwException(bindingResult, new NullPointerException());
        userService.delete(userService.getUserById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private User toUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private UserDTO toUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private void throwException(BindingResult bindingResult, Exception e){
        StringBuilder errorMessage = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for(FieldError error: errors) {
            errorMessage.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";");
        }
        System.out.println(e);
    }
}
