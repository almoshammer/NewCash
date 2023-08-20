package com.b3to.newcash.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.b3to.newcash.Models.AccountType;
import com.b3to.newcash.QueryModels.Q_AccountType;

import java.util.List;

public class VM_AccountType extends AndroidViewModel {

    final com.b3to.newcash.Repos.AccountType repo;

    public VM_AccountType(@NonNull Application application) {
        super(application);
        repo = new com.b3to.newcash.Repos.AccountType(application);
    }

    public void insert(AccountType model) {
        repo.insert(model);
    }

    public void update(AccountType model) {
        repo.update(model);
    }

    public void delete(AccountType model) {
        repo.delete(model);
    }

    public LiveData<List<AccountType>> readAll() {
        return repo.readAll();
    }

    public LiveData<List<Q_AccountType>> readAllQ() {
        return repo.readAllQ();
    }
    public LiveData<List<Q_AccountType>> readAllQ(long account_id) {
        return repo.readAllQ(account_id);
    }
}
