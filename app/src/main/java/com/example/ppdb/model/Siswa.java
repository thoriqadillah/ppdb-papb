package com.example.ppdb.model;

public class Siswa {

    private String namaLengkap, email, password, nisn, tglLahir, tempatLahir, agama, asalKota, asalSekolak, jenisKelamin;

    public Siswa() {}

    public Siswa(String namaLengkap, String email, String password) {
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.password = password;
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
