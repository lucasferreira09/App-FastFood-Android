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

    public String getNameLanche(){
        return nameLanche;
    }
    public int getImageLanche(){
        return imageLanche;
    }
    public void setPriceLanche(int priceLanche){
        this.priceLanche = priceLanche;
    }
    public int getPriceLanche(){
        return priceLanche;
    }

    public void setDescricaoLanche(String descricaoLanche){
        this.descricaoLanche = descricaoLanche;
    }
    public String getDescricaoLanche(){
        return descricaoLanche;
    }

    public void setQuantidLanche(int quantidLanche){
        this.quantidLanche = quantidLanche;
    }
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
    public int getQuantidLanche(){return quantidLanche;}

    public void updateListaGeral(Burgueria burg){
        listaGeral.add(burg);
    }
    public List<Burgueria> getListaGeral(){
        return listaGeral;
    }


    public void setTotalLanche() {
        totalLanche = quantidLanche * priceLanche;
    }

    public int getTotalLanche() {
        return totalLanche;
    }

    public String getValorPedidoTotal() {
        return String.valueOf(valorPedidoTotal);
    }
    public void setValorPedidoTotal(int valorPedidoTotal) {
        int total = 0;
        for (Burgueria b : listaGeral){
            int lanche1 = b.getQuantidLanche() * b.getPriceLanche();
            total += lanche1;
        }
        valorPedidoTotal = total;

    }

};
