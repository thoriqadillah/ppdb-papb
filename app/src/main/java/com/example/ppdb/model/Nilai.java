package com.example.ppdb.model;

import java.util.HashMap;

public class Nilai {

    private String nilaiMathUN, nilaiBindoUN, nilaiIpaUN, nilaiMathUS, nilaiBindoUS, nilaiIpaUS, nilaiIpsUS;
    private HashMap<String, String> hashMap = new HashMap<>();

    public Nilai() {}

    public Nilai(String nilaiMathUN, String nilaiBindoUN, String nilaiIpaUN) {
        this.nilaiMathUN = nilaiMathUN;
        this.nilaiBindoUN = nilaiBindoUN;
        this.nilaiIpaUN = nilaiIpaUN;
    }

    public Nilai(String nilaiMathUS, String nilaiBindoUS, String nilaiIpaUS, String nilaiIpsUS) {
        this.nilaiMathUS = nilaiMathUS;
        this.nilaiBindoUS = nilaiBindoUS;
        this.nilaiIpaUS = nilaiIpaUS;
        this.nilaiIpsUS = nilaiIpsUS;
    }

    public void setNilaiMathUN(String nilaiMathUN) {
        this.nilaiMathUN = nilaiMathUN;
    }

    public void setNilaiMBindoUN(String nilaiBindoUN) {
        this.nilaiBindoUN = nilaiBindoUN;
    }

    public void setNilaiIpaUN(String nilaiIpaUN) {
        this.nilaiIpaUN = nilaiIpaUN;
    }

    public void setNilaiMathUS(String nilaiMathUS) {
        this.nilaiMathUS = nilaiMathUS;
    }

    public void setNilaiMBindoUS(String nilaiBindoUS) {
        this.nilaiBindoUS = nilaiBindoUS;
    }

    public void setNilaiIpaUS(String nilaiIpaUS) {
        this.nilaiIpaUS = nilaiIpaUS;
    }

    public void setNilaiIpsUS(String nilaiIpsUS) {
        this.nilaiIpsUS = nilaiIpsUS;
    }


    public String getNilaiMathUN() {
        return nilaiMathUN;
    }

    public String getNilaiBindoUN() {
        return nilaiBindoUN;
    }

    public String getNilaiIpaUN() {
        return nilaiIpaUN;
    }

    public String getNilaiMathUS() {
        return nilaiMathUS;
    }

    public String getNilaiBindoUS() {
        return nilaiBindoUS;
    }

    public String getNilaiIpaUS() {
        return nilaiIpaUS;
    }

    public String getNilaiIpsUS() {
        return nilaiIpsUS;
    }

}
