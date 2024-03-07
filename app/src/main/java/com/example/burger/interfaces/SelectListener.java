package com.example.burger.interfaces;

import com.example.burger.Burgueria;

//Interface necessária para quando um Lanche for clicado
//O listener de Click está no RecyclerView. Quando é o Lanche clicado
//Esse método é chamado
public interface SelectListener {
    void onItemClicked(Burgueria burgueria);
}
