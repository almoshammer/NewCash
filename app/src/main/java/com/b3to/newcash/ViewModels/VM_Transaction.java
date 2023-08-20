package com.b3to.newcash.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.b3to.newcash.Models.Account;
import com.b3to.newcash.Models.Transaction;
import com.b3to.newcash.QueryModels.Q_Transaction;

import java.util.List;

public class VM_Transaction extends AndroidViewModel {

    private final com.b3to.newcash.Repos.Transaction repo;

    public VM_Transaction(@NonNull Application application) {
        super(application);
        repo = new com.b3to.newcash.Repos.Transaction(application);
    }

    public void insert(Transaction model) {
        repo.insert(model);
    }

    public void update(Transaction model) {
        repo.update(model);
    }

    public void delete(Transaction model) {
        repo.delete(model);
    }

    public LiveData<List<Transaction>> readAll() {
        return repo.readAll();
    }
    public LiveData<List<Q_Transaction>> readAllR() {
        return repo.readAllR();
    }
    public LiveData<List<Q_Transaction>> readRecent(int count){
        return repo.readRecent(count);
    }
}
