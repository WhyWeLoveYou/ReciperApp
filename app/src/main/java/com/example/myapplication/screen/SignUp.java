package com.example.myapplication.screen;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import com.example.myapplication.databinding.ActivitySignUpBinding;
import com.example.myapplication.helper.helpp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private String encodedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = FirebaseFirestore.getInstance();
        listener();
    }

    private void listener() {
        binding.imageProfile.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pickImage.launch(intent);
        });
        binding.ButtonLogin.setOnClickListener(v -> {
            if (validator()) {
                CreateUser();
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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

    private void CreateUser() {
        String Email = binding.InputEmal.getText().toString();
        String Password = binding.InputPw.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(task -> {
            showToast("Berhasil membuat akun");
            HashMap<String, Object> user = new HashMap<>();
            user.put("Nama", binding.InputName.getText().toString());
            user.put("Email", binding.InputEmal.getText().toString());
            user.put("Image", encodedImage);
            db.collection("users").add(user).addOnCompleteListener(
                    documentReference -> {
                        showToast("Berhasil");
                    }
            );
        });
    }

    private Boolean validator() {
        String Email = binding.InputEmal.getText().toString();
        String Password = binding.InputPw.getText().toString();
        String Cpassword = binding.InputCPw.getText().toString();
        String Nama = binding.InputName.getText().toString();
        if (Password.isEmpty()) {
           showToast("Password kosong");
            return false;
        }
        if (Cpassword.isEmpty()) {
            showToast("Password kedua kosong");
            return false;
        }
        if (Email.isEmpty()) {
            showToast("Email Kosong");
            return false;
        }
        if (Nama.isEmpty()) {
            showToast("Nama Kosong");
            return false;
        }
        if (Password != Cpassword) {
            showToast("Password tidak sesuai");
            return false;
        }
        if (encodedImage == null) {
            showToast("Gambar Kosong");
            return false;
        }
        return true;
    }
}