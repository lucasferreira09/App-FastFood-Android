package com.example.burger;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<Holder> {

    Context context;
    private List<Burgueria> burgueriaList;

    public AdapterRecycler(List<Burgueria> burgueriaList) {
        this.burgueriaList = burgueriaList;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_burger, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.nameLanche.setText(burgueriaList.get(position).getNameLanche());
        holder.imageLanche.setImageResource(burgueriaList.get(position).getImageLanche());
    }

    @Override
    public int getItemCount() {
        return burgueriaList.size();
    }

}
