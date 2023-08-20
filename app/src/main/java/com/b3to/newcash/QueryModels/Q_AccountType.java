package com.b3to.newcash.QueryModels;

import com.b3to.newcash.Models.AccountType;

public class Q_AccountType extends AccountType {
    private double debit;
    private double credit;
    private long currency_id;
    private String currency_symbol;
    private String currency_name;

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public long getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(long currency_id) {
        this.currency_id = currency_id;
    }

    public String getCurrency_symbol() {
        return currency_symbol;
    }

    public void setCurrency_symbol(String currency_symbol) {
        this.currency_symbol = currency_symbol;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }
}
