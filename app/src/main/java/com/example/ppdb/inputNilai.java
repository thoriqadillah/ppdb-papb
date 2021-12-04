package com.example.ppdb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class inputNilai extends AppCompatActivity implements View.OnClickListener {

    EditText inputMathUN, inputBindoUN, inputIpaUN, inputMathUS, inputBindoUS, inputIpaUS, inputIpsUS;
    TextView txvSKHUN, txvIjasah;
    Button btnSimpan, btnSKHUN, btnIjasah;
    private static final int RC_Take_Photo = 0;
    private static final int RC_Take_From_Gallery = 1;
    public Uri imageUri;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase db1 = FirebaseDatabase.getInstance("https://ppdb-papb-1a3c3-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference dbReference1 = db1.getReference("Siswa");
    FirebaseUser userSiswa = firebaseAuth.getCurrentUser();
    Nilai nilai;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    String currentPhotoPath;

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
        txvSKHUN = findViewById(R.id.txvSKHUNname);

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(this);

        btnSKHUN = findViewById(R.id.btnSKHUN);
        btnSKHUN.setOnClickListener(this);

        btnIjasah = findViewById(R.id.btnIjasah);
        btnIjasah.setOnClickListener(this);

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

        if(view.getId() == btnSKHUN.getId()){
            choosePicture();
        }

//        if(view.getId() == btnIjasah.getId()){
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
//                    String[] Permisions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
//                    requestPermissions(Permisions, 100);
//                } else {
//                    selectImage(inputNilai.this);
//
//                }
//            } else {
//                selectImage(inputNilai.this);
//
//            }
//        }

    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            uploadPicture();

        }
    }

    private void uploadPicture() {
        StorageReference SKHUNref = storageRef.child("SKHUN/image/" + userSiswa.getUid());

        SKHUNref.putFile(imageUri)
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
                                "can't upload Image, ",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

    }
}
