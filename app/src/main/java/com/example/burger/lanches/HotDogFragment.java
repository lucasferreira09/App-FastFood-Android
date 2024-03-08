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

public class HotDogFragment extends Fragment implements SelectListener {

    //Será usado para substituir toda a tela por um novo Fragment(Nova tela)
    private ConstraintLayout constraintLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_hot_dog, container, false);
        constraintLayout = view.findViewById(R.id.constraintLayout);

        RecyclerView recyclerHotDog = view.findViewById(R.id.recyclerHotDog);

        List<Burgueria> burgueriaListHotDog = new ArrayList<>();

        Context context = getActivity();

        //Preparando o RecyclerView com os Lanches
        AdapterMenuLanches adapterMenuLanches = new AdapterMenuLanches(burgueriaListHotDog, context, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        recyclerHotDog.setLayoutManager(gridLayoutManager);
        recyclerHotDog.setAdapter(adapterMenuLanches);

        //Menu Cardápio
        burgueriaListHotDog.add(new Burgueria("Dogzilla", R.drawable.dogzilla, 9, getString(R.string.descricaoDogzilla)));
        burgueriaListHotDog.add(new Burgueria("Hot Mix", R.drawable.hotmix, 12, getString(R.string.descricaoHotmix)));
        burgueriaListHotDog.add(new Burgueria("Dog Especial", R.drawable.dogespecial, 10, getString(R.string.descricaoDogEspecial)));
        burgueriaListHotDog.add(new Burgueria("Tradicional", R.drawable.tradicional, 11, getString(R.string.descricaoTradicional)));
        burgueriaListHotDog.add(new Burgueria("Dog Clássico", R.drawable.dogclassico, 9, getString(R.string.descricaoDogClassico)));


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