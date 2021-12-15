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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


public class dataDiri extends AppCompatActivity  implements View.OnClickListener {

    EditText inputNISN, inputNamalkp, inputTempatLahir, inputTanggalLahir, inputjenis, inputAgama, inputKota, inputAsalSekolah, inputAlamat;
    TextView tvFoto;
    Button btnImgFoto, btnSimpanDataDiri;
    private static final int RC_FOTO = 0;
    public Uri imageUriFoto;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase db2 = FirebaseDatabase.getInstance("https://ppdb-papb-1a3c3-default-rtdb.asia-southeast1.firebasedatabase.app");
    FirebaseUser userSiswa = firebaseAuth.getCurrentUser();
    DatabaseReference dbSiswa = db2.getReference(Siswa.class.getSimpleName()).child(userSiswa.getUid());

    String nisn, namaLengkap, tempatLahir, tglLahir, jenisKelamin, alamat, agama, asalKota, asalSekolah;
    Siswa siswa = Siswa.getInstance();

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datadiri);

        tvFoto = findViewById(R.id.tvFoto);
        inputNISN = findViewById(R.id.inputNISN);
        inputNamalkp = findViewById(R.id.inputNamalkp);
        inputTempatLahir = findViewById(R.id.inputTempatLahir);
        inputTanggalLahir = findViewById(R.id.inputTanggalLahir);
        inputjenis = findViewById(R.id.inputjenis);
        inputAlamat = findViewById(R.id.inputAlamat);
        inputAgama = findViewById(R.id.inputAgama);
        inputKota = findViewById(R.id.inputKota);
        inputAsalSekolah = findViewById(R.id.inputAsalSekolah);

        btnImgFoto = findViewById(R.id.btnImgFoto);
        btnImgFoto.setOnClickListener(this);
        btnSimpanDataDiri = findViewById(R.id.btnSimpanDataDiri);
        btnSimpanDataDiri.setOnClickListener(this);


        dbSiswa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    nisn = snapshot.child("nisn").getValue(String.class);
                    inputNISN.setText(nisn);

                    namaLengkap = snapshot.child("namaLengkap").getValue(String.class);
                    inputNamalkp.setText(namaLengkap);

                    tempatLahir = snapshot.child("tempatLahir").getValue(String.class);
                    inputTempatLahir.setText(tempatLahir);

                    tglLahir = snapshot.child("tglLahir").getValue(String.class);
                    inputTanggalLahir.setText(tglLahir);

                    agama = snapshot.child("agama").getValue(String.class);
                    inputAgama.setText(agama);

                    jenisKelamin = snapshot.child("jenisKelamin").getValue(String.class);
                    inputjenis.setText(jenisKelamin);

                    alamat = snapshot.child("alamat").getValue(String.class);
                    inputAlamat.setText(alamat);

                    asalKota = snapshot.child("asalKota").getValue(String.class);
                    inputKota.setText(asalKota);

                    asalSekolah = snapshot.child("asalSekolak").getValue(String.class);
                    inputAsalSekolah.setText(asalSekolah);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == btnSimpanDataDiri.getId()){

            uploadPicture();

            siswa.setNisn(inputNISN.getText().toString());
            siswa.setNamaLengkap(inputNamalkp.getText().toString());
            siswa.setJenisKelamin(inputjenis.getText().toString());
            siswa.setTglLahir(inputTanggalLahir.getText().toString());
            siswa.setTempatLahir(inputTempatLahir.getText().toString());
            siswa.setAgama(inputAgama.getText().toString());
            siswa.setAlamat(inputAlamat.getText().toString());
            siswa.setAsalKota(inputKota.getText().toString());
            siswa.setAsalSekolak(inputAsalSekolah.getText().toString());


            dbSiswa.child(userSiswa.getUid()).setValue(siswa).addOnSuccessListener(addNilai -> {
                Toast.makeText(this, "Data berhasil tersimpan", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(failed -> {
                Toast.makeText(this, "Error: " + failed.getMessage(), Toast.LENGTH_SHORT).show();
            });

            finish();
        }

        if(view.getId() == btnImgFoto.getId()){
//            choosePicture();
            Intent intent3 = new Intent();
            intent3.setType("image/*");
            intent3.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent3, RC_FOTO);
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
                        Toast.makeText(dataDiri.this, "Gagal Terunggah", Toast.LENGTH_SHORT).show();
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
                                "Image Uploaded", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(dataDiri.this, "can't upload Image", Toast.LENGTH_LONG).show();
            }
        });
    }
}
