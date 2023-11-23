package com.example.myapplication.helper;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.itemTambahM;
import com.example.myapplication.databinding.ActivityCumanNambahDataBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

public class CumanNambahData extends AppCompatActivity {

    private ActivityCumanNambahDataBinding binding;
    private FirebaseFirestore firebaseFirestore;

    private String encodedImage;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCumanNambahDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imageProfile.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pickImage.launch(intent);
        });

        binding.ButtonLogin.setOnClickListener(v -> {
            if (validator()) {
                addDataToFirestore(encodedImage);
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

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            binding.imageProfile.setImageBitmap(bitmap);
                            binding.addingImage.setVisibility(View.GONE);
                            encodedImage = encodeImage(bitmap);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
    );

    private void addDataToFirestore(String Gambar) {
        String nMakan = binding.InputName.getText().toString();
        String alamat = binding.InputEmal.getText().toString();
        String hrga = binding.InputPw.getText().toString();
        String deskripsi = binding.description.getText().toString();
        UUID documentd = UUID.randomUUID();
        String documentId = String.valueOf(documentd);
        firebaseFirestore = FirebaseFirestore.getInstance();
        itemTambahM ITEM = new itemTambahM(nMakan, alamat, hrga, Gambar, documentId, deskripsi);

        firebaseFirestore.collection("item_penjualan").document(nMakan).set(ITEM).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(CumanNambahData.this, "Berhasil", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CumanNambahData.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Boolean validator() {
        if (binding.InputEmal.getText().toString() == null) {
            Toast.makeText(getApplicationContext(), "Tidak boleh ada satupun yang kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.InputName.getText().toString() == null) {
            Toast.makeText(getApplicationContext(), "Tidak boleh ada satupun yang kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.description.getText().toString() == null) {
            Toast.makeText(getApplicationContext(), "Tidak boleh ada satupun yang kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.InputPw.getText().toString() == null) {
            Toast.makeText(getApplicationContext(), "Tidak boleh ada satupun yang kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}