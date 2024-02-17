package com.example.burger;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_carrinho, container, false);
        RecyclerView recyclerCarrinho = view.findViewById(R.id.recyclerCarrinho);

        ImageButton btnVoltar = view.findViewById(R.id.btnVoltar);
        ImageButton btnEndereco = view.findViewById(R.id.btnEndereco);
        TextView valorPedido = view.findViewById(R.id.valorPedido);


        Context context = getActivity();
        //VAI RETORNAR A LISTA GERAL DO CARRINHO
        Burgueria bug = new Burgueria();

        AdapterRecyclerCarrinho adapterRecyclerCarrinho = new AdapterRecyclerCarrinho(bug.getListaGeral(), context);


        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);

        recyclerCarrinho.setAdapter(adapterRecyclerCarrinho);
        recyclerCarrinho.setLayoutManager(layoutManager);

        return view;
    }
}