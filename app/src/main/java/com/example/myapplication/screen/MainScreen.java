package com.example.myapplication.screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainScreenBinding;
import com.example.myapplication.databinding.ProfileBinding;
import com.example.myapplication.screen.fragment.HomeFragment;
import com.example.myapplication.screen.fragment.ProfileFragment;
import com.example.myapplication.screen.fragment.ReceiptFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainScreen extends AppCompatActivity {

    private ActivityMainScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.BnewChat.setOnClickListener(view -> {
            Intent val = new Intent(this, CartActivity.class);
            startActivity(val);
            finish();
        });

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        binding.BnewChat.setVisibility(View.VISIBLE);
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.receipt:
                        binding.BnewChat.setVisibility(View.GONE);
                        replaceFragment(new ReceiptFragment());
                        break;
                    case R.id.profile:
                        replaceFragment(new ProfileFragment());
                        binding.BnewChat.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }


}
