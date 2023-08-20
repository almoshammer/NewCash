package com.b3to.newcash.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.b3to.newcash.QueryModels.Q_CurrencyOverView;

import java.util.List;

@Dao
public interface Currency {


    @Insert
    void insert(com.b3to.newcash.Models.Currency model);

    @Update
    void update(com.b3to.newcash.Models.Currency model);

    @Delete
    void delete(com.b3to.newcash.Models.Currency model);

    @Query("SELECT * FROM currency")
    LiveData<List<com.b3to.newcash.Models.Currency>> readAll();

    @Query("SELECT c.*,round(NULLIF(credit,0),2) credit,round(NULLIF(debit,0),2) debit FROM currency c LEFT JOIN (SELECT currency_id,SUM(amount) debit FROM `transaction` WHERE type=0) t1 ON c.id = t1.currency_id LEFT JOIN (SELECT currency_id,SUM(amount) credit FROM `transaction` WHERE type=1) t2 ON c.id=t2.currency_id")
    LiveData<List<Q_CurrencyOverView>> readAllOverView();
}
