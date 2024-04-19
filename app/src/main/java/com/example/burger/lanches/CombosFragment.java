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
import com.example.burger.HoldersEAdapters.AdapterMenuLanches;
import com.example.burger.databinding.FragmentBurgerBinding;
import com.example.burger.databinding.FragmentCombosBinding;
import com.example.burger.interfaces.SelectListener;

import java.util.ArrayList;
import java.util.List;


public class CombosFragment extends Fragment implements SelectListener {

    private FragmentCombosBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCombosBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Context context = getActivity();

        List<Burgueria> burgueriaList = new ArrayList<>();

        //Preparando o RecyclerView com os Lanches
        AdapterMenuLanches adapter = new AdapterMenuLanches(burgueriaList, context, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        binding.recyclerCombos.setAdapter(adapter);
        binding.recyclerCombos.setLayoutManager(gridLayoutManager);

        //Menu Cardápio
        burgueriaList.add(new Burgueria("Classico", R.drawable.classico, 16, getString(R.string.descricaoClassico)));
        burgueriaList.add(new Burgueria("Combo Duplo", R.drawable.comboduplo, 14, getString(R.string.descricaoComboDuplo)));
        burgueriaList.add(new Burgueria("Combo Jr.", R.drawable.combojr, 17, getString(R.string.descricaoComboJr)));
        burgueriaList.add(new Burgueria("Big Tasty", R.drawable.bigtasty, 23, getString(R.string.descricaoBigTasty)));
        burgueriaList.add(new Burgueria("Combo", R.drawable.combo, 18, getString(R.string.descricaoCombo)));
        burgueriaList.add(new Burgueria("PvP", R.drawable.variado, 16, getString(R.string.descricaoPvP)));
        burgueriaList.add(new Burgueria("Triplo", R.drawable.triplo, 12, getString(R.string.descricaoTriplo)));

        return view;
    }

    //Método da Interface para quando um Lanche for Clicado no RecyclerView
    //O RecyclerView passa um objeto Burgueria para o método
    @Override
    public void onItemClicked(Burgueria burgueria) {
        //Manda as informações sobre o Lanche Escolhido para a próxima Tela
        LancheEscolhido lancheEscolhido = new LancheEscolhido();
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