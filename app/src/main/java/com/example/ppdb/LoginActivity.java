package com.example.ppdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLogin;
    TextView txtBtnKeDaftar;
    EditText inpEmail, inpPassword;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://ppdb-papb-1a3c3-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference dbReference = db.getReference(Siswa.class.getSimpleName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        txtBtnKeDaftar = findViewById(R.id.txtBtnKeRegister);
        txtBtnKeDaftar.setOnClickListener(this);

        inpEmail = findViewById(R.id.emailFormLogin);
        inpPassword = findViewById(R.id.passwordFormLogin);
    }

    // Sistemnya nanti setelah daftar lewat firebase auth nanti ada id nya disitu. Nah, id tsb jadi id di realtime db nya.
    // Jadi, kalo nanti mau update data, ngambil id siswanya dari FirebaseAuth.getCurrentUser().getUid() (mungkin). Habis itu update deh
    // Challange lain mungkin join data siswa ke nilai sih. Semangat ndes
    public void login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(login -> {
            if (login.isSuccessful()) {
                FirebaseUser siswa = firebaseAuth.getCurrentUser();

                if (siswa.isEmailVerified()) {
//                    Intent intent = new Intent(LoginActivity.this, HalamanUtama.class);
                    Intent intent = new Intent(LoginActivity.this, inputNilai.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Silahkan konfirmasi email terlebih dahulu", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Email atau password salah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        String email = inpEmail.getText().toString();
        String password = inpPassword.getText().toString();

        if (v.getId() == btnLogin.getId()) {
           login(email, password);
        }

        if (v.getId() == txtBtnKeDaftar.getId()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}