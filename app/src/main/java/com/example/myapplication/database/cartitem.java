package com.example.myapplication.database;

public class cartitem {

    private String NamaMakanan, Alamat, Harga;

    public cartitem() {

    }
    public cartitem(String NamaMakanan, String Alamat, String Harga) {
        this.NamaMakanan = NamaMakanan;
        this.Alamat = Alamat;
        this.Harga = Harga;
    }

    public String getNamaMakanan() {
        return NamaMakanan;
    }

    public void setNamaMakanan(String NamaMakanan) {
        this.NamaMakanan = NamaMakanan;
    }

    public String getAlamat() {
        return Alamat;
    }

    // setter method for all variables.
    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String Harga) {
        this.Harga = Harga;
    }
}
