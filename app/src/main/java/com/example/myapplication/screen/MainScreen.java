package com.example.myapplication.screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainScreenBinding;
import com.example.myapplication.screen.fragment.HomeFragment;
import com.example.myapplication.screen.fragment.ProfileFragment;
import com.example.myapplication.screen.fragment.ReceiptFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainScreen extends AppCompatActivity {

  BottomNavigationView bottomNavigationView;

  HomeFragment homeFragment = new HomeFragment();
  ReceiptFragment receiptFragment = new ReceiptFragment();
  ProfileFragment profileFragment = new ProfileFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        bottomNavigationView = findViewById(R.id.bottomAppBar);

        getSupportFragmentManager().beginTransaction().replace(androidx.core.R.id.action_container,homeFragment).commit();
        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(androidx.core.R.id.action_container,homeFragment).commit();
                        return;
                    case R.id.receipt:
                        getSupportFragmentManager().beginTransaction().replace(androidx.core.R.id.action_container,receiptFragment).commit();
                        return;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(androidx.core.R.id.action_container,profileFragment).commit();
                        return;



                }
            }
        });








    }



}