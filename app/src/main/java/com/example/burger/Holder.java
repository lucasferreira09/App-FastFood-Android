package com.example.burger;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {

    public TextView nameLanche;
    public ImageView imageLanche;
    public Holder(@NonNull View itemView) {
        super(itemView);
        nameLanche = itemView.findViewById(R.id.nameLanche);
        imageLanche = itemView.findViewById(R.id.imageLanche);

    }
}
