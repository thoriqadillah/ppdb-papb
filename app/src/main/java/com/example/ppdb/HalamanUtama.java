package com.example.ppdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class HalamanUtama extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout drawer;
    Button idLogout, btnDataDiri, btnNilai, btnPengumuman;
    ImageView imgProfile;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser userSiswa = firebaseAuth.getCurrentUser();

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRefProfile;

    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);

        Toolbar toolbar = findViewById(R.id.idToolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        idLogout = findViewById(R.id.idLogout);
        NavigationView navigationView = findViewById(R.id.idNav_view);
        navigationView.setNavigationItemSelectedListener(this);

        imgProfile = findViewById(R.id.imgProfile);

        btnDataDiri = findViewById(R.id.btnDataDiri);
        btnNilai = findViewById(R.id.btnNilai);
        btnPengumuman = findViewById(R.id.btnPengumuman);

        btnDataDiri.setOnClickListener(this);
        btnNilai.setOnClickListener(this);
        btnPengumuman.setOnClickListener(this);

        storageRefProfile = storage.getReference(userSiswa.getUid() + "/Foto/" + userSiswa.getUid());

        refreshLayout = findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProfilePic();
                refreshLayout.setRefreshing(false);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getProfilePic();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.idLogout:
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_logout)
                        .setTitle("PPDB SMA PAPB")
                        .setMessage("Apakah kamu yakin?")
                        .setPositiveButton(Html.fromHtml("<font color='#000000'>Ok</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                Intent intentOut = new Intent(HalamanUtama.this, LoginActivity.class);
                                startActivity(intentOut);
                                finish();
                            }
                        })
                        .setNegativeButton(Html.fromHtml("<font color='#000000'>Batal</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
                break;
            case R.id.idDataDiri:
                Intent intentDataDiri = new Intent(HalamanUtama.this, dataDiri.class);
                startActivity(intentDataDiri);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        if (view.getId() == btnDataDiri.getId()) {
            intent = new Intent(HalamanUtama.this, dataDiri.class);
            startActivity(intent);
        } else if (view.getId() == btnNilai.getId()) {
            intent = new Intent(HalamanUtama.this, inputNilai.class);
            startActivity(intent);
        } else if (view.getId() == btnPengumuman.getId()) {
            intent = new Intent(HalamanUtama.this, PengumumanActivity.class);
            startActivity(intent);
        }
    }

    private void getProfilePic() {
        storageRefProfile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String imageURL = uri.toString();
                Glide.with(getApplicationContext()).load(imageURL).into(imgProfile);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }

}