package com.example.familytree;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.familytree.Tree;
import com.example.familytree.TreeDao;
import com.example.familytree.TreeDataBase;

import java.util.List;

class TreeRepository {

    private LiveData<List<Tree>>allTreeLive;
    private TreeDao treeDao;

    TreeRepository(Context context){
        TreeDataBase treeDataBase = TreeDataBase.getDatabase(context.getApplicationContext());
        treeDao = treeDataBase.getTreeDao();
        allTreeLive = treeDao.getAllTreesLive();
    }

    void deleteTrees(Tree...trees){
        new DeleteAsyncTask(treeDao).execute(trees);
    }

    void insertTrees(Tree...trees){
        new InsertAsyncTask(treeDao).execute(trees);
    }

    LiveData<List<Tree>> getAllTreeLive(){
        return allTreeLive;
    }

    static class InsertAsyncTask extends AsyncTask<Tree, Void, Void> {
        private TreeDao treeDao;

        InsertAsyncTask(TreeDao treeDao) {
            this.treeDao = treeDao;
        }

        @Override
        protected Void doInBackground(Tree... trees) {
            treeDao.insertTrees(trees);
            return null;
        }

    }

    static class DeleteAsyncTask extends AsyncTask<Tree, Void, Void> {
        private TreeDao treeDao;

        DeleteAsyncTask(TreeDao treeDao) {
            this.treeDao = treeDao;
        }

        @Override
        protected Void doInBackground(Tree... trees) {
            treeDao.deleteTrees(trees);
            return null;
        }

    }
}
