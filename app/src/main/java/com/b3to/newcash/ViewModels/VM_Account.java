package com.b3to.newcash.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.b3to.newcash.Models.Account;
import com.b3to.newcash.QueryModels.Q_Account;
import com.b3to.newcash.QueryModels.Q_Transaction;

import java.util.List;

public class VM_Account extends AndroidViewModel {

    private final com.b3to.newcash.Repos.Account repo;

    public VM_Account(@NonNull Application application) {
        super(application);
        repo = new com.b3to.newcash.Repos.Account(application);
    }

    public void insert(Account model) {
        repo.insert(model);
    }

    public void update(Account model) {
        repo.update(model);
    }

    public void delete(Account model) {
        repo.delete(model);
    }

    public LiveData<List<Account>> readAll() {
        return repo.readAll();
    }
    public LiveData<List<Q_Account>> readAllQ() {
        return repo.readAllQ();
    }

    public LiveData<List<Q_Transaction>> readTransactions(long account_id) {
        return repo.readTransactions(account_id);
    }
}
