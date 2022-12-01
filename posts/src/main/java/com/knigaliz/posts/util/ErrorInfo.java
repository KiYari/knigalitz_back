package com.knigaliz.posts.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorInfo {
    private String error;
    private long timestamp;

}
