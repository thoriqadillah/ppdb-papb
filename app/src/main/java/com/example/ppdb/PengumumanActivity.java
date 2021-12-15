package com.example.ppdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ppdb.adapter.Adapter;
import com.example.ppdb.model.Siswa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class PengumumanActivity extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://ppdb-papb-1a3c3-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference dbReference = db.getReference(Siswa.class.getSimpleName());

    ArrayList<Siswa> listSiswa;
    Adapter adapter;

    private RecyclerView mRecylcer;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengumuman);

        mRecylcer = findViewById(R.id.idRvPeng);
        mRecylcer.setHasFixedSize(true);

        mManager = new LinearLayoutManager(this);
        mRecylcer.setLayoutManager(mManager);
        listSiswa = new ArrayList<>();
        adapter = new Adapter(this, listSiswa);
        mRecylcer.setAdapter(adapter);


        dbReference.orderByChild("rerataUN").limitToFirst(60).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Siswa siswa = dataSnapshot1.getValue(Siswa.class);
                    listSiswa.add(siswa);
                }
                Collections.reverse(listSiswa);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan",Toast.LENGTH_LONG).show();
            }
        });

    }
}