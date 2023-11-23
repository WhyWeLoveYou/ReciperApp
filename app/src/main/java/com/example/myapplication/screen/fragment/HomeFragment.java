package com.example.myapplication.screen.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.cartitem;
import com.example.myapplication.database.itemTambahM;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.databinding.ProfileBinding;
import com.example.myapplication.screen.adapter.HfoodAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private ArrayList<itemTambahM> makananArrayList;
    private FragmentHomeBinding binding;
    private HfoodAdapter itemRvAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getItem();
    }

    public void getItem() {
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        makananArrayList = new ArrayList<itemTambahM>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerViewRecommendationReceipt.setHasFixedSize(true);
        binding.recyclerViewRecommendationReceipt.setLayoutManager(layoutManager);
        itemRvAdapter = new HfoodAdapter(makananArrayList, getContext());
        binding.recyclerViewRecommendationReceipt.setAdapter(itemRvAdapter);

        firebaseFirestore.collection("item_penjualan").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            binding.progressB.setVisibility(View.GONE);
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                itemTambahM c = d.toObject(itemTambahM.class);
                                makananArrayList.add(c);
                            }
                            itemRvAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext().getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext().getApplicationContext(), "Fail to get the data.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}