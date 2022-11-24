package com.knigaliz.sample_service.dto;

import lombok.Data;
import lombok.Getter;

public interface Dto {
    public String getLogin();
    public String getPassword();
    void setLogin(String login);
    void setPassword(String password);
}
