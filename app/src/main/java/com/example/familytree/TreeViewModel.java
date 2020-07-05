package com.example.familytree;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.familytree.Tree;
import com.example.familytree.TreeRepository;

import java.util.List;

public class TreeViewModel extends AndroidViewModel {
    private TreeRepository treeRepository;
    public TreeViewModel(@NonNull Application application) {
        super(application);
        treeRepository = new TreeRepository(application);
    }
    LiveData<List<Tree>> getAllTreeLive() {return treeRepository.getAllTreeLive();}

    void insertTrees(Tree...trees) {treeRepository.insertTrees(trees);}
    void deleteTrees(Tree...trees) {treeRepository.deleteTrees(trees);}


}
