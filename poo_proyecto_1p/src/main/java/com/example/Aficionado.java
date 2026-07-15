package com.example;

import com.example.enums.RolUsuario;
// import com.example.enums.EstadoCompra;
import com.example.enums.TipoCompra;
import com.example.enums.TipoZona;
import java.util.ArrayList;

public class Aficionado extends Usuario {

    // #region Variables de Instancia
    private String celular;
    private String paisFavorito;
    // #endregion

    // #region Constructor
    public Aficionado(String codigoUnico,
            String cedula,
            String nombres,
            String apellidos,
            String usuario,
            String contrasenia,
            String correo,
            String celular,
            String paisFavorito) {
        super(codigoUnico, cedula, nombres, apellidos, usuario, contrasenia, correo);
        this.celular = celular;
        this.paisFavorito = paisFavorito;
        this.rol=RolUsuario.A;
    }
    // #endregion

    // #region getters y setters
    public String getCelular() {
        return this.celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPaisFavortio() {
        return this.paisFavorito;
    }

    public void setPaisFavorito(String paisFavorito) {
        this.paisFavorito = paisFavorito;
    }
    // #endregion

    // #region Metodos
    @Override
    public void consultarEntradas(ArrayList<Compra> compras) {

        System.out.println("\n========== MIS COMPRAS ==========");
        boolean encontro = false;
        for (Compra compra : compras) {

            if (compra.getCodigoAficionado().equals(getCodigo())) {

                System.out.println(compra);
                encontro = true;

            }

        }

        if (!encontro) {
            System.out.println("No posee compras registradas.");
        }

    }

    @Override
    public String toString() {

        return super.toString()
                + "\nCelular: " + celular
                + "\nPaís favorito: " + paisFavorito;

    }

    public Compra comprarEntradas(String codigo, TipoZona zona, int cantidad, String tarjeta) {
        double total = 0;
        Compra c = new Compra(TipoCompra.ENTRADA, codigo, cantidad, total, this.getCodigo());
        return c;
    }

    public Compra comprarEntradas(String codigo, int cantidad, String tarjeta) {
        double total = 0;
        Compra c = new Compra(TipoCompra.KIT, codigo, cantidad, total, this.getCodigo());
        return c;
    }

    // #endregion
}
