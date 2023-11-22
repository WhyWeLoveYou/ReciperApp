package com.example.myapplication.screen.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.screen.Item;

import java.util.List;

public class Myadapter1 extends RecyclerView.Adapter<MyHolder>{


    Context context;
    List<Item> items;

    Button button;

    public Myadapter1(Context context) {
        this.context = context;
        this.items = items;
        this.button = button;
    }



    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.item_view_tidur,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.namaMakananView.setText(items.get(position).getNamaMakanan());
        holder.alamatMakananView.setText(items.get(position).getAlamatMakanan());
        holder.hargaMakananView.setText(items.get(position).getHargaMakanan());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.get(position).getTambahMakanan();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
