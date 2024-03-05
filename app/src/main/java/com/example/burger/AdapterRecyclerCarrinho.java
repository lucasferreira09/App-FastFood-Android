package com.example.burger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterRecyclerCarrinho extends RecyclerView.Adapter<HolderCarrinho> {

    List<Burgueria> lancheListCarrinho;
    Context context;

    ListenerTextView listenerTextView;

    public AdapterRecyclerCarrinho(List<Burgueria> lancheListCarrinho, Context context, ListenerTextView listenerTextView) {
        this.lancheListCarrinho = lancheListCarrinho;
        this.context = context;
        this.listenerTextView = listenerTextView;

    }

    @NonNull
    @Override
    public HolderCarrinho onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderCarrinho(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrinho, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCarrinho holderCarrinho, int position) {
        holderCarrinho.imageLanche.setImageResource(lancheListCarrinho.get(position).getImageLanche());
        holderCarrinho.nameLanche.setText(lancheListCarrinho.get(position).getNameLanche());
        holderCarrinho.quantidLanche.setText(String.valueOf(lancheListCarrinho.get(position).getQuantidLanche()));
        holderCarrinho.priceLanche.setText("$ " + String.valueOf(lancheListCarrinho.get(position).getTotalLanche()));

        holderCarrinho.btnRemoveLanche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lancheListCarrinho.get(position).getQuantidLanche() > 1) {
                    lancheListCarrinho.get(position).removeQuantidLanche();
                    lancheListCarrinho.get(position).setTotalLanche();//atualiza valor do lanche
                    notifyItemChanged(position);

                    listenerTextView.clickTextView(lancheListCarrinho);//avisa o carrinho para atualizar ValorTotal do Pedido

                } else {
                    lancheListCarrinho.remove(position);
                    notifyItemRemoved(position);

                    listenerTextView.clickTextView(lancheListCarrinho);
                }
            }
        });
        holderCarrinho.btnAddLanche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lancheListCarrinho.get(position).addQuantidLanche();
                notifyItemChanged(position);

                listenerTextView.clickTextView(lancheListCarrinho);//avisa o carrinho para atualizar ValorTotal do Pedido


            }
        });

    }

    @Override
    public int getItemCount() {
        return lancheListCarrinho.size();
    }
}
