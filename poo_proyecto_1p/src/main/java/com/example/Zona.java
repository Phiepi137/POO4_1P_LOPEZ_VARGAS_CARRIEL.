package com.example;
import com.example.enums.TipoZona;

public class Zona{
    private TipoZona tipo;
    private int disponible;
    private double precio;

    // getters
    public TipoZona getTipo(){
        return tipo;
    }
    public int getDisponible(){
        return disponible;
    }
    public double getPrecio(){
        return precio;
    }

    // setters
    public void setTipo(TipoZona tipo){
        this.tipo = tipo;
    }
    public void setDisponible(int disponible){
        this.disponible = disponible;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }

    // metodos
    public boolean disponibilidad(String zona, int cantidad){
        // ...
    }
    public void actualizarZona(String zona, int cantidad){
        // ...
    }
}