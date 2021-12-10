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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ppdb.model.Nilai;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
        txvIjasah = findViewById(R.id.txvIjasahname);

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
            uploadPicture();
            nilai = new Nilai(inputMathUN.getText().toString(), inputBindoUN.getText().toString(),
                    inputIpaUN.getText().toString(), inputMathUS.getText().toString(),
                    inputBindoUS.getText().toString(), inputIpaUS.getText().toString(),
                    inputIpsUS.getText().toString());

            dbReference1.child(userSiswa.getUid()).child("Nilai").setValue(nilai).addOnSuccessListener(addNilai -> {
                Toast.makeText(this, "Nilai berhasil tersimpan", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(failed -> {
                Toast.makeText(this, "Error: " + failed.getMessage(), Toast.LENGTH_SHORT).show();
            });
            finish();
        }


        if(view.getId() == btnSKHUN.getId()){
//            choosePicture();
            Intent intent1 = new Intent();
            intent1.setType("image/*");
            intent1.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent1, RC_SKHUN);
        }

        if(view.getId() == btnIjasah.getId()){
//            choosePicture();
            Intent intent2 = new Intent();
            intent2.setType("image/*");
            intent2.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent2, RC_Ijasah);
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

//    private void choosePicture() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, 1);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
//            imageUri = data.getData();
//            if (imageUri!=null){
//
//            }
//            uploadPicture();
//
//        }
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
