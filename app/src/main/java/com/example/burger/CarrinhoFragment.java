package com.example.burger;

import android.content.Context;
import android.content.Intent;
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

public class CarrinhoFragment extends Fragment implements ListenerTextView{

    private AdapterRecyclerCarrinho adapterRecyclerCarrinho;
    private TextView valorPedidoTotal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_carrinho, container, false);
        RecyclerView recyclerCarrinho = view.findViewById(R.id.recyclerCarrinho);

        valorPedidoTotal = view.findViewById(R.id.valorPedidoTotal);
        ImageButton btnPedir = view.findViewById(R.id.btnPedir);

        Context context = getActivity();

        //VAI RETORNAR A LISTA GERAL DO CARRINHO
        Burgueria bug = new Burgueria();


        String pedidoTotal = calculaPedidoTotal(bug.getListaGeral());
        valorPedidoTotal.setText(pedidoTotal);

        AdapterRecyclerCarrinho adapterRecyclerCarrinho = new AdapterRecyclerCarrinho(bug.getListaGeral(), context, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);

        recyclerCarrinho.setAdapter(adapterRecyclerCarrinho);
        recyclerCarrinho.setLayoutManager(layoutManager);

        /*
        btnPedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enviarPedido = new Intent();
                enviarPedido.setAction(Intent.ACTION_SEND);
                enviarPedido.putExtra(Intent.EXTRA_TEXT, descricaoPedidoTotal(bug.getListaGeral()));
                enviarPedido.setType("text/plain");

                Intent shareIntent = Intent.createChooser(enviarPedido, null);
                startActivity(shareIntent);
            }
        });

         */

        return view;
    }

    @Override
    public void clickTextView(List<Burgueria> listaBurgueria) {
        String pedidoTotal = calculaPedidoTotal(listaBurgueria);
        valorPedidoTotal.setText("$ " + pedidoTotal);
    }
    public String calculaPedidoTotal(List<Burgueria> lista){
        int novoValorTotal = 0;

        for (Burgueria b : lista){
            int valor = b.getQuantidLanche() * b.getPriceLanche();
            novoValorTotal += valor;
        }
        return String.valueOf(novoValorTotal);
    }

    public String descricaoPedidoTotal(List<Burgueria> lista){
        StringBuilder descricaoPedido = new StringBuilder();
        descricaoPedido.append("Oii, tudo bem?\n\uD83D\uDE0BVou querer:\n");
        descricaoPedido.append("\n");

        for (Burgueria burgueria : lista){
            if (burgueria.getQuantidLanche() != 0){
                String nomeLanche = burgueria.getNameLanche();
                String quantidade = String.valueOf(burgueria.getQuantidLanche());
                descricaoPedido.append(quantidade + "* " + nomeLanche + "\n");
            }
        }

        descricaoPedido.append("\n");
        descricaoPedido.append("Endereço:\n");
        descricaoPedido.append("Rua Cel. Luiz de Melo N°119");
        descricaoPedido.append("\n");
        descricaoPedido.append("\uD83D\uDE4FObrigado!");

        return descricaoPedido.toString();
    }
}