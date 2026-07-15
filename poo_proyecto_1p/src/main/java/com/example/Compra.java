package com.example;

import com.example.enums.TipoCompra;
import com.example.enums.EstadoCompra;
import java.util.Date;

public class Compra {

    // #region Variables de Instancia
    private String codigoCompra;
    private TipoCompra tipo;
    private String codigoReferencia;
    private Date fecha;
    private int cantidad;
    private double valorPagado;
    private String codigoAficionado;
    private EstadoCompra estado;
    // #endregion

    private static int numCompras = 0;

    // #region Constructor
    public Compra(TipoCompra tipo, String codigoReferencia, int cantidad, double valorPagado, String codigoAficionado/* ,
            EstadoCompra estado*/) {
        numCompras++;
        this.codigoCompra = "C00" + numCompras;
        this.tipo = tipo;
        this.codigoReferencia = codigoReferencia;
        this.fecha = new Date();
        this.cantidad = cantidad;
        this.valorPagado = valorPagado;
        this.codigoAficionado = codigoAficionado;
        //this.estado = estado;
    }
    // Sobrecarga de constructor para lectura de compras.txt
    public Compra(String codigoCompra, TipoCompra tipo, String codigoReferencia,
        Date fecha, int cantidad, double valorPagado, String codigoAficionado) {
        this.codigoCompra = codigoCompra;
        this.tipo = tipo;
        this.codigoReferencia = codigoReferencia;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.valorPagado = valorPagado;
        this.codigoAficionado = codigoAficionado;
}
    // #endregion

    // #region getters y setter
    public static void setNumCompras(int numCompras){
        Compra.numCompras=numCompras;
    }
    public String getCodigoCompra() {
        return codigoCompra;
    }

    public TipoCompra getTipo() {
        return tipo;
    }

    public void setTipo(TipoCompra tipo) {
        this.tipo = tipo;
    }

    public String getCodigoReferencia() {
        return codigoReferencia;
    }

    public void setCodigoReferencia(String codigoReferencia) {
        this.codigoReferencia = codigoReferencia;
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
                "\nReferencia: " + codigoReferencia +
                "\nFecha: " + fecha +
                "\nCantidad: " + cantidad +
                "\nValor pagado: $" + valorPagado +
                "\nEstado: EXITOSA" +
                "\nCódigo aficionado: " + codigoAficionado+"\n------------------------";
    }
}