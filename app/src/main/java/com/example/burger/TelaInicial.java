package com.example.burger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.burger.Endereco.EditaEndereco;
import com.example.burger.databinding.InicialTelaBinding;

public class TelaInicial extends AppCompatActivity {

    private InicialTelaBinding binding;

    SharedPreferences sharedPreferences;
    private static final String SHARED_DADOS_USUARIO = "DadosUsuario";
    private static final String KEY_NOME = "nome";
    private static final String KEY_RUA = "rua";
    private static final String KEY_NUMERO = "numero";
    private static final String KEY_BAIRRO = "bairro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = InicialTelaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        sharedPreferences = getSharedPreferences(SHARED_DADOS_USUARIO, MODE_PRIVATE);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //Apenas um botão que inicia o Menu com os Lanches
        binding.pedeAq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditaEndereco editaEndereco = new EditaEndereco();
                String nome = sharedPreferences.getString(KEY_NOME, "");

                if (!nome.equals("")) {
                    Intent ac = new Intent(TelaInicial.this, ActivityHost.class);
                    startActivity(ac);
                } else {
                    visibilityPedeAq(false);
                    ft.replace(R.id.containerView, editaEndereco);
                    ft.commit();
                }
            }
        });
    }

    public void visibilityPedeAq(boolean visibility) {
        if (visibility) {
            binding.pedeAq.setVisibility(View.VISIBLE);
        } else {
            binding.pedeAq.setVisibility(View.GONE);
        }
    }
}