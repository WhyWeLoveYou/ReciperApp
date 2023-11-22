package com.example.myapplication.screen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.cartitem;

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
    }

    @Override
    public int getItemCount() {
        return cartitemArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewMakanan;
        private final TextView textViewDesk;
        private final TextView textViewHarga;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            textViewMakanan = itemView.findViewById(R.id.textViewMakanan);
            textViewDesk = itemView.findViewById(R.id.textViewDesk);
            textViewHarga = itemView.findViewById(R.id.textViewHarga);
        }
    }
}
