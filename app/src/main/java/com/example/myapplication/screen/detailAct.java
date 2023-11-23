package com.example.myapplication.screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.itemTambahM;
import com.example.myapplication.databinding.ActivityDetailsBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class detailAct extends AppCompatActivity {

    private ActivityDetailsBinding binding;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        listener();
        getData();
    }

    private void listener() {
        binding.ImgBack.setOnClickListener(v -> {
            Intent val = new Intent(this, MainScreen.class);
            startActivity(val);
            finish();
        });

        binding.Bsimaaaobt.setOnClickListener(v -> {
            addData();
        });
    }

    private void getData() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        String namnya = getIntent().getStringExtra("jeneng");

        firebaseFirestore.collection("item_penjualan").document(namnya).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot documentSnapshot = task.getResult();
                if (documentSnapshot != null && documentSnapshot.exists()) {
                    String bytea = documentSnapshot.getString("gambar");
                    byte[] bytes = Base64.decode(bytea, Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    binding.InputEmal.setText(documentSnapshot.getString("deskripsi"));
                    binding.namaMakanan.setText(documentSnapshot.getString("namaMakanan"));
                    binding.Inputneme.setText(documentSnapshot.getString("harga"));
                    binding.Alamat.setText(documentSnapshot.getString("alamat"));
                    binding.imageProfile.setImageBitmap(bitmap);
                }
            }
        });
    }

    private String encodeImage(Bitmap bitmap) {
        int previewW = 150;
        int previewH = bitmap.getHeight() * previewW / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewW, previewH, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private void addData() {
        String NamaMakanan = binding.namaMakanan.getText().toString();
        String Alamat = binding.Alamat.getText().toString();
        String Harga = binding.Inputneme.getText().toString();
        Bitmap bitmap = ((BitmapDrawable)binding.imageProfile.getDrawable()).getBitmap();
        String Gambar = encodeImage(bitmap);
        String Deskripsi = binding.InputEmal.getText().toString();
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        UUID documentd = UUID.randomUUID();
        String documentId = String.valueOf(documentd);
        itemTambahM ITEM = new itemTambahM(NamaMakanan, Alamat, Harga, Gambar, documentId, Deskripsi);
        String email = auth.getCurrentUser().getEmail();

        firebaseFirestore.collection("users").document(email).collection("item").document(documentId)
                .set(ITEM).addOnSuccessListener(task -> {
                    Toast.makeText(getApplicationContext(), "Berhasil Menambahkan ke Cart", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
                    }
                });
}
}