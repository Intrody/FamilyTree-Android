package com.example.familytree;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.ChipGroup;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("用户注册");
        setContentView(R.layout.activity_register);
        final EditText editTextId,editTextPassWord,editTextPassWordok;
        editTextId = findViewById(R.id.editText1);
        editTextPassWord = findViewById(R.id.editText2);
        editTextPassWordok = findViewById(R.id.editText3);
        final Button buttonRegister = findViewById(R.id.button);
        CheckBox checkBoxRead = findViewById(R.id.checkBoxRead);
        buttonRegister.setEnabled(false);
        checkBoxRead.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                    buttonRegister.setEnabled(true);
                else
                    buttonRegister.setEnabled(false);
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ps = editTextPassWord.getText().toString().trim();
                String psok = editTextPassWordok.getText().toString().trim();
                String id = editTextId.getText().toString().trim();
                if(psok.equals(ps) ){
                    Toast.makeText(Register.this,"密码正确！",Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = getSharedPreferences("userdata",MODE_PRIVATE).edit();
                    editor.putString("Id",id);
                    editor.putString("PassWord",ps);
                    editor.apply();
                    Register.this.finish();
                }
                else {
                    Toast.makeText(Register.this,"请正确输入两次密码",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
