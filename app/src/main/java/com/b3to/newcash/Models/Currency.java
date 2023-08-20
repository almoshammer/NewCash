package com.b3to.newcash.Models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Currency extends Base {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String symbol;
    public Currency() {

    }
    @Ignore
    public Currency(long id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
