package com.example.myapplication.screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivitySignInBinding;
import com.example.myapplication.helper.helpp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = FirebaseFirestore.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        } else {
            listener();
        }
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void listener() {
        binding.ButtonLogin.setOnClickListener(v -> {
            validator();
        });
        binding.CreateAccount.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUp.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
        binding.ForgotPw.setOnClickListener(v -> {
            showToast("Still in progress");
        });
    }

    private void userLogin() {
        String email = binding.InputEmal.getText().toString();
        String password = binding.InputPw.getText().toString();
        firebaseAuth.signInWithEmailAndPassword("email", "password").addOnCompleteListener(task -> {
            showToast("Anda Berhasil Login");
            Intent intent = new Intent(LoginActivity.this, SignUp.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    private void validator() {
        String email = binding.InputEmal.getText().toString();
        String password = binding.InputPw.getText().toString();
        if (email.isEmpty()) {
            showToast("Silahkan memasukan email");
        } else if (password.isEmpty()) {
            showToast("Silahkan memasukan password");
        } else {
            userLogin();
        }
    }
}