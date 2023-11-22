package com.example.myapplication.database;

public class itemTambahM {
    private String NamaMakanan, Alamat, Harga;

    public itemTambahM() {

    }

    public itemTambahM(String NamaMakanan,String Alamat,String Harga) {
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
