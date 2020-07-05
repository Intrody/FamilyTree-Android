package com.example.familytree.com.example.mem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.familytree.MyAdapter;
import com.example.familytree.R;

import java.util.List;

public class MemChange extends AppCompatActivity {

    private Button buttonMemSubmit;
    private EditText editTextMemName,editTextPat,editTextMemSex,editTextMemAddress,editTextGenId,editTextMemBorn,editTextDai,editTextParents,editTextChidren;
    private MemViewModel memViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_change);
        setTitle("成员信息修改");
        Intent intent = getIntent();
        final String id = intent.getStringExtra("ID");
        Log.i("TAGChange", id);
        buttonMemSubmit = findViewById(R.id.buttonMemSubmit);
        editTextDai = findViewById(R.id.editTextDai);
        editTextGenId = findViewById(R.id.editTextGenId);
        editTextMemAddress = findViewById(R.id.editTextMemAddress);
        editTextMemBorn = findViewById(R.id.editTextMemBorn);
        editTextMemName =findViewById(R.id.editTextMemName);
        editTextMemSex = findViewById(R.id.editTextMemSex);
        editTextPat = findViewById(R.id.editTextPat);
        editTextParents = findViewById(R.id.editTextParents);
        editTextChidren = findViewById(R.id.editTextChildren);
        buttonMemSubmit.setEnabled(false);
        memViewModel = new ViewModelProvider(this).get(MemViewModel.class);

        memViewModel.findMemWithId(id).observe(this, new Observer<List<Mem>>() {
            @Override
            public void onChanged(List<Mem> mems) {
                StringBuilder text = new StringBuilder();
                final Mem mem = mems.get(0);
                text.append(mem.getMemberId());
                String name = mem.getMemberName();
                String genid = mem.getGenId();
                String sex = mem.getMemberSex();
                String born = mem.getMemberBorn();
                String add = mem.getMemberAddress();
                String pat = mem.getMemberPat();
                String dai = mem.getMemberDai();
                String parents = mem.getMemberParents();
                String children = mem.getMemberChildren();
                editTextMemName.setText(name);
                editTextGenId.setText(genid);
                editTextMemSex.setText(sex);
                editTextMemBorn.setText(born);
                editTextMemAddress.setText(add);
                if(!pat.isEmpty())
                    editTextPat.setText(pat);
                editTextDai.setText(dai);
                editTextParents.setText(parents);
                if(!children.isEmpty())
                    editTextChidren.setText(children);
            }
        });



        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String name = editTextMemName.getText().toString().trim();
                String genid = editTextGenId.getText().toString().trim();
                String dai = editTextDai.getText().toString().trim();
                buttonMemSubmit.setEnabled(!name.isEmpty()&&!genid.isEmpty()&&!dai.isEmpty());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        editTextMemName.addTextChangedListener(textWatcher);
        editTextGenId.addTextChangedListener(textWatcher);
        editTextDai.addTextChangedListener(textWatcher);
        buttonMemSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextMemName.getText().toString().trim();
                String genid = editTextGenId.getText().toString().trim();
                String sex = editTextMemSex.getText().toString().trim();
                String born = editTextMemBorn.getText().toString().trim();
                String add = editTextMemAddress.getText().toString().trim();
                String pat = editTextPat.getText().toString().trim();
                String parents = editTextParents.getText().toString().trim();
                String children = editTextChidren.getText().toString().trim();
                String dai = editTextDai.getText().toString().trim();
                Mem mem = new Mem(name,genid,sex,born,add,pat,parents,children,dai);
                mem.setMemberId(Integer.parseInt(id));
                Log.i("SETID",id);
                memViewModel.updateMems(mem);
                Memember.instance.finish();
                Intent intent1 = new Intent(MemChange.this, Memember.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}
