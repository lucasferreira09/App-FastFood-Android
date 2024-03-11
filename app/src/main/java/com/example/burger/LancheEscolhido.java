package com.example.burger;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.burger.interfaces.AdicionaCarrinho;

import java.util.List;


public class LancheEscolhido extends Fragment implements AdicionaCarrinho {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_escolhido_lanche, container, false);

        View viewSair = view.findViewById(R.id.viewSair);
        CardView adicionaLanche = view.findViewById(R.id.adicionaLanche);
        TextView lancheEscolhido = view.findViewById(R.id.lancheEscolhido);
        TextView descricaoLanche = view.findViewById(R.id.descricaoLanche);
        TextView priceLanche = view.findViewById(R.id.priceLanche);


        //Recupera as informações sobre o Lanche Escolhido
        Bundle bundle = getArguments();
        String nameLanche = bundle.getString("nameLanche");
        int idImage = bundle.getInt("idImage");
        int price = bundle.getInt("priceLanche");
        String descricao = bundle.getString("descricaoLanche");

        //Define as informações do Lanche
        descricaoLanche.setText(descricao);
        priceLanche.setText("$ " + String.valueOf(price));
        lancheEscolhido.setText(nameLanche);


        Burgueria burgueria = new Burgueria(nameLanche, idImage, price, descricao);

        //Listener Botão de adicionar Lanche ao Carrinho
        adicionaLanche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Burgueria> listaGeral = burgueria.getListaGeral();

                //Se a lista com todos os lanches escolhidos não estiver vazia
                if (!listaGeral.isEmpty()) {
                    boolean lancheAchado = false;

                    for (Burgueria b : listaGeral) {  //É preciso verificar se esse lanche já foi adicionado
                        if (b.getNameLanche().equals(nameLanche)) {
                            //Se foi, incrementamos a quantidade
                            b.addQuantidLanche();
                            lancheAchado = true;
                            break;
                        }
                    }
                    //Se o lanche não foi adicionado
                    if (lancheAchado == false){
                        burgueria.addQuantidLanche();  //incrementamos a quantidade
                        adicionaAoCarrinho(burgueria); //Adicionamos o lanche na lista de lanches do carrinho
                    }

                //Se a lista com todos os lanches escolhidos estiver vazia
                }else if (listaGeral.isEmpty()){

                    burgueria.addQuantidLanche();  //incrementamos a quantidade
                    adicionaAoCarrinho(burgueria); //Adicionamos o lanche na lista de lanches do carrinho
                }
                getActivity().getSupportFragmentManager().popBackStack(); //Depois de adicionado, o Fragment é fechado
            }
        });

        //Fecha o Fragment (A tela com informações do Lanche)
        //Quando o lanche for adicionado
        viewSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        return view;
    }

    @Override
    public void adicionaAoCarrinho(Burgueria burguerLanche) {
        Burgueria b = new Burgueria();
        b.updateListaGeral(burguerLanche);
    }

}