package com.b3to.newcash.QueryModels;

import com.b3to.newcash.Models.Account;

import java.io.Serializable;

public class Q_Account extends Account {
    private String type_name;

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
