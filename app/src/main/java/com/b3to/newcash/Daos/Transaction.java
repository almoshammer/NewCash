package com.b3to.newcash.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.b3to.newcash.QueryModels.Q_Transaction;

import java.util.List;

@Dao
public interface Transaction {
    @Insert
    void insert(com.b3to.newcash.Models.Transaction model);

    @Update
    void update(com.b3to.newcash.Models.Transaction model);

    @Delete
    void delete(com.b3to.newcash.Models.Transaction model);

    @Query("SELECT * FROM `transaction` ORDER by date DESC")
    LiveData<List<com.b3to.newcash.Models.Transaction>> readAll();

    @Query("SELECT t.*,a.name as account_name,c.name as currency_name,c.symbol as currency_symbol FROM `transaction` t JOIN account a ON t.account_id=a.id JOIN currency c ON t.currency_id=c.id ORDER by date DESC")
    LiveData<List<Q_Transaction>> readAllR();

    @Query("SELECT t.*,a.name as account_name,c.name as currency_name,c.symbol as currency_symbol FROM `transaction` t JOIN account a ON t.account_id=a.id JOIN currency c ON t.currency_id=c.id ORDER by date DESC limit :count")
    LiveData<List<Q_Transaction>> readRecent(int count);
}
