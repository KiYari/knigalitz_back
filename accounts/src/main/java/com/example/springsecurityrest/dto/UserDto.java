package com.example.springsecurityrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto implements Dto{
    private String login;
    private String password;
    private String email;
}