package com.example.burger.HoldersEAdapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.burger.R;

//Holder para o Menu do Lanches
public class HolderMenuLanches extends RecyclerView.ViewHolder {

    public TextView nameLanche;
    public TextView quantidLanche;
    public ImageView imageLanche;

    public HolderMenuLanches(@NonNull View itemView) {
        super(itemView);
        nameLanche = itemView.findViewById(R.id.nameLanche);
        imageLanche = itemView.findViewById(R.id.imageLanche);
        quantidLanche = itemView.findViewById(R.id.quantidLanche);

    }
}
