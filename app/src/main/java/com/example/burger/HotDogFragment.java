package com.example.burger;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HotDogFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_hot_dog, container, false);

        RecyclerView recyclerHotDog = view.findViewById(R.id.recyclerHotDog);

        List<Burgueria> burgueriaListHotDog = new ArrayList<>();

        AdapterRecycler adapterRecycler = new AdapterRecycler(burgueriaListHotDog);

        Context context = getActivity();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);

        recyclerHotDog.setLayoutManager(gridLayoutManager);
        recyclerHotDog.setAdapter(adapterRecycler);

        Burgueria hotDog1 = new Burgueria("SALADINHA", R.drawable.a);
        Burgueria hotDog2 = new Burgueria("FAMINTO", R.drawable.b);
        Burgueria hotDog3 = new Burgueria("BIG MEG", R.drawable.c);
        Burgueria hotDog4 = new Burgueria("SEM FOME", R.drawable.d);

        burgueriaListHotDog.add(hotDog1);
        burgueriaListHotDog.add(hotDog2);
        burgueriaListHotDog.add(hotDog3);
        burgueriaListHotDog.add(hotDog4);


        return view;
    }
}