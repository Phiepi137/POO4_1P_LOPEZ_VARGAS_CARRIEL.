package com.example;

import java.time.LocalDate;
import com.example.enums.FasePartido;
import com.example.enums.TipoZona;

import java.util.ArrayList;

public class Partido {
    // Atributos
    private String codigoPartido;
    private String seleccionLocal;
    private String seleccionVisitante;
    private LocalDate fecha;
    private String estadio;
    private String ciudad;
    private int capacidad;
    private FasePartido fase;
    private ArrayList<Zona> zonas;

    // constructor
    public Partido(String codigo, String seleccionLocal, String seleccionVisitante, LocalDate fecha, String estadio,
            String ciudad, int capacidad, FasePartido fase, ArrayList<Zona> zonas) {
        this.codigoPartido = codigo;
        this.seleccionLocal = seleccionLocal;
        this.seleccionVisitante = seleccionVisitante;
        this.fecha = fecha;
        this.estadio = estadio;
        this.ciudad = ciudad;
        this.capacidad = capacidad;
        this.fase = fase;
        this.zonas = zonas;
    }

    // getters
    public String getCodigoPartido() {
        return codigoPartido;
    }

    public String getSeleccionLocal() {
        return seleccionLocal;
    }

    public String getSeleccionVisitante() {
        return seleccionVisitante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getEstadio() {
        return estadio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public FasePartido getFase() {
        return fase;
    }

    public ArrayList<Zona> getZonas() {
        return zonas;
    }

    // setters
    public void setCodigoPartido(String codigoPartido) {
        this.codigoPartido = codigoPartido;
    }

    public void setSeleccionLocal(String seleccionLocal) {
        this.seleccionLocal = seleccionLocal;
    }

    public void setSeleccionVisitante(String seleccionVisitante) {
        this.seleccionVisitante = seleccionVisitante;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setFase(FasePartido fase) {
        this.fase = fase;
    }

    public void setZonas(ArrayList<Zona> zonas) {
        this.zonas = zonas;
    }

    // Metodos
    public void mostrarInformacion() {

        System.out.println("--------------------------------");

        System.out.println(seleccionLocal +
                " vs " +
                seleccionVisitante);

        System.out.println("Fecha: " + fecha);
        System.out.println("Estadio: " + estadio);
        System.out.println("Ciudad: " + ciudad);
        System.out.println("Fase: " + fase);

        System.out.println("\nZONAS");

        for (Zona z : zonas) {

            System.out.println(z);

        }

    }

    public Zona buscarZona(TipoZona tipo) {

        for (Zona zona : zonas) {

            if (zona.getTipo() == tipo) {

                return zona;

            }

        }

        return null;
    }

    @Override
    public String toString() {

        return codigoPartido + " - "
                + seleccionLocal + " vs "
                + seleccionVisitante;

    }

}