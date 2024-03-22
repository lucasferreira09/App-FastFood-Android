package com.example.burger.HoldersEAdapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.burger.Burgueria;
import com.example.burger.HoldersEAdapters.HolderMenuLanches;
import com.example.burger.R;
import com.example.burger.interfaces.SelectListener;

import java.util.List;

public class AdapterMenuLanches extends RecyclerView.Adapter<HolderMenuLanches> {

    
    private Context context;
    private List<Burgueria> burgueriaList;

    SelectListener listener;

    public AdapterMenuLanches(List<Burgueria> burgueriaList, Context context, SelectListener listener) {
        this.burgueriaList = burgueriaList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public HolderMenuLanches onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HolderMenuLanches(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_burger, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMenuLanches holderMenuLanches, int position) {
        holderMenuLanches.nameLanche.setText(burgueriaList.get(position).getNameLanche());
        holderMenuLanches.imageLanche.setImageResource(burgueriaList.get(position).getImageLanche());

        holderMenuLanches.imageLanche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Método da interface é chamado, passando um Objeto Burgueria para ele
                listener.onItemClicked(burgueriaList.get(position));

            }
        });

    }

    @Override
    public int getItemCount() {
        return burgueriaList.size();
    }

}
