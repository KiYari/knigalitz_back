package com.knigaliz.models.idclasses;

import java.io.Serializable;

public class UserIdClass implements Serializable {
    private int id;
    private int user_id;

    public UserIdClass(int id, int user_id) {
        this.id = id;
        this.user_id = user_id;
    }
}
