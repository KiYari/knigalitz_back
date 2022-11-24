package com.knigaliz.sample_service.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorInfo {
    private String error;
    private long timestamp;

}
