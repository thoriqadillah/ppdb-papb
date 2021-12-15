package com.example.ppdb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ppdb.model.Siswa;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class inputNilai extends AppCompatActivity implements View.OnClickListener {

    EditText inputMathUN, inputBindoUN, inputIpaUN, inputMathUS, inputBindoUS, inputIpaUS, inputIpsUS;
    TextView txvSKHUN, txvIjasah;
    Button btnSimpan, btnSKHUN, btnIjasah;
    private static final int RC_SKHUN = 0;
    private static final int RC_Ijasah = 1;
    public Uri imageUriSKHUN;
    public Uri imageUriIjasah;
    String mathUN, bindoUN, ipaUN, mathUS, bindoUS, ipaUS, ipsUS;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase db1 = FirebaseDatabase.getInstance("https://ppdb-papb-1a3c3-default-rtdb.asia-southeast1.firebasedatabase.app");
    FirebaseUser userSiswa = firebaseAuth.getCurrentUser();
    DatabaseReference dbSiswa = db1.getReference(Siswa.class.getSimpleName()).child(userSiswa.getUid());
//    DatabaseReference dbNilai = db1.getReference("Siswa").child(userSiswa.getUid()).child("Nilai");

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    Siswa siswa = Siswa.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_nilai);

        inputMathUN = findViewById(R.id.inputMathUN);
        inputBindoUN = findViewById(R.id.inputBindoUN);
        inputIpaUN = findViewById(R.id.inputIpaUN);
        txvSKHUN = findViewById(R.id.txvSKHUNname);

        inputMathUS = findViewById(R.id.inputMathUS);
        inputBindoUS = findViewById(R.id.inputBindoUS);
        inputIpaUS = findViewById(R.id.inputIpaUS);
        inputIpsUS = findViewById(R.id.inputIpsUS);
        txvIjasah = findViewById(R.id.txvIjasahname);

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(this);

        btnSKHUN = findViewById(R.id.btnSKHUN);
        btnSKHUN.setOnClickListener(this);

        btnIjasah = findViewById(R.id.btnIjasah);
        btnIjasah.setOnClickListener(this);

        dbSiswa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    mathUN = String.valueOf(snapshot.child("nilaiMathUN").getValue());
                    inputMathUN.setText(mathUN);

                    bindoUN = String.valueOf(snapshot.child("nilaiBindoUN").getValue());
                    inputBindoUN.setText(bindoUN);

                    ipaUN = String.valueOf(snapshot.child("nilaiIpaUN").getValue());
                    inputIpaUN.setText(ipaUN);

                    mathUS = String.valueOf(snapshot.child("nilaiMathUS").getValue());
                    inputMathUS.setText(mathUS);

                    bindoUS = String.valueOf(snapshot.child("nilaiBindoUS").getValue());
                    inputBindoUS.setText(bindoUS);

                    ipaUS = String.valueOf(snapshot.child("nilaiIpaUS").getValue());
                    inputIpaUS.setText(ipaUS);

                    ipsUS = String.valueOf(snapshot.child("nilaiIpsUS").getValue());
                    inputIpsUS.setText(ipsUS);

                    siswa.setRerataUN(Integer.parseInt(mathUN), Integer.parseInt(bindoUN), Integer.parseInt(ipaUN));
                    siswa.setRerataUS(Integer.parseInt(mathUS), Integer.parseInt(bindoUS), Integer.parseInt(ipaUS), Integer.parseInt(ipsUS));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btnSimpan.getId()) {
            if (mathUN != null) {
                if (!mathUN.equals(inputMathUN.getText().toString())) {
                    siswa.setNilaiMathUN(Integer.parseInt(inputMathUN.getText().toString()));
                    dbSiswa.child("nilaiMathUN").setValue(siswa.getNilaiMathUN());
                    Toast.makeText(this, "Nilai berhasil terupdate", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Nilai tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }

            if (bindoUN != null) {
                if (!bindoUN.equals(inputBindoUN.getText().toString())) {
                    siswa.setNilaiBindoUN(Integer.parseInt(inputBindoUN.getText().toString()));
                    dbSiswa.child("nilaiBindoUN").setValue(siswa.getNilaiBindoUN());
                    Toast.makeText(this, "Nilai berhasil terupdate", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Nilai tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }

            if (ipaUN != null) {
                if (!ipaUN.equals(inputIpaUN.getText().toString())) {
                    siswa.setNilaiIpaUN(Integer.parseInt(inputIpaUN.getText().toString()));
                    dbSiswa.child("nilaiIpaUN").setValue(siswa.getNilaiIpaUN());
                    Toast.makeText(this, "Nilai berhasil terupdate", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Nilai tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }

            if (mathUS != null) {
                if (!mathUS.equals(inputMathUS.getText().toString())) {
                    siswa.setNilaiMathUS(Integer.parseInt(inputMathUS.getText().toString()));
                    dbSiswa.child("nilaiMathUS").setValue(siswa.getNilaiMathUS());
                    Toast.makeText(this, "Nilai berhasil terupdate", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Nilai tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }

            if (bindoUS != null) {
                if (!bindoUS.equals(inputBindoUS.getText().toString())) {
                    siswa.setNilaiBindoUS(Integer.parseInt(inputMathUN.getText().toString()));
                    dbSiswa.child("nilaiBindoUS").setValue(siswa.getNilaiBindoUS());
                    Toast.makeText(this, "Nilai berhasil terupdate", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Nilai tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }

            if (ipaUS != null) {
                if (!ipaUS.equals(inputIpaUS.getText().toString())) {
                    siswa.setNilaiIpaUS(Integer.parseInt(inputIpaUS.getText().toString()));
                    dbSiswa.child("nilaiIpaUS").setValue(siswa.getNilaiIpaUS());
                    Toast.makeText(this, "Nilai berhasil terupdate", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Nilai tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }

            if (ipsUS != null) {
                if (!ipsUS.equals(inputIpsUS.getText().toString())) {
                    siswa.setNilaiIpsUS(Integer.parseInt(inputIpsUS.getText().toString()));
                    dbSiswa.child("nilaiIpsUS").setValue(siswa.getNilaiIpsUS());
                    Toast.makeText(this, "Nilai berhasil terupdate", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Nilai tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }

            if (mathUN != null && bindoUN != null && ipaUN != null) {
                siswa.setRerataUN(Integer.parseInt(inputMathUN.getText().toString()), Integer.parseInt(inputBindoUN.getText().toString()),
                        Integer.parseInt(inputIpaUN.getText().toString()));
                dbSiswa.child("rerataUN").setValue(siswa.getRerataUN());
            } if (mathUS != null && bindoUS != null && ipaUS != null && ipsUS != null) {
                siswa.setRerataUS(Integer.parseInt(inputMathUS.getText().toString()), Integer.parseInt(inputBindoUS.getText().toString()),
                        Integer.parseInt(inputIpaUS.getText().toString()), Integer.parseInt(inputIpsUS.getText().toString()));
                dbSiswa.child("rerataUS").setValue(siswa.getRerataUS());
            } else {
                uploadPicture();

                siswa.setNilaiMathUN(Integer.parseInt(inputMathUN.getText().toString()));
                siswa.setNilaiBindoUN(Integer.parseInt(inputBindoUN.getText().toString()));
                siswa.setNilaiIpaUN(Integer.parseInt(inputIpaUN.getText().toString()));
                siswa.setNilaiMathUS(Integer.parseInt(inputMathUS.getText().toString()));
                siswa.setNilaiBindoUS(Integer.parseInt(inputBindoUS.getText().toString()));
                siswa.setNilaiIpaUS(Integer.parseInt(inputIpaUS.getText().toString()));
                siswa.setNilaiIpsUS(Integer.parseInt(inputIpsUS.getText().toString()));
                siswa.setRerataUN(Integer.parseInt(inputMathUN.getText().toString()), Integer.parseInt(inputBindoUN.getText().toString()),
                        Integer.parseInt(inputIpaUN.getText().toString()));
                siswa.setRerataUS(Integer.parseInt(inputMathUS.getText().toString()), Integer.parseInt(inputBindoUS.getText().toString()),
                        Integer.parseInt(inputIpaUS.getText().toString()), Integer.parseInt(inputIpsUS.getText().toString()));

                dbSiswa.setValue(siswa).addOnSuccessListener(success -> {
                    Toast.makeText(this, "Nilai berhasil tersimpan", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(failed -> {
                    Toast.makeText(this, "Error: " + failed.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }

            finish();
        }


        if (view.getId() == btnSKHUN.getId()) {
//            choosePicture();
            Intent intent1 = new Intent();
            intent1.setType("image/*");
            intent1.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent1, RC_SKHUN);
        }

        if (view.getId() == btnIjasah.getId()) {
//            choosePicture();
            Intent intent2 = new Intent();
            intent2.setType("image/*");
            intent2.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent2, RC_Ijasah);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RC_SKHUN:
                if(resultCode==RESULT_OK){
                    imageUriSKHUN = data.getData();
                    if(imageUriSKHUN!=null){
                        txvSKHUN.setText("Berhasil terunggah");
                    }else{
                        Toast.makeText(inputNilai.this,
                                "Gagal Terunggah",
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                }
                break;
            case RC_Ijasah:
                if(resultCode==RESULT_OK){
                    imageUriIjasah = data.getData();
                    if(imageUriIjasah!=null){
                        txvIjasah.setText("Berhasil terunggah");
                    }else{
                        Toast.makeText(inputNilai.this,
                                "Gagal Terunggah",
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                }
                break;

        }
    }

    private void uploadPicture() {
        StorageReference SKHUNref = storageRef.child(userSiswa.getUid() + "/SKHUN/" + userSiswa.getUid());
        StorageReference Ijasahref = storageRef.child(userSiswa.getUid() + "/Ijasah/" + userSiswa.getUid());

        SKHUNref.putFile(imageUriSKHUN)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(inputNilai.this,
                                "Image Uploaded",
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(inputNilai.this,
                                "can't upload Image",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
        Ijasahref.putFile(imageUriIjasah)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(inputNilai.this,
                                "Image Uploaded",
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(inputNilai.this,
                                "can't upload Image",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

    }
}
