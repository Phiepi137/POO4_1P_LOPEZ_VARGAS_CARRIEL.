package com.example;

import java.util.ArrayList;

import com.example.enums.RolUsuario;
import com.example.enums.TipoCompra;

public class Organizador extends Usuario {

    // #region Variables de Instancia
    private String empresaOrganizadora;
    private String cargo;
    //#endregion

    // #region Constructor
    public Organizador(String codigo,
            String cedula,
            String nombres,
            String apellidos,
            String usuario,
            String contrasenia,
            String correo,
            RolUsuario rol,
            String empresaOrganizadora,
            String cargo) {

        super(codigo, cedula, nombres, apellidos,
                usuario, contrasenia, correo);

        this.empresaOrganizadora = empresaOrganizadora;
        this.cargo = cargo;
        this.rol = RolUsuario.O;

    }
    // #endregion

    // #region getters y setters
    public String getEmpresaOrganizadora() {
        return empresaOrganizadora;
    }

    public void setEmpresaOrganizadora(String empresaOrganizadora) {
        this.empresaOrganizadora = empresaOrganizadora;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // #endregion

    // #region Metodos
    @Override
    public void consultarEntradas(ArrayList<Compra> compras) {
        System.out.println("El organizador " + cargo + " de la empresa " + empresaOrganizadora
                + " puede consultar las entradas vendidas.");
        System.out.println("\n========== TODAS LAS COMPRAS ==========");
        if (compras.size() == 0) {
            System.out.println("No existen compras registradas.");
            return;
        }
        for (Compra compra : compras) {
            System.out.println(compra);
        }
    }
    public void generarReporte(ArrayList<Compra> compras){
        System.out.println("===== GENERAR REPORTE DE VENTAS =====");
        System.out.println("Resumen de ventas registradas");
        System.out.println("Total de compras: "+compras.size());
        System.out.println("Compras por tipo:");
        int montoKit=0;
        int montoEntrada=0;
        double montoTotal=0;
        for(Compra c: compras){
            montoTotal=montoTotal+c.getValorPagado();
            if(c.getTipo()==TipoCompra.ENTRADA){
                montoEntrada++;
            }else{
                montoKit++;
            }
        }
        double montoTotal= montoKit+montoEntrada;
        System.out.println("Entradas: "+montoEntrada);
        System.out.println("Kits: "+montoKit);
        System.out.println("Monto recaudado: "+montoTotal);

    }

    @Override
    public String toString() {

        return super.toString()
                + "\nEmpresa Organizadora: " + empresaOrganizadora
                + "\nCargo: " + cargo;

    }

    //#endregion

}