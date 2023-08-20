package com.b3to.newcash.Models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Account.class
        , parentColumns = "id", childColumns = "account_id", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Currency.class, parentColumns = "id", childColumns = "currency_id", onDelete = ForeignKey.CASCADE)})
public class Transaction extends Base {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long account_id;
    private double amount;
    private long currency_id;
    private String desc;
    private long date;
    private int type;
    private String info;
    private int resources_count;


    public Transaction(long account_id, double amount, long currency_id, String desc, long date, int type, String info) {
        this.account_id = account_id;
        this.amount = amount;
        this.currency_id = currency_id;
        this.desc = desc;
        this.date = date;
        this.type = type;
        this.info = info;
    }

    public Transaction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(long currency_id) {
        this.currency_id = currency_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getResources_count() {
        return resources_count;
    }

    public void setResources_count(int resources_count) {
        this.resources_count = resources_count;
    }
}
