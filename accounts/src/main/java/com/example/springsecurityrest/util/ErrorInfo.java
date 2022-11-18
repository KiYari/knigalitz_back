package com.example.springsecurityrest.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorInfo {
    private String error;
    private long timestamp;

}
