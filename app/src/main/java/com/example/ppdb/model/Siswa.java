package com.example.ppdb.model;

import java.util.HashMap;

public class Siswa {

    private String siswaId, namaLengkap, email, password, nisn, tglLahir, tempatLahir, agama, asalKota, asalSekolak, jenisKelamin, alamat;

    public Siswa( String nisn, String namaLengkap, String jenisKelamin, String tglLahir, String tempatLahir, String agama, String alamat, String asalKota, String asalSekolak) {
        this.nisn = nisn;
        this.namaLengkap = namaLengkap;
        this.jenisKelamin = jenisKelamin;
        this.tglLahir = tglLahir;
        this.tempatLahir = tempatLahir;
        this.agama = agama;
        this.alamat = alamat;
        this.asalKota = asalKota;
        this.asalSekolak = asalSekolak;
    }

    public Siswa(String namaLengkap, String email, String password) {
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setSiswaId(String siswaId) {
        this.siswaId = siswaId;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public void setAsalKota(String asalKota) {
        this.asalKota = asalKota;
    }

    public void setAsalSekolak(String asalSekolak) {
        this.asalSekolak = asalSekolak;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getSiswaId() {
        return siswaId;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNisn() {
        return nisn;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public String getAgama() {
        return agama;
    }

    public String getAsalKota() {
        return asalKota;
    }

    public String getAsalSekolak() {
        return asalSekolak;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }
}
