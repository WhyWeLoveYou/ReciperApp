package com.example.myapplication.screen.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.ImageView;
import android.widget.Toast;
=======
>>>>>>> origin/master

import com.example.myapplication.R;


public class ReceiptFragment extends Fragment {

<<<<<<< HEAD
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
=======
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_receipt, container, false);
    }
}
>>>>>>> origin/master
