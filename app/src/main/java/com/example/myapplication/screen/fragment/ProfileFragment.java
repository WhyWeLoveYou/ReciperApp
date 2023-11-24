package com.example.myapplication.screen.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ProfileBinding;
import com.example.myapplication.helper.CumanNambahData;
import com.example.myapplication.screen.About;
import com.example.myapplication.screen.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProfileFragment extends Fragment {

    private ProfileBinding binding;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;
    private static final String TAG = "MyActivity";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ProfileBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        listener();
        getUserDetail();
    }



    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void listener() {
        binding.Bsimaaao.setOnClickListener(v -> {
            auth = FirebaseAuth.getInstance();
            auth.signOut();
            showToast("Berhasil Log Out");
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
        });
        binding.Bsimaaaobt.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), CumanNambahData.class);
            startActivity(intent);
        });
        binding.ImgBack.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), About.class);
            startActivity(intent);
        });
        binding.Bsimaaaort.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), CumanNambahResep.class);
            startActivity(intent);
        });
    }

    private void getUserDetail() {
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            String Cuser = auth.getCurrentUser().getEmail();
            firebaseFirestore.collection("users").document(Cuser).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot documentSnapshot = task.getResult();
                                if (documentSnapshot != null && documentSnapshot.exists()) {
                                    String bytea = documentSnapshot.getString("Image");
                                    byte[] bytes = Base64.decode(bytea, Base64.DEFAULT);
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                    binding.InputEmal.setText(documentSnapshot.getString("Email"));
                                    binding.Inputneme.setText(documentSnapshot.getString("Nama"));
                                    binding.imageProfile.setImageBitmap(bitmap);
                                }
                            }
                        }
                    })
                    .addOnFailureListener(task -> {
                        FirebaseFirestoreException e = (FirebaseFirestoreException) task.getCause();
                        Toast.makeText(requireContext(), "Errorn: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Errorn: " + e.getMessage());
                    });
        } else {
            showToast("ntah");
        }
    }
}
