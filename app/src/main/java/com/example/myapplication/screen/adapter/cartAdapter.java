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

import java.util.ArrayList;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.ViewHolder> {

    private ArrayList<cartitem> cartitemArrayList;
    private Context context;
    Integer[] image = { R.drawable.makanan1, R.drawable.makanan2,R.drawable.makanan3,
            R.drawable.makanan4, R.drawable.makanan5, R.drawable.makanan6, R.drawable.makanan7,
            R.drawable.makanan8, R.drawable.makanan9, R.drawable.makanan10};

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
            int i;
            for(i=0; i<10;i++) {
                holder.imageView.setImageResource(image[i]);
            }
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
}
