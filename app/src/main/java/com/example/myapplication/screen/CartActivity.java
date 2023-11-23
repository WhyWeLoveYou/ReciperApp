package com.example.myapplication.screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.cartitem;
import com.example.myapplication.databinding.ActivityCart2Binding;
import com.example.myapplication.screen.adapter.cartAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ArrayList<cartitem> cartArrayList;
    private FirebaseFirestore db;
    private cartAdapter cartRVAdapter;
    private FirebaseAuth auth;

    private ActivityCart2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCart2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        cartArrayList = new ArrayList<>();
        binding.cartRv.setHasFixedSize(true);
        binding.cartRv.setLayoutManager(new LinearLayoutManager(this));

        cartRVAdapter = new cartAdapter(cartArrayList, this);
        binding.cartRv.setAdapter(cartRVAdapter);
        String Cemail = auth.getCurrentUser().getEmail();

        binding.ImgBack.setOnClickListener(view -> {
            Intent val = new Intent(this, MainScreen.class);
            startActivity(val);
            finish();
        });

        db.collection("users").document(Cemail).collection("item").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            binding.progressB.setVisibility(View.GONE);
                            binding.cartRv.setVisibility(View.VISIBLE);
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                cartitem c = d.toObject(cartitem.class);
                                cartArrayList.add(c);
                            }

                            Button button = findViewById(R.id.im);
                            cartRVAdapter.notifyDataSetChanged();
                        } else {
                            binding.progressB.setVisibility(View.GONE);
                            binding.textErrorMsg.setText("No data");
                            binding.textErrorMsg.setVisibility(View.VISIBLE);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Fail to get the data.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    }