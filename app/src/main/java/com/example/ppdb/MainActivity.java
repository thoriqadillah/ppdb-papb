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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister;
    TextView txtKeLogin;
    EditText inpNamaLengkap, inpEmail, inpPassword;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://ppdb-papb-1a3c3-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference dbReference = db.getReference(Siswa.class.getSimpleName());

    Siswa siswa = Siswa.getInstance();

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

    // Sistemnya nanti setelah daftar lewat firebase auth nanti ada id nya disitu. Nah, id tsb jadi id di realtime db nya.
    // Jadi, kalo nanti mau update data, ngambil id siswanya dari FirebaseAuth.getCurrentUser().getUid() (mungkin). Habis itu update deh
    // Challange lain mungkin join data siswa ke nilai sih. Semangat ndes

    public void register(Siswa siswa) {
        firebaseAuth.createUserWithEmailAndPassword(siswa.getEmail(), siswa.getPassword()).addOnCompleteListener(register -> {
            if (register.isSuccessful()) {
                FirebaseUser userSiswa = firebaseAuth.getCurrentUser();

                userSiswa.sendEmailVerification();

                String siswaId = userSiswa.getUid();
                siswa.setSiswaId(siswaId);

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

                // menambahkan data ke database
                dbReference.child(siswaId).setValue(siswa).addOnSuccessListener(addSiswa -> {
                    Toast.makeText(this, "Pendaftaran berhasil, silahkan verifikasi email Anda dan Login", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(failed -> {
                    Toast.makeText(this, "Error: " + failed.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }

        }).addOnFailureListener(fail -> {
            Toast.makeText(this, "Error: " + fail.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onClick(View view) {
        String namaLengkap = inpNamaLengkap.getText().toString();
        String email = inpEmail.getText().toString();
        String password = inpPassword.getText().toString();
//        siswa = new Siswa(namaLengkap, email, password);
        siswa.setNamaLengkap(namaLengkap);
        siswa.setEmail(email);
        siswa.setPassword(password);
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
            register(siswa);
        }

        if (view.getId() == txtKeLogin.getId()) {
            startActivity(intent);
            finish();
        }

    }
}