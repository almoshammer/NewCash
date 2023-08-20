package com.b3to.newcash.QueryModels;

import androidx.room.Entity;

import com.b3to.newcash.Models.Currency;

@Entity
public class Q_CurrencyOverView extends Currency {
    private double debit;
    private double credit;

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
}
