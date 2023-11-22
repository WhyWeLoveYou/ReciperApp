package com.example.myapplication.screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ProfileBinding;
import com.example.myapplication.screen.fragment.HomeFragment;
import com.example.myapplication.screen.fragment.ProfileFragment;
import com.example.myapplication.screen.fragment.ReceiptFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.checkerframework.checker.units.qual.C;

public class Profile extends AppCompatActivity {

    private ProfileBinding binding;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;
    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        listener();
        getUserDetail();

        binding.bottomAppBar.setOnMenuItemClickListener( item -> {

            switch (item.getItemId()){

                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.receipt:
                    replaceFragment(new ReceiptFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
                default:
                    break;

            }

            return true;
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
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
            auth = FirebaseAuth.getInstance();
            auth.signOut();
            showToast("Berhasil Log Out");
            Intent intent = new Intent(this, LoginActivity.class);
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
                        FirebaseFirestoreException e = (FirebaseFirestoreException ) task.getCause();
                        Toast.makeText(this, "Errorn: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Errorn: "+e.getMessage());
                    });
        } else {
            showToast("ntah");
        }
    }



}