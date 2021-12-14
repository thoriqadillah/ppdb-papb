package com.example.ppdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class PengumumanLolos extends AppCompatActivity implements View.OnClickListener {

    TextView txSelamat, txSelamat1, txSelamat2, txSelamat3;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase db1 = FirebaseDatabase.getInstance("https://ppdb-papb-1a3c3-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference dbReference1 = db1.getReference("Siswa");
    FirebaseUser userSiswa = firebaseAuth.getCurrentUser();
    DatabaseReference dbNilai = db1.getReference("Siswa").child(userSiswa.getUid()).child("Nilai");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengumuman_lolos);

        txSelamat=findViewById(R.id.txSelamat);
        txSelamat1=findViewById(R.id.txSelamat1);
        txSelamat2=findViewById(R.id.txSelamat2);
        txSelamat3=findViewById(R.id.txSelamat3);

        dbNilai.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Integer rerataUN=0, rerataUS=0;
                    rerataUN = Integer.valueOf(snapshot.child("rerataUN").getValue(String.class));
                    rerataUS = Integer.valueOf(snapshot.child("rerataUS").getValue(String.class));

                    if(rerataUN>80 && rerataUS>80){
                        txSelamat.setText("Selamat");
                        txSelamat.setBackgroundColor(Color.parseColor("#74FF43"));
                    }else {
                        txSelamat.setText("Maaf");
                        txSelamat.setBackgroundColor(Color.parseColor("#FF4444"));
                        txSelamat1.setText("Anda gagal diterima di");
                        txSelamat3.setText("Silahkan kembali ke halaman utama");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}