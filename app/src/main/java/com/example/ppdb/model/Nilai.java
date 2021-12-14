package com.example.ppdb.model;

public class Nilai {

    private String nilaiMathUN, nilaiBindoUN, nilaiIpaUN, nilaiMathUS, nilaiBindoUS, nilaiIpaUS, nilaiIpsUS;
    private String rerataUN, rerataUS;

    public Nilai() {}

    public Nilai(String nilaiMathUN, String nilaiBindoUN, String nilaiIpaUN, String nilaiMathUS, String nilaiBindoUS, String nilaiIpaUS, String nilaiIpsUS) {
        this.nilaiMathUN = nilaiMathUN;
        this.nilaiBindoUN = nilaiBindoUN;
        this.nilaiIpaUN = nilaiIpaUN;
        this.nilaiMathUS = nilaiMathUS;
        this.nilaiBindoUS = nilaiBindoUS;
        this.nilaiIpaUS = nilaiIpaUS;
        this.nilaiIpsUS = nilaiIpsUS;
    }

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

    public void setNilaiBindoUN(String nilaiBindoUN) {
        this.nilaiBindoUN = nilaiBindoUN;
    }

    public void setNilaiBindoUS(String nilaiBindoUS) {
        this.nilaiBindoUS = nilaiBindoUS;
    }

    public void setRerataUN(String nilaiMathUN, String nilaiIpaUN, String nilaiBindoUN) {
        int rerata = (Integer.parseInt(nilaiMathUN) + Integer.parseInt(nilaiIpaUN) + Integer.parseInt(nilaiBindoUN)) / 3;
        this.rerataUN = String.valueOf(rerata);
    }

    public void setRerataUS(String nilaiMathUS, String nilaiIpaUS, String nilaiBindoUS, String nilaiIpsUS) {
        int rerata =  (Integer.parseInt(nilaiMathUS) + Integer.parseInt(nilaiIpaUS) + Integer.parseInt(nilaiBindoUS) + Integer.parseInt(nilaiIpsUS)) / 4;
        this.rerataUS = String.valueOf(rerata);
    }

    public String getRerataUN() {
        return rerataUN;
    }

    public String getRerataUS() {
        return rerataUS;
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
