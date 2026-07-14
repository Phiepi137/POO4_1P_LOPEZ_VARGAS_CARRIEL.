package com.example;
//Clase Momentanea para manejar la lectura de archivos de texto plano
import java.io.*;
import java.util.ArrayList;

import com.example.enums.FasePartido;
import com.example.enums.TipoZona;

import java.time.LocalDate;

public class ManejoArchivos {

    public static ArrayList<Usuario> leerUsuarios(String ruta) {

        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            br.readLine();
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
                    // Constructor Aficionado
                    usuarios.add(new Aficionado(codigo,cedula,nombres,apellidos,usuario,contrasenia,correo,
                            "",          // celular
                            ""          // país favorito
                    ));
                } else {
                    //Constructor Organizador
                    usuarios.add(new Organizador(codigo,cedula,nombres,apellidos,usuario,contrasenia,correo,
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

    // Completar casillas de aficionados
    public static void leerAficionados(String ruta, ArrayList<Usuario> us) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;

            br.readLine();
            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split("\\|");
                String codigo = datos[0];
                String celular = datos[4];
                String paisFavorito = datos[5];

                for (Usuario u : us) {

                    if (u instanceof Aficionado && u.getCodigo().equals(codigo)) {
                        Aficionado aficionado = (Aficionado) u;
                        aficionado.setCelular(celular);
                        aficionado.setPaisFavorito(paisFavorito);
                        break;
                    }
                }
            }
        }catch (IOException e){
            System.out.println("Error al leer aficionado.");   
        }
    }


    // leer archivos de partidos
    public static ArrayList<Partido> leerPartidos(String ruta) {

    ArrayList<Partido> partidos = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

        String linea;

        br.readLine(); // saltar encabezado

        while ((linea = br.readLine()) != null) {

            String[] datos = linea.split("\\|");

            String codigo = datos[0];
            String local = datos[1];
            String visitante = datos[2];

            LocalDate fecha = LocalDate.parse(datos[3]);

            String estadio = datos[4];
            String ciudad = datos[5];

            int capacidad = Integer.parseInt(datos[6]);


            // Crear zonas
            ArrayList<Zona> zonas = new ArrayList<>();

            zonas.add(new Zona(
                    TipoZona.GENERAL,
                    Integer.parseInt(datos[7]),
                    0.0
            ));

            zonas.add(new Zona(
                    TipoZona.PREFERENCIAL,
                    Integer.parseInt(datos[8]),
                    0.0
            ));

            zonas.add(new Zona(
                    TipoZona.VIP,
                    Integer.parseInt(datos[9]),
                    0.0
            ));


            FasePartido fase = FasePartido.valueOf(datos[10].toUpperCase());


            partidos.add(new Partido(
                    codigo,
                    local,
                    visitante,
                    fecha,
                    estadio,
                    ciudad,
                    capacidad,
                    fase,
                    zonas
            ));
        }

    } catch (Exception e) {
        System.out.println("Error al leer partidos: " + e.getMessage());
    }

    return partidos;
}
}