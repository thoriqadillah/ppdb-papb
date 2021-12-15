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
            if (nisn != null) {
                if (!nisn.equals(inputNISN.getText().toString())) {
                    siswa.setNisn(inputNISN.getText().toString());
                    dbSiswa.child("nisn").setValue(siswa.getNisn());
                    Toast.makeText(this, "Data diri berhasil terupdate", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Data NISN tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
            if (namaLengkap != null) {
                if (!namaLengkap.equals(inputNamalkp.getText().toString())) {
                    siswa.setNamaLengkap(inputNamalkp.getText().toString());
                    dbSiswa.child("namaLengkap").setValue(siswa.getNamaLengkap());
                    Toast.makeText(this, "Data diri berhasil terupdate", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Data Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
            if (agama != null) {
                if (!agama.equals(inputAgama.getText().toString())) {
                    siswa.setAgama(inputAgama.getText().toString());
                    dbSiswa.child("agama").setValue(siswa.getAgama());
                    Toast.makeText(this, "Data diri berhasil terupdate", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Data Agama tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
            if (alamat != null) {
                if (!alamat.equals(inputAlamat.getText().toString())) {
                    siswa.setAlamat(inputAlamat.getText().toString());
                    dbSiswa.child("alamat").setValue(siswa.getAlamat());
                    Toast.makeText(this, "Data diri berhasil terupdate", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Data Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
            if (asalKota != null) {
                if (!asalKota.equals(inputKota.getText().toString())) {
                    siswa.setAsalKota(inputKota.getText().toString());
                    dbSiswa.child("asalKota").setValue(siswa.getAsalKota());
                    Toast.makeText(this, "Data diri berhasil terupdate", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Data Asal Kota tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
            if (asalSekolah != null) {
                if (!asalSekolah.equals(inputAsalSekolah.getText().toString())) {
                    siswa.setAsalSekolak(inputAsalSekolah.getText().toString());
                    dbSiswa.child("asalSekolak").setValue(siswa.getAsalSekolak());
                    Toast.makeText(this, "Data diri berhasil terupdate", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Data Asal Sekolah tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
            if (jenisKelamin != null) {
                if (!jenisKelamin.equals(inputjenis.getText().toString())) {
                    siswa.setJenisKelamin(inputjenis.getText().toString());
                    dbSiswa.child("jenisKelamin").setValue(siswa.getJenisKelamin());
                    Toast.makeText(this, "Data diri berhasil terupdate", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Data Jenis Kelamin tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
            if (tempatLahir != null) {
                if (!tempatLahir.equals(inputTempatLahir.getText().toString())) {
                    siswa.setTempatLahir(inputTempatLahir.getText().toString());
                    dbSiswa.child("tempatLahir").setValue(siswa.getTempatLahir());
                    Toast.makeText(this, "Data diri berhasil terupdate", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Data Tempat Lahir tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
            if (tglLahir != null) {
                if (!tglLahir.equals(inputTanggalLahir.getText().toString())) {
                    siswa.setTglLahir(inputTanggalLahir.getText().toString());
                    dbSiswa.child("tglLahir").setValue(siswa.getTglLahir());
                    Toast.makeText(this, "Data diri berhasil terupdate", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Data Tanggal Lahir tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }

            if (nisn == null && namaLengkap == null && agama == null && alamat == null && asalKota == null
                    && asalSekolah == null && jenisKelamin == null && tempatLahir == null && tglLahir == null){

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
            }



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