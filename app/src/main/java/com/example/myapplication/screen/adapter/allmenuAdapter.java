package com.example.myapplication.screen.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.itemTambahM;
import com.example.myapplication.screen.detailAct;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class allmenuAdapter extends RecyclerView.Adapter<allmenuAdapter.ViewHolder> {

    private ArrayList<itemTambahM> MitemArrayList;
    private Context context;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private String itunya;

    public allmenuAdapter(ArrayList<itemTambahM> coursesArrayList, Context context) {
        this.MitemArrayList = coursesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public allmenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_tidur, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull allmenuAdapter.ViewHolder holder, int position) {
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
            if (bitmap != null) {
                holder.imageview.setImageBitmap(bitmap);
            } else {
                holder.imageview.setImageResource(R.drawable.makanan7);
            }
    }
    }

    @Override
    public int getItemCount() {
        return MitemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView namamakanan;
        private final TextView alamatmakanan;
        private final TextView hargamakanan;
        private final ImageView imageview;
        private final RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namamakanan = itemView.findViewById(R.id.namamakanan);
            alamatmakanan = itemView.findViewById(R.id.alamatmakanan);
            hargamakanan = itemView.findViewById(R.id.hargamakanan);
            imageview = itemView.findViewById(R.id.imageview);
            relativeLayout = itemView.findViewById(R.id.apa);


            relativeLayout.setOnClickListener(v -> {
                itemTambahM Mitem = MitemArrayList.get(getAdapterPosition());
                Intent val = new Intent(context.getApplicationContext(), detailAct.class);
                val.putExtra("jeneng", Mitem.getNamaMakanan());
                context.startActivity(val);
            });
        }
    }
}
