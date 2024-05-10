package com.example.burger.Endereco;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.burger.ActivityHost;
import com.example.burger.TelaInicial;
import com.example.burger.databinding.EnderecoEditaBinding;


public class EditaEndereco extends Fragment {

    private EnderecoEditaBinding binding;

    //DADOS DO USUÁRIO - NOME E ENDEREÇO
    SharedPreferences sharedPreferences;
    private static final String SHARED_DADOS_USUARIO = "DadosUsuario";
    private static final String KEY_NOME = "nome";
    private static final String KEY_RUA = "rua";
    private static final String KEY_NUMERO = "numero";
    private static final String KEY_BAIRRO = "bairro";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = EnderecoEditaBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        Context context = getActivity();

        sharedPreferences = context.getSharedPreferences(SHARED_DADOS_USUARIO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        binding.salvaEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context1 = getActivity();

                //Caso seja a primeira vez q abri o Aplicativo
                if (sharedPreferences.getAll().isEmpty()) {
                    salvaDadosUsuario(editor);
                    Toast.makeText(getContext(), "SALVO COM SUCESSO!", Toast.LENGTH_SHORT).show();

                    //É preciso deixar o botão (PedeAki) da Tela Inicial INVISÍVEL
                    ((TelaInicial) getActivity()).visibilityPedeAq(true);

                    //E remover esse Fragment da Pilha
                    getParentFragmentManager().beginTransaction().remove(EditaEndereco.this).commit();

                    //Inicia a Activity com o Cardápio dos Lacnhes
                    Intent intent = new Intent(getActivity(), ActivityHost.class);
                    startActivity(intent);
                } else {

                    //Caso o usuário já tenha colocado um endereço antes, e agora quer mudá-lo
                    //Só preciso salvar os dados do novo endereço
                    salvaDadosUsuario(editor);
                    Toast.makeText(getContext(), "SALVO COM SUCESSO!", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().popBackStack();
                }

            }
        });
        return view;
    }

    //Salvar os dados do usuário. É passado, como parâmetro, um EDITOR do SharedPreferences
    public void salvaDadosUsuario(SharedPreferences.Editor editor) {
        editor.putString(KEY_NOME, binding.nome.getText().toString());
        editor.putString(KEY_RUA, binding.rua.getText().toString());
        editor.putString(KEY_NUMERO, binding.numero.getText().toString());
        editor.putString(KEY_BAIRRO, binding.bairro.getText().toString());
        editor.commit();
    }
}