package com.example.familytree;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.familytree.Tree;

import java.util.List;

@Dao
public interface TreeDao {
    @Insert
    void insertTrees(Tree...trees);

    @Update
    void updateTrees(Tree...trees);

    @Delete
    void deleteTrees(Tree...trees);

    @Query("DELETE FROM TREE")
    void  deleteAllTrees();

    @Query("SELECT * FROM TREE ORDER BY GENID ASC")

    LiveData<List<Tree>>getAllTreesLive();
}
