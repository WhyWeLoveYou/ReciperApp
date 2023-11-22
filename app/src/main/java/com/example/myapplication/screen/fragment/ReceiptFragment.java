package com.example.myapplication.screen.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;


public class ReceiptFragment extends Fragment {
    ImageView imageView;

    @Override
    public void  onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receipt,container,false);

        imageView = (ImageView) imageView.findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Toast.makeText(getActivity(),"Tunggu Sebentar",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
