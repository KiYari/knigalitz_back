package com.knigaliz.accounts.util.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserNotCreatedException extends RuntimeException{
    private String error;
}
