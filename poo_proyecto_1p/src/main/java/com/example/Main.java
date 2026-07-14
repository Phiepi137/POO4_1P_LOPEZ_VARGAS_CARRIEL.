package com.example;

public class Main {
    public static void main(String[] args) {
                Sistema sistema = new Sistema();

        sistema.cargarUsuarios();
        sistema.cargarAficionados();
        sistema.cargarOrganizadores();
        sistema.cargarPartidos();
        sistema.cargarKits();

        sistema.iniciarSesion();
    }
}