package com.example.ppdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ppdb.model.Siswa;
import com.example.ppdb.model.SiswaDao;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister;
    TextView txtKeLogin;
    EditText inpNamaLengkap, inpEmail, inpPassword;

    SiswaDao siswaDao = new SiswaDao();
    Siswa siswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        txtKeLogin = findViewById(R.id.txtBtnKeRegister);
        txtKeLogin.setOnClickListener(this);

        inpNamaLengkap = findViewById(R.id.namaLengkapFormRegister);
        inpEmail = findViewById(R.id.emailFormRegister);
        inpPassword = findViewById(R.id.passwordFormRegister);

    }

    public void register(String namaLengkap, String email, String password) {
        siswa = new Siswa(namaLengkap, email, password);
        siswaDao.addSiswa(siswa).addOnSuccessListener(success -> {
            Toast.makeText(this, "Pendaftaran berhasil, silahkan verifikasi email Anda dan Login", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(failed -> {
            Toast.makeText(this, ""+ failed.getMessage() +"", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onClick(View view) {
        String namaLengkap = inpNamaLengkap.getText().toString();
        String email = inpEmail.getText().toString();
        String password = inpPassword.getText().toString();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);

        if (TextUtils.isEmpty(namaLengkap)) {
            Toast.makeText(this, "Nama Lengkap wajib diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email wajib diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password wajib diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        if (view.getId() == btnRegister.getId()) {
            register(namaLengkap, email, password);

            startActivity(intent);
            finish();
        }

        if (view.getId() == txtKeLogin.getId()) {
            startActivity(intent);
            finish();
        }

    }
}