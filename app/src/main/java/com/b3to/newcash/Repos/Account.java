package com.b3to.newcash.Repos;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.b3to.newcash.Db.RoomDb;
import com.b3to.newcash.QueryModels.Q_Account;
import com.b3to.newcash.QueryModels.Q_Transaction;

import java.util.List;

public class Account {
    com.b3to.newcash.Daos.Account daoAccount;

    public
    Account(Application application) {
        RoomDb db = RoomDb.getInstance(application);
        daoAccount = db.GetDaoAccount();
    }

    public void insert(com.b3to.newcash.Models.Account model) {
        new TaskInsert(daoAccount).execute(model);
    }

    public void update(com.b3to.newcash.Models.Account model) {
        new TaskUpdate(daoAccount).execute(model);
    }

    public void delete(com.b3to.newcash.Models.Account model) {
        new TaskDelete(daoAccount).execute(model);
    }

    public LiveData<List<com.b3to.newcash.Models.Account>> readAll() {
        return daoAccount.readAll();
    }

    public LiveData<List<Q_Account>> readAllQ() {
        return daoAccount.readAllQ();
    }

    public LiveData<List<Q_Transaction>> readTransactions(long account_id) {
        return daoAccount.readAllTransactions(account_id);
    }

    //Task classes
    static class TaskInsert extends AsyncTask<com.b3to.newcash.Models.Account, Void, Void> {
        com.b3to.newcash.Daos.Account dao;

        public TaskInsert(com.b3to.newcash.Daos.Account dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.b3to.newcash.Models.Account... accounts) {
            dao.insert(accounts[0]);
            return null;
        }
    }

    static class TaskUpdate extends AsyncTask<com.b3to.newcash.Models.Account, Void, Void> {
        com.b3to.newcash.Daos.Account dao;

        public TaskUpdate(com.b3to.newcash.Daos.Account dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.b3to.newcash.Models.Account... accounts) {
            dao.update(accounts[0]);
            return null;
        }
    }

    static class TaskDelete extends AsyncTask<com.b3to.newcash.Models.Account, Void, Void> {
        com.b3to.newcash.Daos.Account dao;

        public TaskDelete(com.b3to.newcash.Daos.Account dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.b3to.newcash.Models.Account... accounts) {
            dao.delete(accounts[0]);
            return null;
        }
    }
}
