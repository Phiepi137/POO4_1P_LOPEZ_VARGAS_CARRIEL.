package com.example;

import com.example.enums.TipoCompra;
import com.example.enums.EstadoCompra;
import java.util.Date;

public class Compra {

    // #region Variables de Instancia
    private String codigoCompra;
    private TipoCompra tipo;
    private Date fecha;
    private int cantidad;
    private double valorPagado;
    private String codigoAficionado;
    private EstadoCompra estado;
    private String codigoReferencia;
    // #endregion

    private static int numCompras = 0;

    // #region Constructor
    public Compra(TipoCompra tipo, String codigoReferencia, int cantidad, double valorPagado, String codigoAficionado,
            EstadoCompra estado) {
        numCompras++;
        this.codigoCompra = "C" + numCompras;
        this.tipo = tipo;
        this.fecha = new Date();
        this.codigoReferencia = codigoReferencia;
        this.cantidad = cantidad;
        this.valorPagado = valorPagado;
        this.codigoAficionado = codigoAficionado;
        this.estado = estado;
    }

    public Compra(String codigoCompra,
            TipoCompra tipo,
            Date fecha,
            int cantidad,
            double valorPagado,
            String codigoAficionado,
            EstadoCompra estado) {

        this.codigoCompra = codigoCompra;
        this.tipo = tipo;
        this.codigoCompra = "C" + numCompras;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.valorPagado = valorPagado;
        this.codigoAficionado = codigoAficionado;
        this.estado = estado;
    }
    // #endregion

    // #region getters y setter
    public String getCodigoCompra() {
        return codigoCompra;
    }

    public TipoCompra getTipo() {
        return tipo;
    }

    public void setTipo(TipoCompra tipo) {
        this.tipo = tipo;
    }

    public void setCodigoCompra(String codigoCompra) {
        this.codigoCompra = codigoCompra;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }

    public String getCodigoAficionado() {
        return codigoAficionado;
    }

    public void setCodigoAficionado(String codigoAficionado) {
        this.codigoAficionado = codigoAficionado;
    }

    public EstadoCompra getEstado() {
        return estado;
    }

    public void setEstado(EstadoCompra estado) {
        this.estado = estado;
    }

    // #endregion

    @Override
    public String toString() {

        return "Código: " + codigoCompra +
                "\nTipo: " + tipo +
                "\nReferencia: " + codigoCompra +
                "\nFecha: " + fecha +
                "\nCantidad: " + cantidad +
                "\nValor pagado: $" + valorPagado +
                "\nEstado: " + estado +
                "\nCódigo aficionado: " + codigoAficionado;
    }
}