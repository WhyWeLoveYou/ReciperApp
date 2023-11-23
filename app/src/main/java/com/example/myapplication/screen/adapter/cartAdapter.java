package com.example.myapplication.screen.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.cartitem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.ViewHolder> {




    private ArrayList<cartitem> cartitemArrayList;
    private Context context;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;

    public cartAdapter(ArrayList<cartitem> coursesArrayList, Context context) {
        this.cartitemArrayList = coursesArrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public cartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull cartAdapter.ViewHolder holder, int position) {
        cartitem cartItem = cartitemArrayList.get(position);
        holder.textViewMakanan.setText(cartItem.getNamaMakanan());
        holder.textViewDesk.setText(cartItem.getAlamat());
        holder.textViewHarga.setText(cartItem.getHarga());
        if (cartItem.getGambar() == null) {
            holder.imageView.setImageResource(R.drawable.makanan7);
        } else {
            String bytea = cartItem.getGambar();
            byte[] bytes = Base64.decode(bytea, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            holder.imageView.setImageBitmap(bitmap);
        }

        holder.imageButton.setOnClickListener(v -> {
            deleteDataFirebase(cartItem.getDocumentId());
        });
    }

    private void deleteDataFirebase(String documentId) {
        if (auth.getCurrentUser() == null) {
            String userEmail = auth.getCurrentUser().getEmail();

            firebaseFirestore.collection("users").document(userEmail).collection("item")
                    .document(documentId).delete()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(context, "Berhasil Menghapus Cart", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(context, "Tidak Berhasil Menghapus Cart", Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(context, "Tidak Berhasil Menghapus Cart", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return cartitemArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewMakanan;
        private final TextView textViewDesk;
        private final TextView textViewHarga;
        private final ImageView imageView;
        private final ImageButton imageButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMakanan = itemView.findViewById(R.id.textViewMakanan);
            textViewDesk = itemView.findViewById(R.id.textViewDesk);
            textViewHarga = itemView.findViewById(R.id.textViewHarga);
            imageView = itemView.findViewById(R.id.imageView);
            imageButton = itemView.findViewById(R.id.imageViewDelete);
            auth = FirebaseAuth.getInstance();
            firebaseFirestore = FirebaseFirestore.getInstance();
        }
    }
}
