package com.example.myapplication.screen.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.cartitem;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.ViewHolder> {

    private ArrayList<cartitem> cartitemArrayList;
    private Context context;

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
            holder.imageView.setImageResource(R.drawable.photo);
        } else {
            String bytea = cartItem.getGambar();
            byte[] bytes = Base64.decode(bytea, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            holder.imageView.setImageBitmap(bitmap);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMakanan = itemView.findViewById(R.id.textViewMakanan);
            textViewDesk = itemView.findViewById(R.id.textViewDesk);
            textViewHarga = itemView.findViewById(R.id.textViewHarga);
            imageView = itemView.findViewById(R.id.imageView);
        }
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
}
