package com.example;

import com.example.enums.*;

public class Zona {
    private TipoZona tipo;
    private int disponible;
    private double precio;

    // constructor
    public Zona(TipoZona tipo, int disponible, double precio) {
        this.tipo = tipo;
        this.disponible = disponible;
        this.precio = precio;
    }

    // getters
    public TipoZona getTipo() {
        return tipo;
    }

    public int getDisponible() {
        return disponible;
    }

    public double getPrecio() {
        return precio;
    }

    // setters
    public void setTipo(TipoZona tipo) {
        this.tipo = tipo;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // metodos
    public boolean hayDisponibilidad(int cantidad) {
        return disponible >= cantidad;
    }

    public void actualizarZona(String zona, int cantidad) {
        if (hayDisponibilidad(cantidad)) {
            disponible -= cantidad;
            System.out.println("Asientos disponibles en la zona " + zona + ": " + disponible);
        }

    }

    @Override
    public String toString() {

        return tipo +
                " | Disponibles: " + disponible +
                " | Precio: $" + precio;

    }
}