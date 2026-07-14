package com.example;
//Clase Momentanea para manejar la lectura de archivos de texto plano
import java.io.*;
import java.util.ArrayList;

public class ManejoArchivos {

    public static ArrayList<Usuario> leerUsuarios(String ruta) {

        ArrayList<Usuario> usuarios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split("\\|");

                String codigo = datos[0];
                String cedula = datos[1];
                String nombres = datos[2];
                String apellidos = datos[3];
                String usuario = datos[4];
                String contrasenia = datos[5];
                String correo = datos[6];
                String rol = datos[7];

                if (rol.equals("A")) {

                    usuarios.add(new Aficionado(
                            codigo,
                            cedula,
                            nombres,
                            apellidos,
                            usuario,
                            contrasenia,
                            correo,
                            "",          // celular
                            ""          // país favorito
                    ));

                } else {

                    usuarios.add(new Organizador(
                            codigo,
                            cedula,
                            nombres,
                            apellidos,
                            usuario,
                            contrasenia,
                            correo,
                            null,
                            "",          // empresa
                            ""           // cargo
                    ));

                }

            }

        } catch (IOException e) {
            System.out.println("Error al leer usuarios.");
        }

        return usuarios;
    }

}