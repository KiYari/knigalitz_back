package com.knigaliz.posts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthDto implements Dto{
    private String login;
    private String password;
}
