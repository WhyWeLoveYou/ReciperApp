package com.example.myapplication.helper;

import android.content.Context;
import android.widget.Toast;

public class helpp {
    private Context context;
    public helpp(Context context){
        this.context = context;
    }
    public void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
