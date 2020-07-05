package com.example.familytree.com.example.mem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.familytree.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Member_info extends AppCompatActivity {

    TextView textViewMemId,textViewName,textViewGenId,textViewSex,textViewBorn,textViewAdd,textViewPat,textViewDai,textViewParents,textViewChildren;
    FloatingActionButton floatingActionButtonChange;
    MemViewModel memViewModel;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_info);
        setTitle("成员信息详情");
        Intent intent = getIntent();
        final String id = intent.getStringExtra("ID");
        Log.i("TAG", id);
        memViewModel = new ViewModelProvider(this).get(MemViewModel.class);
        textViewMemId = findViewById(R.id.textViewMemId);
        textViewName = findViewById(R.id.textViewMemName);
        textViewParents = findViewById(R.id.textViewMemParents);
        textViewAdd = findViewById(R.id.textViewMemAdd);
        textViewBorn = findViewById(R.id.textViewMemBorn);
        textViewChildren = findViewById(R.id.textViewMemChidren);
        textViewDai = findViewById(R.id.textViewDai);
        textViewPat = findViewById(R.id.textViewPat);
        textViewSex = findViewById(R.id.textViewMemSex);
        textViewGenId = findViewById(R.id.textViewGenId);

         memViewModel.findMemWithId(id).observe(this, new Observer<List<Mem>>() {
            @Override
            public void onChanged(List<Mem> mems) {
                    StringBuilder text = new StringBuilder();
                    Mem mem = mems.get(0);
                    text.append(mem.getMemberId());
//                    StringBuilder name = new StringBuilder();
//                    text.append(mem.getMemberName());
                    String name = mem.getMemberName();
                    String genid = mem.getGenId();
                    String sex = mem.getMemberSex();
                    String born = mem.getMemberBorn();
                    String add = mem.getMemberAddress();
                    String pat = mem.getMemberPat();
                    String dai = mem.getMemberDai();
                    String parents = mem.getMemberParents();
                    String children = mem.getMemberChildren();
                    Log.i("TEXT", String.valueOf(text));
                    textViewMemId.setText(text.toString());
                    textViewName.setText(name);
                    textViewGenId.setText(genid);
                    textViewSex.setText(sex);
                    textViewBorn.setText(born);
                    textViewAdd.setText(add);
                    if(!pat.isEmpty())
                    textViewPat.setText(pat);
                    textViewDai.setText("第"+dai+"代");
                    textViewParents.setText(parents);
                    if(!children.isEmpty())
                    textViewChildren.setText(children);
            }
        });
         floatingActionButtonChange = findViewById(R.id.floatingActionButtonChange);
         floatingActionButtonChange.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(Member_info.this,MemChange.class);
                 intent.putExtra("ID",id);
                 startActivity(intent);
                 finish();
             }
         });
    }



}