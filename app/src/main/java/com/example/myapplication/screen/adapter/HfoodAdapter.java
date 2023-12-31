package com.example.myapplication.screen.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.itemTambahM;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class HfoodAdapter extends RecyclerView.Adapter<HfoodAdapter.ViewHolder> {

    private ArrayList<itemTambahM> MitemArrayList;
    private Context context;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private String itunya;

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
        if (Mitem.getGambar() == null) {
            holder.imageview.setImageResource(R.drawable.makanan7);
        } else {
            String bytea = Mitem.getGambar();
            byte[] bytes = Base64.decode(bytea, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            holder.imageview.setImageBitmap(bitmap);
            itunya =  Mitem.getGambar();
        }

        holder.tambahmakanan.setOnClickListener(v -> {
            Toast.makeText(context.getApplicationContext(), Mitem.getNamaMakanan(), Toast.LENGTH_SHORT);
            addDataToFirestore(Mitem.getNamaMakanan(), Mitem.getAlamat(), Mitem.getHarga(), itunya);
        });
    }

    private String encodeImage(Bitmap bitmap) {
        int previewW = 150;
        int previewH = bitmap.getHeight() * previewW / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewW, previewH, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private void addDataToFirestore(String NamaMakanan,String Alamat,String Harga, String Gambar) {
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        UUID documentd = UUID.randomUUID();
        String documentId = String.valueOf(documentd);
        itemTambahM ITEM = new itemTambahM(NamaMakanan, Alamat, Harga, Gambar, documentId, null);
        String email = auth.getCurrentUser().getEmail();

        firebaseFirestore.collection("users").document(email).collection("item").document(documentId)
                .set(ITEM).addOnSuccessListener(task -> {
            Toast.makeText(context.getApplicationContext(), "Berhasil Menambahkan ke Cart", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context.getApplicationContext(), "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void decodeImg(String ya) {
        byte[] bytes = Base64.decode(ya, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
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
        private final ImageView imageview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namamakanan = itemView.findViewById(R.id.namamakanan);
            alamatmakanan = itemView.findViewById(R.id.alamatmakanan);
            hargamakanan = itemView.findViewById(R.id.hargamakanan);
            imageview = itemView.findViewById(R.id.imageview);
            tambahmakanan = itemView.findViewById(R.id.tambahmakanan);
        }
    }
}
