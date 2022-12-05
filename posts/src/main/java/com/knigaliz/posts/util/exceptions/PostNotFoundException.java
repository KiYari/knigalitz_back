package com.knigaliz.posts.util.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PostNotFoundException extends RuntimeException{
    private String error;
}
