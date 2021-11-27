package com.example.ppdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLogin;
    TextView txtBtnKeDaftar;
    EditText namaLengkap, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        txtBtnKeDaftar = findViewById(R.id.txtBtnKeRegister);
        txtBtnKeDaftar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //karena capek, jadi yang penting bisa masuk dulu
        //kurang auth nya
        //TODO: Bikin sistem login dan verifikasi email
        if (v.getId() == btnLogin.getId()) {
            Intent intent = new Intent(LoginActivity.this, HalamanUtama.class);
            startActivity(intent);
            finish();
        }

        if (v.getId() == txtBtnKeDaftar.getId()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}