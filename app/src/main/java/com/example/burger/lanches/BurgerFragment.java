package com.example.burger.lanches;

import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.burger.Burgueria;
import com.example.burger.LancheEscolhido;
import com.example.burger.R;
import com.example.burger.adapters.AdapterMenuLanches;
import com.example.burger.interfaces.SelectListener;

import java.util.ArrayList;
import java.util.List;

public class BurgerFragment extends Fragment implements SelectListener {

    private ConstraintLayout constraintLayout;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_burger, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        constraintLayout = view.findViewById(R.id.constraintLayout);

        //Armazena todos os Lanches
        List<Burgueria> burgueriaList = new ArrayList<>();

        Context context = getActivity();


        //Preparando o RecyclerView com os Lanches
        AdapterMenuLanches adapter = new AdapterMenuLanches(burgueriaList, context, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);

        //Menu Cardápio
        burgueriaList.add(new Burgueria("X Bacon", R.drawable.xbacon, 11, getString(R.string.descricaoXbacon)));
        burgueriaList.add(new Burgueria("Saladinha", R.drawable.saladinha, 9, getString(R.string.descricaoSaladinha)));
        burgueriaList.add(new Burgueria("Big Mac", R.drawable.bigmac, 15, getString(R.string.descricaoBigMac)));
        burgueriaList.add(new Burgueria("X Duplo", R.drawable.duplo, 15, getString(R.string.descricaoXduplo)));
        burgueriaList.add(new Burgueria("Mini Burger", R.drawable.mini, 6, getString(R.string.descricaoMiniBurger)));
        burgueriaList.add(new Burgueria("Americano", R.drawable.americano, 9, getString(R.string.descricaoAmericano)));
        burgueriaList.add(new Burgueria("Faminto", R.drawable.faminto, 16, getString(R.string.descricaoFaminto)));
        burgueriaList.add(new Burgueria("Nordestino", R.drawable.nordestino, 7, getString(R.string.descricaoNordestino)));


        return view;
    }

    //Método da Interface para quando um Lanche for Clicado no RecyclerView
    //O RecyclerView passa um objeto Burgueria para o método
    @Override
    public void onItemClicked(Burgueria burgueria) {

        LancheEscolhido lancheEscolhido = new LancheEscolhido();

        //Manda as informações sobre o Lanche Escolhido para a próxima Tela
        Bundle bundle = new Bundle();
        bundle.putString("nameLanche", burgueria.getNameLanche());
        bundle.putInt("idImage", burgueria.getImageLanche());
        bundle.putInt("priceLanche", burgueria.getPriceLanche());
        bundle.putString("descricaoLanche", burgueria.getDescricaoLanche());
        lancheEscolhido.setArguments(bundle);


        //Criar uma transação entre Fragments(mudança entre...)
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.constraintLayout, lancheEscolhido);
        ft.addToBackStack(null);
        ft.commit();


    }

}
