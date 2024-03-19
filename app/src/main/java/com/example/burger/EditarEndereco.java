package com.example.burger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class EditarEndereco extends Fragment {

    //DADOS DO USUÁRIO - NOME E ENDEREÇO
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_DADOS = "myDados";
    private static final String KEY_NOME = "nome";
    private static final String KEY_RUA = "rua";
    private static final String KEY_NUMERO = "numero";
    private static final String KEY_BAIRRO = "bairro";

    private EditText nome;
    private EditText rua;
    private EditText numero;
    private EditText bairro;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_endereco_editar, container, false);

        ImageView salvaEndereco = view.findViewById(R.id.salvaEndereco);

        nome = view.findViewById(R.id.nome);
        rua = view.findViewById(R.id.rua);
        numero = view.findViewById(R.id.numero);
        bairro = view.findViewById(R.id.bairro);


        Context context = getActivity();

        sharedPreferences = context.getSharedPreferences(SHARED_PREF_DADOS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        salvaEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context1 = getActivity();

                //Caso seja a primeira vez q abri o Aplicativo
                if (sharedPreferences.getAll().isEmpty()) {
                    salvaDadosUsuario(editor);
                    Toast.makeText(getContext(), "SALVO COM SECESSO!", Toast.LENGTH_SHORT).show();

                    //É preciso deixar o botão da Tela Inicial INVISÍVEL
                    ((MainActivity) getActivity()).visibilityPedeAq(true);
                    //E remover esse Fragment da Pilha
                    getParentFragmentManager().beginTransaction().remove(EditarEndereco.this).commit();

                    //Inicia a Activity com o Cardápio dos Lacnhes
                    Intent intent = new Intent(getActivity(), ActivityHost.class);
                    startActivity(intent);
                } else {

                    //Caso o usuário já tenha colocado um endereço antes, e agora quer mudá-lo
                    //Só preciso salvar os dados do novo endereço
                    salvaDadosUsuario(editor);
                    Toast.makeText(getContext(), "SALVO COM SECESSO!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    //Salvar os dados do usuário. É passado, como parâmetro, um EDITOR do SharedPreferences
    public void salvaDadosUsuario(SharedPreferences.Editor editor) {
        editor.putString(KEY_NOME, nome.getText().toString());
        editor.putString(KEY_RUA, rua.getText().toString());
        editor.putString(KEY_NUMERO, numero.getText().toString());
        editor.putString(KEY_BAIRRO, bairro.getText().toString());
        editor.commit();
    }
}