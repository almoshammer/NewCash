package com.b3to.newcash.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.b3to.newcash.QueryModels.Q_AccountType;

import java.util.List;

@Dao
public interface AccountType {
    @Insert
    void insert(com.b3to.newcash.Models.AccountType model);

    @Update
    void update(com.b3to.newcash.Models.AccountType model);

    @Delete
    void delete(com.b3to.newcash.Models.AccountType model);

    @Query("SELECT * FROM accounttype")
    LiveData<List<com.b3to.newcash.Models.AccountType>> readAll();

    @Query("select tp.*,c.id currency_id,c.name currency_name,c.symbol currency_symbol,round(debit,2) debit,round(credit,2) credit from accounttype tp join " +
            "(select type_id,currency_id,sum(debit) debit,sum(credit) credit from account a join " +
            "(SELECT t.type,t.currency_id,t.account_id, " +
            "case when type = 0 then SUM(amount) else 0 end debit, " +
            "case when type = 1 then sum(amount) else 0 end credit FROM `TRANSACTION` t group by t.type,currency_id,t.account_id) tg " +
            "ON a.id = tg.account_id group by type_id,currency_id) tt on tt.type_id=tp.id " +
            "join currency c on tt.currency_id = c.id order by tp.id")
    LiveData<List<Q_AccountType>> readAllQ();

    @Query("select tp.*,c.id currency_id,c.name currency_name,c.symbol currency_symbol,debit,credit from accounttype tp join " +
            "(select type_id,currency_id,sum(debit) debit,sum(credit) credit from account a join " +
            "(SELECT t.type,t.currency_id,t.account_id, " +
            "case when type = 0 then SUM(amount) else 0 end debit, " +
            "case when type = 1 then sum(amount) else 0 end credit FROM `TRANSACTION` t WHERE t.account_id=:account_id group by t.type,currency_id,t.account_id) tg " +
            "ON a.id = tg.account_id group by type_id,currency_id) tt on tt.type_id=tp.id " +
            "join currency c on tt.currency_id = c.id order by tp.id")
    LiveData<List<Q_AccountType>> readAllQ(long account_id);
}
