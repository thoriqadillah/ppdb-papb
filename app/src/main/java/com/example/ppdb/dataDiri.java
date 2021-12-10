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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ppdb.R;
import com.example.ppdb.model.Nilai;
import com.example.ppdb.model.Siswa;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class dataDiri extends AppCompatActivity  implements View.OnClickListener {

    EditText inputNISN, inputNamalkp, inputTempatLahir, inputTanggalLahir, inputjenis, inputAgama, inputKota, inputAsalSekolah;
    TextView tvFoto;
    Button btnImgFoto, btnSimpanDataDiri;
    private static final int RC_FOTO = 0;
    public Uri imageUriFoto;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase db2 = FirebaseDatabase.getInstance("https://ppdb-papb-1a3c3-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference dbReference2 = db2.getReference("Data Diri");
    FirebaseUser userSiswa = firebaseAuth.getCurrentUser();
    Siswa siswa;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    String currentPhotoPath;


    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_datadiri);


        
        inputNISN = findViewById(R.id.inputNISN);
        inputNamalkp = findViewById(R.id.inputNamalkp);
        inputTempatLahir = findViewById(R.id.inputTempatLahir);
        inputTanggalLahir = findViewById(R.id.inputTanggalLahir);
        inputjenis = findViewById(R.id.inputjenis);
        inputAgama = findViewById(R.id.inputAgama);
        inputKota = findViewById(R.id.inputKota);
        inputAsalSekolah = findViewById(R.id.inputAsalSekolah);

        btnSimpanDataDiri = findViewById(R.id.btnSimpanDataDiri);
        btnSimpanDataDiri.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {

        if(view.getId() == btnSimpanDataDiri.getId()){
            uploadPicture();
            siswa = new Siswa(inputNISN.getText().toString(), inputNamalkp.getText().toString(),
                    inputTempatLahir.getText().toString(), inputTanggalLahir.getText().toString(),
                    inputAgama.getText().toString(), inputKota.getText().toString(),inputAsalSekolah.getText().toString());

            dbReference2.child(userSiswa.getUid()).child("Data Diri").push().setValue(siswa).addOnSuccessListener(addNilai -> {
                Toast.makeText(this, "Nilai berhasil tersimpan", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(failed -> {
                Toast.makeText(this, "Error: " + failed.getMessage(), Toast.LENGTH_SHORT).show();
            });
        }
        if(view.getId() == btnImgFoto.getId()){
//            choosePicture();
            Intent intent1 = new Intent();
            intent1.setType("image/*");
            intent1.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent1, RC_FOTO);
        }


    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RC_FOTO:
                if (resultCode == RESULT_OK) {
                    imageUriFoto = data.getData();
                    if (imageUriFoto != null) {
                        tvFoto.setText("Berhasil terunggah");
                    } else {
                        Toast.makeText(dataDiri.this,
                                "Gagal Terunggah",
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                }
                break;

        }
    }
            private void uploadPicture () {
                StorageReference Fotoref = storageRef.child(userSiswa.getUid() + "/Foto/" + userSiswa.getUid());
                Fotoref.putFile(imageUriFoto)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(dataDiri.this,
                                        "Image Uploaded",
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(dataDiri.this,
                                        "can't upload Image",
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });
            }
        }
