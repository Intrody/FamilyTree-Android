package com.example.familytree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.PreferenceChangeEvent;

public class MainActivity extends AppCompatActivity {
    private  SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("登陆我的族谱");
        TextView textViewId,textViewPassWord,textViewRegister;
        final EditText editTextId,editTextPassWord;
        final Button buttonLogin;
        textViewRegister = findViewById(R.id.textViewRegister);
        editTextId = findViewById(R.id.editTextId);
        editTextPassWord = findViewById(R.id.editTextPassWord);
        pref = PreferenceManager.getDefaultSharedPreferences(this );
        rememberPass = findViewById(R.id.checkBox);
        boolean isRemember = pref.getBoolean("remember_password",false);
        if(isRemember){
            String id = pref.getString("id","");
            String pw = pref.getString("password","");
            editTextId.setText(id);
            editTextPassWord.setText(pw);
            rememberPass.setChecked(true);
        }
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new  Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setEnabled(false);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String name = editTextId.getText().toString().trim();
                String ps = editTextPassWord.getText().toString().trim();

                buttonLogin.setEnabled(!name.isEmpty()&&!ps.isEmpty());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        editTextId.addTextChangedListener(textWatcher);
        editTextPassWord.addTextChangedListener(textWatcher);
        if(rememberPass.isChecked()){
            buttonLogin.setEnabled(true);
        }
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefe = getSharedPreferences("userdata",MODE_PRIVATE);
                String username = prefe.getString("Id","");
                String userpassword = prefe.getString("PassWord","");
                String id = editTextId.getText().toString().trim();
                String password = editTextPassWord.getText().toString().trim();
                if (id.equals(username)&&password.equals(userpassword)){
                    Toast.makeText(MainActivity.this,"登陆成功！",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,MainTree.class);
                    startActivity(intent);
                    finish();
                    editor = pref.edit();
                    if(rememberPass.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("id",id);
                        editor.putString("password",password);
                    }
                    else {
                        editor.clear();
                    }
                    editor.apply();
                }
                else {
                    Toast.makeText(MainActivity.this,"用户名或密码输入错误",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
