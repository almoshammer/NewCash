package com.b3to.newcash.Repos;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.b3to.newcash.Db.RoomDb;
import com.b3to.newcash.QueryModels.Q_CurrencyOverView;

import java.util.List;

public class Currency {
    com.b3to.newcash.Daos.Currency dao;

    public Currency(Application application) {
        RoomDb db = RoomDb.getInstance(application);
        this.dao = db.GetDaoCurrency();
    }

    public void insert(com.b3to.newcash.Models.Currency model) {
        new TaskInsert(dao).execute(model);
    }

    public void update(com.b3to.newcash.Models.Currency model) {
        new TaskUpdate(dao).execute(model);
    }

    public void delete(com.b3to.newcash.Models.Currency model) {
        new TaskDelete(dao).execute(model);
    }

    public LiveData<List<com.b3to.newcash.Models.Currency>> readAll() {
        return dao.readAll();
    }

    public LiveData<List<Q_CurrencyOverView>> readAllOverView() {
        return dao.readAllOverView();
    }


    //Task classes
    static class TaskInsert extends AsyncTask<com.b3to.newcash.Models.Currency, Void, Void> {

        com.b3to.newcash.Daos.Currency dao;

        public TaskInsert(com.b3to.newcash.Daos.Currency dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.b3to.newcash.Models.Currency... models) {
            dao.insert(models[0]);
            return null;
        }
    }

    static class TaskUpdate extends AsyncTask<com.b3to.newcash.Models.Currency, Void, Void> {

        com.b3to.newcash.Daos.Currency dao;

        public TaskUpdate(com.b3to.newcash.Daos.Currency dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.b3to.newcash.Models.Currency... models) {
            dao.update(models[0]);
            return null;
        }
    }

    static class TaskDelete extends AsyncTask<com.b3to.newcash.Models.Currency, Void, Void> {

        com.b3to.newcash.Daos.Currency dao;

        public TaskDelete(com.b3to.newcash.Daos.Currency dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.b3to.newcash.Models.Currency... models) {
            dao.delete(models[0]);
            return null;
        }
    }
}
