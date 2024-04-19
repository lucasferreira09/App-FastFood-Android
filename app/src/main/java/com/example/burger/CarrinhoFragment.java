package com.example.burger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.burger.HoldersEAdapters.AdapterLanchesCarrinho;
import com.example.burger.databinding.FragmentCarrinhoBinding;
import com.example.burger.interfaces.ListenerTextView;

import java.util.List;

public class CarrinhoFragment extends Fragment implements ListenerTextView {

    private FragmentCarrinhoBinding binding;

    //DADOS DO USUÁRIO - NOME E ENDEREÇO
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_DADOS = "myDados";
    private static final String KEY_NOME = "nome";
    private static final String KEY_RUA = "rua";
    private static final String KEY_NUMERO = "numero";
    private static final String KEY_BAIRRO = "bairro";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCarrinhoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_DADOS, Context.MODE_PRIVATE);


        //Será usada para pegar toda a lista de Lanches adicionados
        Burgueria burgueria = new Burgueria();

        String pedidoTotal = calculaPedidoTotal(burgueria.getListaGeral());
        binding.valorPedidoTotal.setText(pedidoTotal);

        //Prepara a lista que terá todos os pedidos adicionados
        Context context = getActivity();
        AdapterLanchesCarrinho adapterLanchesCarrinho = new AdapterLanchesCarrinho(burgueria.getListaGeral(), context, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        binding.recyclerCarrinho.setAdapter(adapterLanchesCarrinho);
        binding.recyclerCarrinho.setLayoutManager(layoutManager);


        //Botão de fazer o pedido. Será redirecionado para WhatsApp
        binding.btnPedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enviarPedido = new Intent();
                enviarPedido.setAction(Intent.ACTION_SEND);
                enviarPedido.putExtra(Intent.EXTRA_TEXT, descricaoPedidoTotal(burgueria.getListaGeral()));
                enviarPedido.setType("text/plain");

                Intent shareIntent = Intent.createChooser(enviarPedido, null);
                startActivity(shareIntent);
            }
        });

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        //Botão de ir para o Endereço
        binding.btnVerEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerEndereco verEndereco = new VerEndereco();

                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.telaCarrinho, verEndereco);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    //Método da interface para mudar o $ Valor total do pedido
    @Override
    public void clickTextView(List<Burgueria> listaBurgueria) {
        String pedidoTotal = calculaPedidoTotal(listaBurgueria);
        binding.valorPedidoTotal.setText("$ " + pedidoTotal);
    }

    //Calcula o $ Valor total do pedido
    public String calculaPedidoTotal(List<Burgueria> lista){
        int novoValorTotal = 0;

        for (Burgueria b : lista){
            int valor = b.getQuantidLanche() * b.getPriceLanche();
            novoValorTotal += valor;
        }
        return String.valueOf(novoValorTotal);
    }


    //Faz uma descrição do pedido para enviar por WhatsApp
    public String descricaoPedidoTotal(List<Burgueria> lista){
        StringBuilder descricaoPedido = new StringBuilder();
        descricaoPedido.append("Olá, tudo bem? \uD83D\uDE0BVou querer:\n");
        descricaoPedido.append("\n");

        //Obtêm quais lanches foram adicionados ao carrinho
        for (Burgueria burgueria : lista){
            if (burgueria.getQuantidLanche() != 0){
                String nomeLanche = burgueria.getNameLanche();
                String quantidade = String.valueOf(burgueria.getQuantidLanche());
                descricaoPedido.append(quantidade + "* " + nomeLanche + "\n");
            }
        }
        descricaoPedido.append("\n");
        descricaoPedido.append(enderecoUsuario());

        return descricaoPedido.toString();
    }

    //Pega os dados do usuário para fazer o pedido
    public String enderecoUsuario() {

        StringBuilder dados = new StringBuilder();

        dados.append("\uD83C\uDFDA Endereço\n");
        dados.append(sharedPreferences.getString(KEY_NOME, "---") +"\n");
        dados.append("Rua " + sharedPreferences.getString(KEY_RUA, "---") +"\n");
        dados.append("N° " + sharedPreferences.getString(KEY_NUMERO, "---") +"\n");
        dados.append("Bairro  " +  sharedPreferences.getString(KEY_BAIRRO, "---"));

        return dados.toString();
    }
}