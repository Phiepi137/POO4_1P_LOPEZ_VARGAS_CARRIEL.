package com.example;

import java.util.ArrayList;

public class Kit {

    // #region Variables de Instancia
    private String codigoKit;
    private String nombre;
    private String descripcion;
    private ArrayList<Partido> partidosIncluidos;
    private double precio;
    private int disponibles;
    // #endregion

    // #region Constructor
    public Kit(String codigoKit,
            String nombre,
            String descripcion,
            ArrayList<Partido> partidosIncluidos,
            double precio,
            int disponibles) {

        this.codigoKit = codigoKit;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.partidosIncluidos = partidosIncluidos;
        this.precio = precio;
        this.disponibles = disponibles;
    }
    // #endregion

    // #region getters y setters
    public String getCodigoKit() {
        return codigoKit;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<Partido> getPartidosIncluidos() {
        return partidosIncluidos;
    }

    public double getPrecio() {
        return precio;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }
    // #endregion

    // #region metodos
    public boolean disponibilidad() {
        return disponibles > 0;
    }

    public void venderKit() {

        if (disponibles > 0) {
            disponibles--;
        }

    }

    @Override
    public String toString() {

        return "------------------\n"+codigoKit + " - " + nombre +
                "\nDescripción: " + descripcion +
                "\nPrecio: $" + precio +
                "\nDisponibles: " + disponibles+"\n------------------";

    }

    // #endregion
}