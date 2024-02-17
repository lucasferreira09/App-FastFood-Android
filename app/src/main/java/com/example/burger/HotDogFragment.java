package com.example.burger;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HotDogFragment extends Fragment implements SelectListener{

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

        AdapterRecycler adapterRecycler = new AdapterRecycler(burgueriaListHotDog, context, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);

        recyclerHotDog.setLayoutManager(gridLayoutManager);
        recyclerHotDog.setAdapter(adapterRecycler);

        burgueriaListHotDog.add(new Burgueria("Saladinha", R.drawable.a));
        burgueriaListHotDog.add(new Burgueria("Faminto", R.drawable.b));
        burgueriaListHotDog.add(new Burgueria("Big Mec", R.drawable.c));
        burgueriaListHotDog.add(new Burgueria("Sem Fome", R.drawable.d));

        return view;
    }

    @Override
    public void onItemClicked(Burgueria burgueria) {
        Bundle bundle = new Bundle();
        bundle.putString("nameLanche", burgueria.getNameLanche());
        bundle.putInt("idImage", burgueria.getImageLanche());
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ChoiceFragment choiceFragment = new ChoiceFragment();
        choiceFragment.setArguments(bundle);
        ft.replace(R.id.constraintLayout, choiceFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}