package com.b3to.newcash.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.b3to.newcash.QueryModels.Q_Account;
import com.b3to.newcash.QueryModels.Q_Transaction;

import java.util.List;

@Dao
public interface Account {
    @Insert
    void insert(com.b3to.newcash.Models.Account model);

    @Update
    void update(com.b3to.newcash.Models.Account model);

    @Delete
    void delete(com.b3to.newcash.Models.Account model);

    @Query("SELECT * FROM account")
    LiveData<List<com.b3to.newcash.Models.Account>> readAll();

    @Query("SELECT a.*,t.name type_name FROM account a JOIN accounttype t ON a.type_id = t.id")
    LiveData<List<Q_Account>> readAllQ();

    @Query("SELECT t.*,a.name as account_name,c.name as currency_name,c.symbol as currency_symbol FROM `transaction` t JOIN account a ON t.account_id=a.id JOIN currency c ON t.currency_id=c.id WHERE t.account_id=:account_id ORDER by date DESC")
    LiveData<List<Q_Transaction>> readAllTransactions(long account_id);

}
