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
    private static List<Burgueria> listaGeral = new ArrayList<>();


    //Construtores
    public Burgueria(){}
    public Burgueria(String nameLanche, int imageLanche, int priceLanche) {
        this.nameLanche = nameLanche;
        this.imageLanche = imageLanche;
        this.priceLanche = priceLanche;
    }

    public String getNameLanche(){
        return nameLanche;
    }
    public int getImageLanche(){
        return imageLanche;
    }
    public void setPriceLanche(String priceLanche){
        this.priceLanche = priceLanche;
    }
    public int getPriceLanche(){
        return priceLanche;
    }

    public void setQuantidLanche(int quantidLanche){
        this.quantidLanche = quantidLanche;
    }
    public void addQuantidLanche(){
        quantidLanche++;
    }
    public boolean removeQuantidLanche(){
        if (quantidLanche > 0){
            quantidLanche--;
            return true;
        }
        else{
            return false;
        }

    }
    public int getQuantidLanche(){return quantidLanche;}

    public void updateListaGeral(Burgueria burg){
        listaGeral.add(burg);
    }
    public List<Burgueria> getListaGeral(){
        return listaGeral;
    }


};
