package com.example.myapplication.screen.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {


    private List<Integer> mImageView;
    private List<String> mTextView;
    private ItemClickListener mClickListener;

    HorizontalAdapter(List<Integer> imageView, List<String> textView) {
        this.mImageView = imageView;
        this.mTextView = textView;
    }

    @NonNull
    @androidx.annotation.NonNull
    @Override
    public HorizontalAdapter.ViewHolder onCreateViewHolder(@NonNull @androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @androidx.annotation.NonNull HorizontalAdapter.ViewHolder holder, int position) {
        int image = mImageView.get(position);
        String text = mTextView.get(position);
        holder.i
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
