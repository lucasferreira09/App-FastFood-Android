package com.example.burger.interfaces;

import com.example.burger.Burgueria;

import java.util.List;

/*

Essa interface é necessária, pois a view que contém o valor total do pedido
está fora do RecyclerView do carrinho, e é preciso atualizá-la sempre que um produto
for adicionado ou removido do carrinho

 */

public interface ListenerTextView {

    //Passa uma lista de Objetos burgueria
    void clickTextView(List<Burgueria> listaBurgueria);
}
