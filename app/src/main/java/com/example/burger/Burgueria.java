package com.example.burger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Burgueria {
    private String nameLanche;
    private String descricaoLanche;
    private int imageLanche;
    private int priceLanche;
    private int quantidLanche;
    private int totalLanche;
    private static int valorPedidoTotal;

    private static List<Burgueria> listaGeral = new ArrayList<>();



    //Construtores
    public Burgueria(){}
    public Burgueria(String nameLanche, int imageLanche) {
        this.nameLanche = nameLanche;
        this.imageLanche = imageLanche;
    }
    public Burgueria(String nameLanche, int imageLanche, int priceLanche, String descricaoLanche) {
        this.nameLanche = nameLanche;
        this.imageLanche = imageLanche;
        this.priceLanche = priceLanche;
        this.descricaoLanche = descricaoLanche;
    }

    //Métodos SET
    public void setTotalLanche() {
        totalLanche = quantidLanche * priceLanche;
    }


    //Métodos GET
    public String getNameLanche(){
        return nameLanche;
    }
    public int getImageLanche(){
        return imageLanche;
    }
    public int getPriceLanche(){
        return priceLanche;
    }
    public String getDescricaoLanche(){
        return descricaoLanche;
    }
    public int getTotalLanche() {
        return totalLanche;
    }
    public int getQuantidLanche(){return quantidLanche;}

    public List<Burgueria> getListaGeral(){
        return listaGeral;
    }


    //Métodos de Atualização
    public void addQuantidLanche(){
        quantidLanche++;
        if (totalLanche == 0){
            totalLanche = priceLanche;
            valorPedidoTotal = totalLanche;
        }
        else {
            totalLanche = priceLanche*quantidLanche;
            valorPedidoTotal = totalLanche;
        }
    }
    public void removeQuantidLanche(){
        if (quantidLanche > 0){
            quantidLanche--;
        }
    }
    public void updateListaGeral(Burgueria burg){
        listaGeral.add(burg);
    }

};
