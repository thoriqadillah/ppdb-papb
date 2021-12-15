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

public class PengumumanActivity extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://ppdb-papb-1a3c3-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference dbReference = db.getReference(Siswa.class.getSimpleName());
    DatabaseReference dbReference2 = db.getReference(Siswa.class.getSimpleName()).child("Nilai");

    ArrayList<Siswa> list;
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
//        mManager.setReverseLayout(true);
//        mManager.setStackFromEnd(true);
        mRecylcer.setLayoutManager(mManager);
        list = new ArrayList<>();
        adapter = new Adapter(this, list);
        mRecylcer.setAdapter(adapter);


        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Siswa mhs = dataSnapshot1.getValue(Siswa.class);
                    list.add(mhs);
                }
                for (list : )
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan",Toast.LENGTH_LONG).show();
            }
        });

    }
}