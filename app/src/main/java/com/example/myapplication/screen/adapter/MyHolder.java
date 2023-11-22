package com.example.myapplication.screen.adapter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class MyHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView  namaMakananView,alamatMakananView,hargaMakananView;
    Button button;
    public MyHolder(@NonNull View itemView){
        super(itemView);
        imageView=itemView.findViewById(R.id.imageView);
        namaMakananView=itemView.findViewById(R.id.namamakanan);
        alamatMakananView=itemView.findViewById(R.id.hargamakanan);
        button=itemView.findViewById(R.id.tambahmakanan);
    }


}
