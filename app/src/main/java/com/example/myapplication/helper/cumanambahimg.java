package com.example.myapplication.helper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.database.itemTambahM;
import com.example.myapplication.database.random;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;

public class cumanambahimg {
    private static FirebaseAuth auth;
    static FirebaseFirestore firebaseFirestore;



    public static void main(String[] args) {
        Bitmap bm = BitmapFactory.decodeFile("../res/drawable/makanan1.jpg");
        String itunya =  encodeImage(bm);
        byte[] bytes = Base64.decode(itunya, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        addDataToFirestore(itunya);
    }

    private static String encodeImage(Bitmap bitmap) {
        int previewW = 150;
        int previewH = bitmap.getHeight() * previewW / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewW, previewH, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private static void addDataToFirestore(String Gambar) {
        String nMakan = "Kepiting Jawa";
        String alamat = "Pelabuhan Merapi";
        String hrga = "20.000";
        String documentId = auth.getCurrentUser().getUid().toString();
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        itemTambahM ITEM = new itemTambahM(nMakan, alamat, hrga, Gambar, documentId);

        firebaseFirestore.collection("item_penjualan").document(nMakan).set(ITEM).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                System.out.println("Berhasil");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("gagal");
            }
        });

    }
}
