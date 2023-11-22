package com.example.myapplication.screen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.cartitem;
import com.example.myapplication.database.itemMakanan;
import com.example.myapplication.database.itemTambahM;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class HfoodAdapter extends RecyclerView.Adapter<HfoodAdapter.ViewHolder> {

    private ArrayList<itemTambahM> MitemArrayList;
    private Context context;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    public HfoodAdapter(ArrayList<itemTambahM> coursesArrayList, Context context) {
        this.MitemArrayList = coursesArrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public HfoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view_berdiri, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HfoodAdapter.ViewHolder holder, int position) {
        itemTambahM Mitem = MitemArrayList.get(position);
        holder.namamakanan.setText(Mitem.getNamaMakanan());
        holder.alamatmakanan.setText(Mitem.getAlamat());
        holder.hargamakanan.setText(Mitem.getHarga());
        holder.tambahmakanan.setOnClickListener(v -> {
            Toast.makeText(context.getApplicationContext(), Mitem.getNamaMakanan(), Toast.LENGTH_SHORT);
            addDataToFirestore(Mitem.getNamaMakanan(), Mitem.getAlamat(), Mitem.getHarga());
        });
    }

    private void addDataToFirestore(String NamaMakanan,String Alamat,String Harga) {
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        itemTambahM ITEM = new itemTambahM(NamaMakanan, Alamat, Harga);
        String email = auth.getCurrentUser().getEmail();

        firebaseFirestore.collection("users").document(email).collection("item")
                .add(ITEM).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                Toast.makeText(context.getApplicationContext(), "Your Course has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(context.getApplicationContext(), "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return MitemArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView namamakanan;
        private final TextView alamatmakanan;
        private final TextView hargamakanan;
        private final Button tambahmakanan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            namamakanan = itemView.findViewById(R.id.namamakanan);
            alamatmakanan = itemView.findViewById(R.id.alamatmakanan);
            hargamakanan = itemView.findViewById(R.id.hargamakanan);
            tambahmakanan = itemView.findViewById(R.id.tambahmakanan);
        }
    }
}
