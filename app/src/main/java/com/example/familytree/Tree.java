package com.example.familytree;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tree {
    @PrimaryKey(autoGenerate = true)
    private  int genId;

    @ColumnInfo(name = "gen_name")
    private String genName;
    @ColumnInfo(name = "gen_number")
    private String genNumber;
    @ColumnInfo(name = "gen_maker")
    private String genMaker;

    public Tree(String genName, String genNumber, String genMaker) {
        this.genName = genName;
        this.genNumber = genNumber;
        this.genMaker = genMaker;
    }

    public int getGenId() {
        return genId;
    }

    public void setGenId(int genId) {
        this.genId = genId;
    }

    public String getGenName() {
        return genName;
    }

    public void setGenName(String genName) {
        this.genName = genName;
    }

    public String getGenNumber() {
        return genNumber;
    }

    public void setGenNumber(String genNumber) {
        this.genNumber = genNumber;
    }

    public String getGenMaker() {
        return genMaker;
    }

    public void setGenMaker(String genMaker) {
        this.genMaker = genMaker;
    }
}
