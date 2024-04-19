package com.example.burger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.burger.databinding.FragmentEscolhidoLancheBinding;
import com.example.burger.databinding.InicialTelaBinding;

public class TelaInicial extends AppCompatActivity {

    private InicialTelaBinding binding;

    private static final String SHARED_PREF_DADOS = "myDados";
    private static final String KEY_NOME = "nome";
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = InicialTelaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        sharedPreferences = getSharedPreferences(SHARED_PREF_DADOS, MODE_PRIVATE);
        String nomeUsuario = sharedPreferences.getString(KEY_NOME, "");

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        //Apenas um botão que inicia o Menu com os Lanches
        binding.pedeAq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditaEndereco editaEndereco = new EditaEndereco();

                if (sharedPreferences.getAll().isEmpty()) {
                    visibilityPedeAq(false);
                    ft.replace(R.id.containerView, editaEndereco);
                    ft.commit();
                }
                else {
                    Intent ac = new Intent(TelaInicial.this, ActivityHost.class);
                    startActivity(ac);
                }
            }
        });
    }

    public void visibilityPedeAq(boolean vf) {
        if (vf == true) {
            binding.pedeAq.setVisibility(View.VISIBLE);
        } else {
            binding.pedeAq.setVisibility(View.GONE);
        }
    }
}