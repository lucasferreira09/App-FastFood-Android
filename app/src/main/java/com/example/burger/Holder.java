package com.example.burger;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Holder extends RecyclerView.ViewHolder {

    public TextView nameLanche;
    public TextView quantidLanche;
    public ImageView imageLanche;

    public Holder(@NonNull View itemView) {
        super(itemView);
        nameLanche = itemView.findViewById(R.id.nameLanche);
        imageLanche = itemView.findViewById(R.id.imageLanche);
        quantidLanche = itemView.findViewById(R.id.quantidLanche);

    }
}
