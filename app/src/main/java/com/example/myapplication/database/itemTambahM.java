package com.example.myapplication.database;

public class itemTambahM {
    private String NamaMakanan, Alamat, Harga, Gambar, documentId;

    public itemTambahM() {

    }

    public itemTambahM(String NamaMakanan, String Alamat,String Harga, String Gambar, String documentId) {
        this.NamaMakanan = NamaMakanan;
        this.Alamat = Alamat;
        this.Harga = Harga;
        this.Gambar = Gambar;
        this.documentId = documentId;
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

    public String getGambar() {
        return Gambar;
    }
    public void setGambar(String Gambar) {
        this.Gambar = Gambar;
    }

    public String getId() {
        return documentId;
    }
    public void setId(String id) {
        this.documentId = documentId;
    }
}
