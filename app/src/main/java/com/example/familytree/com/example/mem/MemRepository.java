package com.example.familytree.com.example.mem;

import android.content.Context;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;


import java.util.List;

public class MemRepository {
    private LiveData<List<Mem>>allMemLive;
    private MemDao memDao;

    MemRepository(Context context){
        MemDatabase memDatabase = MemDatabase.getDatabase(context.getApplicationContext());
        memDao = memDatabase.getMemDao();
        allMemLive = memDao.getAllMemsLive();
    }

    void insertMems(Mem...mems){
        new InsertAsyncTask(memDao).execute(mems);
    }

    void updateMems(Mem...mems){
        new UpdateAsyncTask(memDao).execute(mems);
    }

    void deleteMems(Mem...mems){
        new DeleteAsyncTask(memDao).execute(mems);
    }

    void deleteAllMems(Void...voids){
        new DeleteAllAsyncTask(memDao).execute();
    }

    LiveData<List<Mem>>getAllMemLive(){
        return allMemLive;
    }
    LiveData<List<Mem>>findMemsWithPatten(String patten){
        return  memDao.findMemsWithPatten("%"+patten+"%");
    }
    LiveData<List<Mem>>findMemWithId(String id){
        return memDao.findMemWithId(id);
    }


    static class InsertAsyncTask extends AsyncTask<Mem, Void, Void> {
        private MemDao memDao;

        InsertAsyncTask(MemDao memDao) {
            this.memDao = memDao;
        }

        @Override
        protected Void doInBackground(Mem... mems) {
            memDao.insertMems(mems);
            return null;
        }

    }

    static class UpdateAsyncTask extends AsyncTask<Mem, Void, Void> {
        private MemDao memDao;

        UpdateAsyncTask(MemDao memDao) {
            this.memDao = memDao;
        }

        @Override
        protected Void doInBackground(Mem... mems) {
            memDao.updateMems(mems);
            return null;
        }

    }

    static class DeleteAsyncTask extends AsyncTask<Mem, Void, Void> {
        private MemDao memDao;

        DeleteAsyncTask(MemDao memDao) {
            this.memDao = memDao;
        }

        @Override
        protected Void doInBackground(Mem... mems) {
            memDao.deleteMems(mems);
            return null;
        }

    }

    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private MemDao memDao;

        DeleteAllAsyncTask(MemDao memDao) {
            this.memDao = memDao;
        }

        @Override
        protected Void doInBackground(Void... mems) {
            memDao.insertMems();
            return null;
        }

    }





}
