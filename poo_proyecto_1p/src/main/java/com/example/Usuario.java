package com.example;

//Imports
import java.util.ArrayList; //Importación para usar ArrayList
import com.example.enums.RolUsuario; //Importación para usar Enum RolUsuario

//Clase
public abstract class Usuario { //abstracta porque espera implementación específica

    // #region Variables de instancia
    protected String codigoUnico;
    protected String cedula;
    protected String nombres;
    protected String apellidos;
    protected String usuario;
    protected String contrasenia;
    protected String correo;
    protected RolUsuario rol;
    // #endregion

    // #region Constructor
    public Usuario(String codigoUnico,
            String cedula,
            String nombres,
            String apellidos,
            String usuario,
            String contrasenia,
            String correo,
            RolUsuario rol) {
        this.codigoUnico = codigoUnico;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.rol = rol;
    }
    // #endregion

    // #region getters y setters
    public String getCodigo() {
        return codigoUnico;
    }

    public void setCodigo(String codigo) {
        this.codigoUnico = codigo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    // #endregion

    // #region Metodos
    public abstract void consultarEntradas(ArrayList<Compra> compras);

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }

    public boolean autenticar(String usuario, String contrasenia) {
        return this.usuario.equals(usuario) && this.contrasenia.equals(contrasenia);
    }

    @Override
    public String toString() {
        return "Código: " + codigoUnico +
               "\nCédula: " + cedula +
               "\nNombre: " + getNombreCompleto() +
               "\nUsuario: " + usuario +
               "\nCorreo: " + correo +
               "\nRol: " + rol;
    }
    //#endregion

}
