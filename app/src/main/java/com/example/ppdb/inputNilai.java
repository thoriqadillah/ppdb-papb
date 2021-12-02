package com.example.ppdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ppdb.model.Nilai;
import com.example.ppdb.model.Siswa;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class inputNilai extends AppCompatActivity implements View.OnClickListener {

    EditText inputMathUN, inputBindoUN, inputIpaUN, inputMathUS, inputBindoUS, inputIpaUS, inputIpsUS;
    Button btnSimpan;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase db1 = FirebaseDatabase.getInstance("https://ppdb-papb-1a3c3-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference dbReference1 = db1.getReference("Siswa");
    FirebaseUser userSiswa = firebaseAuth.getCurrentUser();
    Nilai nilai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_nilai);

        inputMathUN = findViewById(R.id.inputMathUN);
        inputBindoUN = findViewById(R.id.inputBindoUN);
        inputIpaUN = findViewById(R.id.inputIpaUN);

        inputMathUS = findViewById(R.id.inputMathUS);
        inputBindoUS = findViewById(R.id.inputBindoUS);
        inputIpaUS = findViewById(R.id.inputIpaUS);
        inputIpsUS = findViewById(R.id.inputIpsUS);

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if(view.getId() == btnSimpan.getId()){
            nilai = new Nilai(inputMathUN.getText().toString(), inputBindoUN.getText().toString(),
                    inputIpaUN.getText().toString(), inputMathUS.getText().toString(),
                    inputBindoUS.getText().toString(), inputIpaUS.getText().toString(),
                    inputIpsUS.getText().toString());
        }
        dbReference1.child(userSiswa.getUid()).child("Nilai").push().setValue(nilai).addOnSuccessListener(addNilai -> {
            Toast.makeText(this, "Nilai berhasil tersimpan", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(failed -> {
            Toast.makeText(this, "Error: " + failed.getMessage(), Toast.LENGTH_SHORT).show();
        });

    }
}