package com.example;
public class Organizador{
    private String empresaOrganizadora;
    private String cargo;
    // Constructor
    public Organizador(String empresaOrganizadora, String cargo){
        this.empresaOrganizadora=empresaOrganizadora;
        this.cargo=cargo;
    }
    // Getters
    public String getEmpresaOrganizadora(){
        return empresaOrganizadora;
    }
    public String getCargo(){
        return cargo;
    }
    // Setters
    public void setEmpresaOrganizadora(String empresaOrganizadora){
        this.empresaOrganizadora=empresaOrganizadora;
    }
    public void setCargo(String cargo){
        this.cargo=cargo;
    }
    // Metodos
    @Override
    public void conslEntradas(){
        System.out.println("El organizador "+cargo+" de la empresa "+empresaOrganizadora+" puede consultar las entradas vendidas.");
    }

}