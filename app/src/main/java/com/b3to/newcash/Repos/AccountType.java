package com.b3to.newcash.Repos;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.b3to.newcash.Db.RoomDb;
import com.b3to.newcash.QueryModels.Q_AccountType;

import java.util.List;

public class AccountType {

    com.b3to.newcash.Daos.AccountType dao;

    public AccountType(Application application) {
        RoomDb db = RoomDb.getInstance(application);
        dao = db.GetDaoAccountType();
    }

    public void insert(com.b3to.newcash.Models.AccountType model) {
        new TaskInsert(dao).execute(model);
    }

    public void update(com.b3to.newcash.Models.AccountType model) {
        new TaskUpdate(dao).execute(model);
    }

    public void delete(com.b3to.newcash.Models.AccountType model) {
        new TaskDelete(dao).execute(model);
    }

    public LiveData<List<com.b3to.newcash.Models.AccountType>> readAll() {
        return dao.readAll();
    }

    public LiveData<List<Q_AccountType>> readAllQ() {
        return dao.readAllQ();
    }

    public LiveData<List<Q_AccountType>> readAllQ(long account_id) {
        return dao.readAllQ(account_id);
    }

    //Task classes
    static class TaskInsert extends AsyncTask<com.b3to.newcash.Models.AccountType, Void, Void> {

        com.b3to.newcash.Daos.AccountType dao;

        public TaskInsert(com.b3to.newcash.Daos.AccountType dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.b3to.newcash.Models.AccountType... accountTypes) {
            dao.insert(accountTypes[0]);
            return null;
        }
    }

    static class TaskUpdate extends AsyncTask<com.b3to.newcash.Models.AccountType, Void, Void> {

        com.b3to.newcash.Daos.AccountType dao;

        public TaskUpdate(com.b3to.newcash.Daos.AccountType dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.b3to.newcash.Models.AccountType... accountTypes) {
            dao.update(accountTypes[0]);
            return null;
        }
    }

    static class TaskDelete extends AsyncTask<com.b3to.newcash.Models.AccountType, Void, Void> {

        com.b3to.newcash.Daos.AccountType dao;

        public TaskDelete(com.b3to.newcash.Daos.AccountType dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.b3to.newcash.Models.AccountType... accountTypes) {
            dao.delete(accountTypes[0]);
            return null;
        }
    }
}
