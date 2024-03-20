package com.example.burger.HoldersEAdapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.burger.R;

public class HolderCarrinhoLanches extends RecyclerView.ViewHolder {
    public ImageView imageLanche;
    public TextView nameLanche;
    public TextView priceLanche;
    public ImageView btnAddLanche;
    public ImageView btnRemoveLanche;
    public TextView quantidLanche;

    public HolderCarrinhoLanches(@NonNull View itemView) {
        super(itemView);
        imageLanche = itemView.findViewById(R.id.imageLanche);
        nameLanche = itemView.findViewById(R.id.nameLanche);
        priceLanche = itemView.findViewById(R.id.priceLanche);
        btnAddLanche = itemView.findViewById(R.id.btnAddLanche);
        btnRemoveLanche = itemView.findViewById(R.id.btnRemoveLanche);
        quantidLanche = itemView.findViewById(R.id.quantidLanche);


    }
}
