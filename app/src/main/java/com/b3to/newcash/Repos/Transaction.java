package com.b3to.newcash.Repos;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.b3to.newcash.Db.RoomDb;
import com.b3to.newcash.QueryModels.Q_Transaction;

import java.util.List;

public class Transaction {
    com.b3to.newcash.Daos.Transaction daoAccount;

    public Transaction(Application application) {
        RoomDb db = RoomDb.getInstance(application);
        daoAccount = db.GetDaoTransaction();
    }

    public void insert(com.b3to.newcash.Models.Transaction model) {
        new TaskInsert(daoAccount).execute(model);
    }

    public void update(com.b3to.newcash.Models.Transaction model) {
        new TaskUpdate(daoAccount).execute(model);
    }

    public void delete(com.b3to.newcash.Models.Transaction model) {
        new TaskDelete(daoAccount).execute(model);
    }

    public LiveData<List<com.b3to.newcash.Models.Transaction>> readAll() {
        return daoAccount.readAll();
    }

    public LiveData<List<Q_Transaction>> readAllR() {
        return daoAccount.readAllR();
    }
    public LiveData<List<Q_Transaction>> readRecent(int count){
        return daoAccount.readRecent(count);
    }


    //Task classes
    static class TaskInsert extends AsyncTask<com.b3to.newcash.Models.Transaction, Void, Void> {
        com.b3to.newcash.Daos.Transaction dao;

        public TaskInsert(com.b3to.newcash.Daos.Transaction dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.b3to.newcash.Models.Transaction... tran) {
            dao.insert(tran[0]);
            return null;
        }
    }

    static class TaskUpdate extends AsyncTask<com.b3to.newcash.Models.Transaction, Void, Void> {
        com.b3to.newcash.Daos.Transaction dao;

        public TaskUpdate(com.b3to.newcash.Daos.Transaction dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.b3to.newcash.Models.Transaction... tran) {
            dao.update(tran[0]);
            return null;
        }
    }

    static class TaskDelete extends AsyncTask<com.b3to.newcash.Models.Transaction, Void, Void> {
        com.b3to.newcash.Daos.Transaction dao;

        public TaskDelete(com.b3to.newcash.Daos.Transaction dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.b3to.newcash.Models.Transaction... tran) {
            dao.delete(tran[0]);
            return null;
        }
    }
}
