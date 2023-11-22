package com.example.myapplication.screen;

import android.view.View;
import android.widget.Button;

public class Item {

    String namaMakanan;
    String alamatMakanan;
    String tambahMakanan;
    int hargaMakanan;
    int image;
    Button button;

    public Item(String namaMakanan, int hargaMakanan, int image) {
        this.namaMakanan = namaMakanan;
        this.alamatMakanan = alamatMakanan;
        this.hargaMakanan = hargaMakanan;
        this.tambahMakanan = tambahMakanan;
        this.image = image;

    }


    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public String getAlamatMakanan() {
        return alamatMakanan;
    }

    public void setAlamatMakanan(String alamatMakanan) {
        this.alamatMakanan = alamatMakanan;
    }

    public String getTambahMakanan() {
        return tambahMakanan;
    }

    public void setTambahMakanan(String tambahMakanan) {
        this.tambahMakanan = tambahMakanan;
    }

    public int getHargaMakanan() {
        return hargaMakanan;
    }

    public void setHargaMakanan(int hargaMakanan) {
        this.hargaMakanan = hargaMakanan;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image =image;
    }
}