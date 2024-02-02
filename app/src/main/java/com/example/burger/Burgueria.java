package com.example.burger;

public class Burgueria {
    private String nameLanche;
    private int imageLanche;

    public Burgueria(String nameLanche, int imageLanche) {
        this.nameLanche = nameLanche;
        this.imageLanche = imageLanche;
    }

    public String getNameLanche(){
        return nameLanche;
    }
    public int getImageLanche(){
        return imageLanche;
    }

};
