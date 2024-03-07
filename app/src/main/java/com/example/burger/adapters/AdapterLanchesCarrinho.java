package com.example.burger.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.burger.Burgueria;
import com.example.burger.HolderCarrinhoLanches;
import com.example.burger.R;
import com.example.burger.interfaces.ListenerTextView;

import java.util.List;

public class AdapterLanchesCarrinho extends RecyclerView.Adapter<HolderCarrinhoLanches> {

    List<Burgueria> lancheListCarrinho;
    Context context;

    ListenerTextView listenerTextView;

    public AdapterLanchesCarrinho(List<Burgueria> lancheListCarrinho, Context context, ListenerTextView listenerTextView) {
        this.lancheListCarrinho = lancheListCarrinho;
        this.context = context;
        this.listenerTextView = listenerTextView;

    }

    @NonNull
    @Override
    public HolderCarrinhoLanches onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderCarrinhoLanches(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrinho, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCarrinhoLanches holderCarrinhoLanches, int position) {
        holderCarrinhoLanches.imageLanche.setImageResource(lancheListCarrinho.get(position).getImageLanche());
        holderCarrinhoLanches.nameLanche.setText(lancheListCarrinho.get(position).getNameLanche());
        holderCarrinhoLanches.quantidLanche.setText(String.valueOf(lancheListCarrinho.get(position).getQuantidLanche()));
        holderCarrinhoLanches.priceLanche.setText("$ " + String.valueOf(lancheListCarrinho.get(position).getTotalLanche()));

        holderCarrinhoLanches.btnRemoveLanche.setOnClickListener(new View.OnClickListener() {
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
        holderCarrinhoLanches.btnAddLanche.setOnClickListener(new View.OnClickListener() {
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
