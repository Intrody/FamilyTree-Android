package com.example.familytree.com.example.mem;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.familytree.com.example.mem.Mem;

import java.util.List;

@Dao
public interface MemDao {
    @Insert
    void insertMems(Mem...mems);

    @Update
    void updateMems(Mem...mems);

    @Delete
    void deleteMems(Mem...mems);

    @Query("DELETE FROM MEM")
    void deleteAllMems();

    @Query("SELECT * FROM MEM ORDER BY MEMBERID ASC")
    LiveData<List<Mem>>getAllMemsLive();

    @Query("SELECT * FROM MEM WHERE member_name LIKE :patten ORDER BY memberId ASC")
    LiveData<List<Mem>>findMemsWithPatten(String patten);

    @Query("SELECT * FROM MEM WHERE memberId = :id")
    LiveData<List<Mem>>findMemWithId(String id);

}
