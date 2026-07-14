package com.example;

import java.util.Date;
import com.example.enums.FasePartido;
import com.example.enums.TipoZona;

public class Partido {
    // Atributos
    private String codigoPartido;
    private String seleccionLocal;
    private String seleccionVisitante;
    private Date fecha;
    private String estadio;
    private String ciudad;
    private int capacidad;
    private FasePartido fase;
    private int[] entradasPorZona = new int[3];

    // #region constructor
    public Partido(String codigo,
            String seleccionLocal,
            String seleccionVisitante,
            Date fecha,
            String estadio,
            String ciudad,
            int capacidad,
            FasePartido fase,
            int entradasGeneral,
            int entradasPreferencial,
            int entradasVIP) {
        this.codigoPartido = codigo;
        this.seleccionLocal = seleccionLocal;
        this.seleccionVisitante = seleccionVisitante;
        this.fecha = fecha;
        this.estadio = estadio;
        this.ciudad = ciudad;
        this.capacidad = capacidad;
        this.fase = fase;
        this.entradasPorZona[0] = entradasGeneral;
        this.entradasPorZona[1] = entradasPreferencial;
        this.entradasPorZona[2] = entradasVIP;
    }

    // #endregion

    // #region getters y setters
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

    public Date getFecha() {
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

    public int[] getEntradasPorZona() {
        return entradasPorZona;
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

    public void setFecha(Date fecha) {
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

    public void setEntradasPorZona(int eG, int eP, int eV) {
        this.entradasPorZona[0] = eG;
        this.entradasPorZona[1] = eP;
        this.entradasPorZona[3] = eV;
    }

    // #endregion

    // Metodos
    public void mostrarInformacion() {

        System.out.println("--------------------------------");
        System.out.println("Código: " + this.codigoPartido);
        System.out.println(seleccionLocal +
                " vs " +
                seleccionVisitante);

        System.out.println("Fecha: " + fecha);
        System.out.println("Estadio: " + estadio);
        System.out.println("Ciudad: " + ciudad);
        System.out.println("Fase: " + fase);

        System.out.println("\nZonas disponibles:");

        System.out.println("- GENERAL        | Disponibles: " + entradasPorZona[0] + " | Precio: $"
                + (int) (Math.random() * (1000)));
        System.out.println("- PREFERENCIAL   | Disponibles: " + entradasPorZona[1] + " | Precio: $"
                + (int) (Math.random() * (1000)));
        System.out.println("- VIP            | Disponibles: " + entradasPorZona[2] + " | Precio: $"
                + (int) (Math.random() * (1000)));
        System.out.println("--------------------------------");
    }

    public String toString(){
        return this.seleccionLocal + " vs " + this.seleccionVisitante;
    }

}