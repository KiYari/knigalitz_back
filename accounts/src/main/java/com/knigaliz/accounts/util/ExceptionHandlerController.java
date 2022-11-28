package com.knigaliz.accounts.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(value = Exception.class)
    public @ResponseBody ErrorInfo handleException(Exception e) {
        return new ErrorInfo(e.getMessage(), System.currentTimeMillis());
    }
}
