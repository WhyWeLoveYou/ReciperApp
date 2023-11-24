package com.example.myapplication.database;

public class itemData{


    private String NamaMakanan, Alamat, Gambar, documentId, url;

    public itemData() {

    }
    public itemData(String NamaMakanan, String Alamat, String Gambar, String documentId, String url) {
        this.NamaMakanan = NamaMakanan;
        this.Alamat = Alamat;
        this.Gambar = Gambar;
        this.documentId = documentId;
        this.url = url;
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

    public String getGambar() {
        return Gambar;
    }
    public void setGambar(String Gambar) {
        this.Gambar = Gambar;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl(){
        return url;
    }
}
