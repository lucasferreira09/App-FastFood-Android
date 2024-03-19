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

public class MainActivity extends AppCompatActivity {


    private static final String SHARED_PREF_DADOS = "myDados";
    private static final String KEY_NOME = "nome";

    SharedPreferences sharedPreferences;
    private ImageView pedeAq;
    ConstraintLayout consts;
    FragmentContainerView containt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pedeAq = findViewById(R.id.pedeAq);
        consts = findViewById(R.id.consts);

        containt = findViewById(R.id.containt);

        sharedPreferences = getSharedPreferences(SHARED_PREF_DADOS, MODE_PRIVATE);
        String nomeUsuario = sharedPreferences.getString(KEY_NOME, "");

        if (nomeUsuario.equals("lucas")) {
            visibilityPedeAq(true);
        }


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //Apenas um botão que inicia o Menu com os Lanches
        pedeAq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditarEndereco editarEndereco = new EditarEndereco();

                if (nomeUsuario.equals("")) {
                    visibilityPedeAq(false);
                    ft.replace(R.id.containt, editarEndereco);
                    ft.commit();
                }
                else {
                    Intent ac = new Intent(MainActivity.this, ActivityHost.class);
                    startActivity(ac);
                }

            }
        });


    }
    public void visibilityPedeAq(boolean vf) {
        if (vf == true) {
            pedeAq.setVisibility(View.VISIBLE);
        } else {
            pedeAq.setVisibility(View.GONE);
        }

    }
}