package com.example;
import com.example.enums.TipoCompra;
import com.example.enums.EstadoCompra;
import java.util.Date;

public class Compra{
    private String codigoCompra;
    private TipoCompra tipo;
    private String codigoReferencia;
    private Date fecha;
    private int cantidad;
    private double valorPagado;
    private String codigoAficionado;
    private EstadoCompra estado;
    private static int numCompras=0;

    // Constructor
    public Compra(TipoCompra tipo, String codigoReferencia, int cantidad, double valorPagado, String codigoAficionado, EstadoCompra estado){
    numCompras++;
    this.codigoCompra="C"+numCompras;
    this.tipo=tipo;
    this.codigoReferencia=codigoReferencia;
    this.fecha=new Date();
    this.cantidad=cantidad;
    this.valorPagado=valorPagado;
    this.codigoAficionado=codigoAficionado;
    this.estado=estado;
    }
    public double getValorPagado(){
        return valorPagado;
    }
}