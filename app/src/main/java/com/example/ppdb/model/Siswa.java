package com.example.ppdb.model;

import java.util.HashMap;

public class Siswa {

    private String siswaId, namaLengkap, email, password, nisn, tglLahir, tempatLahir, agama, asalKota, asalSekolak, jenisKelamin, alamat;
    private int nilaiMathUN, nilaiBindoUN, nilaiIpaUN, nilaiMathUS, nilaiBindoUS, nilaiIpaUS, nilaiIpsUS, rerataUN, rerataUS;
    private static Siswa instatce;

    public static Siswa getInstance() {
        if (instatce == null) {
            instatce = new Siswa();
        }
        return instatce;
    }

    private Siswa( String nisn, String namaLengkap, String jenisKelamin, String tglLahir, String tempatLahir, String agama, String alamat, String asalKota, String asalSekolak) {
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

    private Siswa(String namaLengkap, String email, String password) {
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.password = password;
    }

    private Siswa() {

    }

    public void setNilaiMathUN(int nilaiMathUN) {
        this.nilaiMathUN = nilaiMathUN;
    }

    public void setNilaiBindoUN(int nilaiBindoUN) {
        this.nilaiBindoUN = nilaiBindoUN;
    }

    public void setNilaiIpaUN(int nilaiIpaUN) {
        this.nilaiIpaUN = nilaiIpaUN;
    }

    public void setNilaiMathUS(int nilaiMathUS) {
        this.nilaiMathUS = nilaiMathUS;
    }

    public void setNilaiBindoUS(int nilaiBindoUS) {
        this.nilaiBindoUS = nilaiBindoUS;
    }

    public void setNilaiIpaUS(int nilaiIpaUS) {
        this.nilaiIpaUS = nilaiIpaUS;
    }

    public void setNilaiIpsUS(int nilaiIpsUS) {
        this.nilaiIpsUS = nilaiIpsUS;
    }

    public void setRerataUN(int nilaiMathUN, int nilaiBindoUN, int nilaiIpaUN) {
        this.rerataUN = (nilaiMathUN + nilaiBindoUN + nilaiIpaUN) / 3 ;
    }

    public void setRerataUS(int nilaiMathUS, int nilaiBindoUS, int nilaiIpaUS, int nilaiIpsUS) {
        this.rerataUS = (nilaiMathUS + nilaiBindoUS + nilaiIpaUS + nilaiIpsUS) / 4;
    }

    public int getNilaiMathUN() {
        return nilaiMathUN;
    }

    public int getNilaiBindoUN() {
        return nilaiBindoUN;
    }

    public int getNilaiIpaUN() {
        return nilaiIpaUN;
    }

    public int getNilaiMathUS() {
        return nilaiMathUS;
    }

    public int getNilaiBindoUS() {
        return nilaiBindoUS;
    }

    public int getNilaiIpaUS() {
        return nilaiIpaUS;
    }

    public int getNilaiIpsUS() {
        return nilaiIpsUS;
    }

    public int getRerataUN() {
        return rerataUN;
    }

    public int getRerataUS() {
        return rerataUS;
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
