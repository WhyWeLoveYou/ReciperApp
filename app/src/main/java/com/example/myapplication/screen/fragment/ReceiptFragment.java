package com.example.myapplication.screen.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.cartitem;
import com.example.myapplication.database.itemData;
import com.example.myapplication.databinding.FragmentReceiptBinding;
import com.example.myapplication.screen.adapter.receiptFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ReceiptFragment extends Fragment {

    private FragmentReceiptBinding binding;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private receiptFragment receiptFragmentRv;

    private ArrayList<itemData> itemDataArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReceiptBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getItem();
    }

    private void getItem() {
        itemDataArrayList = new ArrayList<itemData>();
        binding.RecommendationReceiptRV.setLayoutManager(new LinearLayoutManager(getContext()));

        receiptFragmentRv = new receiptFragment(itemDataArrayList, getContext());
        binding.RecommendationReceiptRV.setAdapter(receiptFragmentRv);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("item_recipe").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                itemData c = d.toObject(itemData.class);
                                if (c != null) {
                                    c.setDocumentId(d.getId());
                                    itemDataArrayList.add(c);
                                }
                            }
                            receiptFragmentRv.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
