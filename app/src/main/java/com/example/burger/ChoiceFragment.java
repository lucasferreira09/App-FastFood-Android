package com.example.burger;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class ChoiceFragment extends Fragment implements AdicionaCarrinho{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choice, container, false);
        CardView cardAdd = view.findViewById(R.id.carAdd);
        TextView choiceLanche = view.findViewById(R.id.choiceLanche);
        LinearLayout linear = view.findViewById(R.id.linear);
        TextView descricaoLanche = view.findViewById(R.id.descricaoLanche);
        TextView priceLanche = view.findViewById(R.id.priceLanche);

        Bundle bundle = getArguments();

        String nameLanche = bundle.getString("nameLanche");
        int idImage = bundle.getInt("idImage");
        int price = bundle.getInt("priceLanche");
        String descricao = bundle.getString("descricaoLanche");

        descricaoLanche.setText(descricao);
        priceLanche.setText("$ " + String.valueOf(price));

        choiceLanche.setText(nameLanche);



        //VIEW QUE VOLTA PARA A TELA INICIAL
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        Burgueria burgueria = new Burgueria(nameLanche, idImage, price, descricao);

        cardAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Burgueria> listaGeral = burgueria.getListaGeral();
                if (!listaGeral.isEmpty()) {
                    boolean lancheAchado = false;
                    for (Burgueria b : listaGeral) {
                        if (b.getNameLanche().equals(nameLanche)) {
                            b.addQuantidLanche();
                            lancheAchado = true;
                            break;
                        }
                    }
                    if (lancheAchado == false){
                        burgueria.addQuantidLanche();
                        adicionaAoCarrinho(burgueria);
                    }

                }else if (listaGeral.isEmpty()){
                    burgueria.addQuantidLanche();
                    adicionaAoCarrinho(burgueria);
                }
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