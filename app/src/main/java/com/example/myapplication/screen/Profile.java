package com.example.myapplication.screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

import com.example.myapplication.databinding.ProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Profile extends AppCompatActivity {

    private ProfileBinding binding;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        listener();
        getUserDetail();
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void listener() {
        binding.ImgBack.setOnClickListener(v -> {
            //buat ke home
            showToast("Bentar");
        });
        binding.Bsimaaao.setOnClickListener(v -> {
            //buat ke update
            showToast("Bentar");
        });
    }

    private void getUserDetail() {
        String Cuser = auth.getCurrentUser().toString();
        firebaseFirestore.collection("users").document(Cuser).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot != null && documentSnapshot.exists()) {
                                Bitmap bitmapnya = decodeImg(documentSnapshot.getString("Image").getBytes());
                                binding.InputEmal.setText(documentSnapshot.getString("Nama"));
                                binding.imageProfile.setImageBitmap(bitmapnya);
                            }
                        }
                    }
                });
    }

    private Bitmap decodeImg(byte[] bytes) {
        byte[] bytea = Base64.decode(bytes, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytea, 0, bytes.length);
        return bitmap;
    }
}