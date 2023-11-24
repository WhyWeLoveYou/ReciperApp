package com.example.myapplication.screen.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.itemData;
import com.example.myapplication.database.itemTambahM;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class receiptFragment extends RecyclerView.Adapter<receiptFragment.ViewHolder> {

    private ArrayList<itemData> Arraylistnya;
    private Context context;
    private String itunya;

    public receiptFragment(ArrayList<itemData> coursesArrayList, Context context) {
        this.Arraylistnya = coursesArrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public receiptFragment.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_receipt_horizontal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull receiptFragment.ViewHolder holder, int position) {
        itemData itemDatanya = Arraylistnya.get(position);
        holder.alamat.setText(itemDatanya.getAlamat());
        holder.nama.setText(itemDatanya.getNamaMakanan());
        if (itemDatanya.getGambar() == null) {
            holder.gambar.setImageResource(R.drawable.makanan7);
        } else {
            String bytea = itemDatanya.getGambar();
            byte[] bytes = Base64.decode(bytea, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            if (bitmap != null) {
                holder.gambar.setImageBitmap(bitmap);
            } else {
                holder.gambar.setImageResource(R.drawable.makanan7);
            }
        }
    }

    @Override
    public int getItemCount() {
        return Arraylistnya.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView gambar;
        private TextView alamat;
        private TextView nama;
        private Button baca;
        private FirebaseAuth auth;
        private FirebaseFirestore firebaseFirestore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gambar = itemView.findViewById(R.id.imageview);
            alamat = itemView.findViewById(R.id.alamatmakanan);
            nama = itemView.findViewById(R.id.judulReceipt);
            baca = itemView.findViewById(R.id.tambahmakanan);

            baca.setOnClickListener(v -> {
                auth = FirebaseAuth.getInstance();
                firebaseFirestore = FirebaseFirestore.getInstance();

                firebaseFirestore.collection("item_recipe").document(nama.getText().toString()).get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if (documentSnapshot != null && documentSnapshot.exists()) {
                            String url = documentSnapshot.getString("url");
                            if (!url.startsWith("https://") && !url.startsWith("http://")){
                                url = "http://" + url;
                            }
                            Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            context.startActivity(openUrlIntent);
                        }
                    }
                });
            });
        }
    }
}
