package com.example;

public abstract class Usuario {
protected String codigoUnico;
protected String cedula;
protected String nombres;
protected String apellidos;
protected String usuario;
protected String contrasenia;
protected String correo;
protected RolUsuario rol;

// Constructor
public Usuario(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasenia, String correo){
    this.codigoUnico=codigoUnico;
    this.cedula=cedula;
    this.nombres=nombres;
    this.apellidos=apellidos;
    this.usuario=usuario;
    this.contrasenia=contrasenia;
    this.correo=correo;
    this.rol=rol;
}
// getters
public String getCodigoUnico(){
    return codigoUnico;
}
public String getUsuario(){
    return usuario;
}
public String getContraseña(){
    return contrasenia
}
public RolUsuario getRol(){
    return rol;
}
// Metodos
public abstract void conslEntradas();




}
