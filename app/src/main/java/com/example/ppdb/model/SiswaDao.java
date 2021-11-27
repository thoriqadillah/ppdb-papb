package com.example.ppdb.model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SiswaDao {

    private DatabaseReference databaseReference;
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://ppdb-papb-1a3c3-default-rtdb.asia-southeast1.firebasedatabase.app");

    public SiswaDao() {

        this.databaseReference = db.getReference(Siswa.class.getSimpleName());
    }

    public Task<Void> addSiswa(Siswa siswa) {
        return databaseReference.push().setValue(siswa);
    }

//    public void isSiswaExist(String email, String password) {
//
//    }
}
