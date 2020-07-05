package com.example.familytree.com.example.mem;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity

public class Mem {
    @PrimaryKey(autoGenerate = true)
    private int memberId;

    @ColumnInfo(name = "member_name")
    private String memberName;

    @ColumnInfo(name = "gen_id")
    private String genId;

    @ColumnInfo(name = "member_sex")
    private String memberSex;

    @ColumnInfo(name = "member_born")
    private String memberBorn;

    @ColumnInfo(name = "member_address")
    private String memberAddress;

    @ColumnInfo(name = "member_pat")
    private  String memberPat;

    @ColumnInfo(name = "member_parents")
    private String memberParents;

    @ColumnInfo(name = "member_children")
    private String memberChildren;

    @ColumnInfo(name = "member_dai")
    private String memberDai;

    public Mem(String memberName, String genId, String memberSex, String memberBorn, String memberAddress, String memberPat, String memberParents, String memberChildren, String memberDai) {
        this.memberName = memberName;
        this.genId = genId;
        this.memberSex = memberSex;
        this.memberBorn = memberBorn;
        this.memberAddress = memberAddress;
        this.memberPat = memberPat;
        this.memberParents = memberParents;
        this.memberChildren = memberChildren;
        this.memberDai = memberDai;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getGenId() {
        return genId;
    }

    public void setGenId(String genId) {
        this.genId = genId;
    }

    public String getMemberSex() {
        return memberSex;
    }

    public void setMemberSex(String memberSex) {
        this.memberSex = memberSex;
    }

    public String getMemberBorn() {
        return memberBorn;
    }

    public void setMemberBorn(String memberBorn) {
        this.memberBorn = memberBorn;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public String getMemberPat() {
        return memberPat;
    }

    public void setMemberPat(String memberPat) {
        this.memberPat = memberPat;
    }

    public String getMemberParents() {
        return memberParents;
    }

    public void setMemberParents(String memberParents) {
        this.memberParents = memberParents;
    }

    public String getMemberChildren() {
        return memberChildren;
    }

    public void setMemberChildren(String memberChildren) {
        this.memberChildren = memberChildren;
    }

    public String getMemberDai() {
        return memberDai;
    }

    public void setMemberDai(String memberDai) {
        this.memberDai = memberDai;
    }
}
