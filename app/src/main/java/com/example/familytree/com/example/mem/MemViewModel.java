package com.example.familytree.com.example.mem;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MemViewModel extends AndroidViewModel {
    private MemRepository memRepository;
    public MemViewModel(@NonNull Application application) {
        super(application);
        memRepository = new MemRepository(application);
    }

    LiveData<List<Mem>> getAllMemLive(){
        return memRepository.getAllMemLive();
    }
    LiveData<List<Mem>> findMemsWithPatten(String patten){
        return memRepository.findMemsWithPatten(patten);
    }
    LiveData<List<Mem>> findMemWithId(String id){
        return memRepository.findMemWithId(id);
    }

    void insertMems(Mem...mems){
        memRepository.insertMems(mems);
    }
    void updateMems(Mem...mems){
        memRepository.updateMems(mems);
    }
    void deleteMems(Mem...mems){
        memRepository.deleteMems(mems);
    }
    void deleteAllMems(){
        memRepository.deleteAllMems();
    }



}
