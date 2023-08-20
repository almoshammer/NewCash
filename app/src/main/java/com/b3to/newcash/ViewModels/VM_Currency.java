package com.b3to.newcash.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.b3to.newcash.QueryModels.Q_CurrencyOverView;
import com.b3to.newcash.Repos.Currency;

import java.util.List;

public class VM_Currency extends AndroidViewModel {
    Currency repo;

    public VM_Currency(@NonNull Application application) {
        super(application);
        this.repo = new Currency(application);
    }

    public void insert(com.b3to.newcash.Models.Currency model) {
        repo.insert(model);
    }

    public void update(com.b3to.newcash.Models.Currency model) {
        repo.update(model);
    }

    public void delete(com.b3to.newcash.Models.Currency model) {
        repo.delete(model);
    }

    public LiveData<List<com.b3to.newcash.Models.Currency>> readAll() {
        return repo.readAll();
    }

    public LiveData<List<Q_CurrencyOverView>> readAllOverView() {
        return repo.readAllOverView();
    }
}
