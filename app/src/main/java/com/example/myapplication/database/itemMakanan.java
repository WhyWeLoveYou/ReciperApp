package com.example.myapplication.database;

public class itemMakanan {
    private String NamaMakanan, Alamat, Harga, Deskripsi;

    public itemMakanan() {

    }

    public itemMakanan(String NamaMakanan,String Alamat,String Harga,String Deskripsi) {
        this.NamaMakanan = NamaMakanan;
        this.Alamat = Alamat;
        this.Harga = Harga;
        this.Deskripsi = Deskripsi;
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

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String Deskripsi) {
        this.Deskripsi = Deskripsi;
    }
}
