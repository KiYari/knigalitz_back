package com.knigaliz.accounts.util.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnexpectedLoginException extends RuntimeException{
    public String message;
}
