package com.example.burger;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.burger.Endereco.EditaEndereco;
import com.example.burger.Endereco.VerEndereco;
import com.example.burger.databinding.FragmentCarrinhoBinding;

public class CarrinhoFragment extends Fragment implements TelaCarrinhoCompras.OnButtonClickListener {

    private FragmentCarrinhoBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCarrinhoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        //De início, o Layout será a lista do que foi adicionado ao Carrinho
        TelaCarrinhoCompras telaCarrinhoCompras = new TelaCarrinhoCompras();
        telaCarrinhoCompras.setOnButtonClickListener(this); // Define o listener

        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_carrinho, telaCarrinhoCompras);
        fragmentTransaction.commit();

        return view;
    }

    @Override
    public void onButtonClicked() {

        VerEndereco novoFragmento = new VerEndereco();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.frame_carrinho, novoFragmento)
                .commit();

    }

}
