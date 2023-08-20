package com.b3to.newcash.Models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class AccountType extends Base {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;

    public AccountType() {
    }
    @Ignore
    public AccountType(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
