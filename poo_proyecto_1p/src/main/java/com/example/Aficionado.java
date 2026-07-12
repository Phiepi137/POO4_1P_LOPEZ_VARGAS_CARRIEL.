package com.example;

import com.example.enums.EstadoCompra;
import com.example.enums.TipoCompra;
import com.example.enums.TipoZona;

public class Aficionado extends Usuario{
    private String celular;
    private String paisFavorito;

    // Constructor
    public Aficionado(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasenia, String correo, String celular, String paisFavorito){
        super(codigoUnico, cedula, nombres, apellidos, usuario, contrasenia, correo);
        this.celular=celular;
        this.paisFavorito=paisFavorito;
    }

    // Metodos
    @Override
    public void conslMisEntradas(ArrayList<Compra> compras){}){
        String tieneCompras= false;
        for(Compra c: compras){
            if(c.getCodigoUnico().equals(this.codigoUnico()){
                System.out.println(c)
                tieneCompras=true;
            }
        }
        ig(tieneCompras){
            System.out.println("No tiene compras realizadas.")
        }


    }
     
    public Compra comprEntradas(String codigo, TipoZona zona, int cantidad, String tarjeta){
        double total=0;
        Compra c=new Compra(TipoCompra.ENTRADA,codigo,cantidad,total,this.getCodigoUnico(),EstadoCompra.EXITOSA);
        return c;
    }
    public Compra comprEntradas(String codigo, int cantidad, String tarjeta){
        double total=0;
        Compra c=new Compra(TipoCompra.ENTRADA,codigo,cantidad,total,this.getCodigoUnico(),EstadoCompra.EXITOSA);
        return c;
    }
    }
    