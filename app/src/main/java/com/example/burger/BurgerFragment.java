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

public class BurgerFragment extends Fragment implements SelectListener {

    private ConstraintLayout constraintLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_burger, container, false);

        CarrinhoFragment carrinhoFragment = new CarrinhoFragment();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        constraintLayout = view.findViewById(R.id.constraintLayout);

        List<Burgueria> burgueriaList = new ArrayList<>();

        Context context = getActivity();
        AdapterRecycler adapter = new AdapterRecycler(burgueriaList, context, this);
        recyclerView.setAdapter(adapter);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        burgueriaList.add(new Burgueria("X Bacon", R.drawable.burguera, 9));
        burgueriaList.add(new Burgueria("X Salada", R.drawable.burguerb, 7));
        burgueriaList.add(new Burgueria("Big Mac", R.drawable.burguerd, 15));
        burgueriaList.add(new Burgueria("EXTRA", R.drawable.burguerc));
        burgueriaList.add(new Burgueria("Inglês", R.drawable.burguerb));
        burgueriaList.add(new Burgueria("Top Top", R.drawable.burguerc));
        burgueriaList.add(new Burgueria("Combo", R.drawable.burguera));
        burgueriaList.add(new Burgueria("Americano", R.drawable.burguera));

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
