package com.example;

import java.util.ArrayList;
public class Kit{
    private String codigoKit;
    private String nombre;
    private String descripcion;
    private ArrayList<Partido> partidosIncluidos;
    private double precio;
    private int disponibles;

    // getters
    public String getCodigoKit(){
        return codigoKit;
    }
    public ArrayList<Partido> getPartidosIncluidos(){
        return partidosIncluidos;
    }
    public int getDisponible(){
        return disponibles;
    }
    public String getNombre(){
        return nombre;
    }
    // setters
    public void setDisponible(int disponibles){
        this.disponibles=disponibles;
    }
    // metodo
    public boolean disponibilidad(){
        return disponibles>0;
    }
    @Override
    public String toString(){
        return "Codigo del Kit: "+codigoKit+"\n"+nombre+"\n"+descripcion+"\n Precio: "+precio;
    }
}