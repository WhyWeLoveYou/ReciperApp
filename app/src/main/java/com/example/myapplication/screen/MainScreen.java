package com.example.myapplication.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.media.RouteListingPreference;
import android.os.Bundle;
import android.widget.Adapter;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainScreenBinding;
import com.example.myapplication.screen.adapter.Myadapter1;
import com.example.myapplication.screen.fragment.HomeFragment;
import com.example.myapplication.screen.fragment.ProfileFragment;
import com.example.myapplication.screen.fragment.ReceiptFragment;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends AppCompatActivity {

    ActivityMainScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        List<Item> items = new ArrayList<Item>();
        items.add(new Item("mie ayam",12000,R.drawable.photo));


        RecyclerView recyclerView = findViewById(R.id.recyclerView_RecommendationReceipt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Myadapter1(getApplicationContext()));

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

}