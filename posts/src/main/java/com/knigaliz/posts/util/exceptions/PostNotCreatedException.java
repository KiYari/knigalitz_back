package com.knigaliz.posts.util.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PostNotCreatedException extends RuntimeException{
    private String error;
}
