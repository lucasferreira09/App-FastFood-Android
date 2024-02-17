package com.example.burger;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<Holder> {

    
    private Context context;
    private List<Burgueria> burgueriaList;

    SelectListener listener;

    public AdapterRecycler(List<Burgueria> burgueriaList, Context context, SelectListener listener) {
        this.burgueriaList = burgueriaList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_burger, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.nameLanche.setText(burgueriaList.get(position).getNameLanche());
        holder.imageLanche.setImageResource(burgueriaList.get(position).getImageLanche());

        holder.imageLanche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(burgueriaList.get(position));

            }
        });

    }

    @Override
    public int getItemCount() {
        return burgueriaList.size();
    }

}
