package com.example.burger.Endereco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.burger.R;
import com.example.burger.TelaCarrinhoCompras;
import com.example.burger.databinding.EnderecoEditaBinding;
import com.example.burger.databinding.EnderecoVerBinding;


public class VerEndereco extends Fragment {

    private EnderecoVerBinding binding;
    private static final String SHARED_DADOS_USUARIO = "DadosUsuario";
    private static final String KEY_NOME = "nome";
    private static final String KEY_RUA = "rua";
    private static final String KEY_NUMERO = "numero";
    private static final String KEY_BAIRRO = "bairro";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = EnderecoVerBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Context context = getActivity();

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_DADOS_USUARIO, Context.MODE_PRIVATE);

        if (!sharedPreferences.getAll().isEmpty()) {
            binding.textOiUsuario.setText("Oii, " + sharedPreferences.getString(KEY_NOME, ""));
            binding.rua.setText("Rua:, " + sharedPreferences.getString(KEY_RUA, ""));
            binding.numero.setText("Número:  " + sharedPreferences.getString(KEY_NUMERO, ""));
            binding.bairro.setText("Bairro: " + sharedPreferences.getString(KEY_BAIRRO, ""));
        }

        binding.editaEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.constraint, new EditaEndereco());
                ft.commit();

            }
        });


        return view;
    }
}