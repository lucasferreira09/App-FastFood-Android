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

public class BurgerFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_burger, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        List<Burgueria> burgueriaList = new ArrayList<>();
        AdapterRecycler adapter = new AdapterRecycler(burgueriaList);

        recyclerView.setAdapter(adapter);
        Context context = getActivity();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        Burgueria b1 = new Burgueria("MEGA", R.drawable.burguera);
        Burgueria b2 = new Burgueria("TOP", R.drawable.burguerb);
        Burgueria b3 = new Burgueria("EXTRA", R.drawable.burguerc);
        Burgueria b4 = new Burgueria("BIG", R.drawable.burguerd);

        burgueriaList.add(b1);
        burgueriaList.add(b2);
        burgueriaList.add(b3);
        burgueriaList.add(b4);



        return view;
    }
}