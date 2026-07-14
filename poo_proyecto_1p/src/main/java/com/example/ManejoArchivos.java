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
    // completar arhivos de organizador
    public static void leerOrganizadores(String ruta, ArrayList<Usuario> us) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            br.readLine(); // Saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                String codigo = datos[0];
                String empresa = datos[4];
                String cargo = datos[5];
                for (Usuario u : us) {
                    if (u instanceof Organizador && u.getCodigo().equals(codigo)) {
                        Organizador organizador = (Organizador) u;
                        organizador.setEmpresaOrganizadora(empresa);
                        organizador.setCargo(cargo);
                        break;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer organizadores.");
        }
    }

    // leer archivos de partidos
    public static ArrayList<Partido> leerPartidos(String ruta){
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

                // Crear las zonas
                ArrayList<Zona> zonas = new ArrayList<>();
                zonas.add(new Zona(TipoZona.GENERAL,Integer.parseInt(datos[7]),45.0));
                zonas.add(new Zona(TipoZona.PREFERENCIAL,Integer.parseInt(datos[8]),85.0));
                zonas.add(new Zona(TipoZona.VIP,Integer.parseInt(datos[9]),150.0));

                String faseTexto = datos[10].trim().toUpperCase().replace(" ", "_");
                FasePartido fase = FasePartido.valueOf(faseTexto);
                partidos.add(new Partido(codigo,local,visitante,fecha,estadio,ciudad,capacidad,fase,zonas));
            }

        } catch (Exception e) {
            System.out.println("Error al leer partidos: " + e.getMessage());
        }
        return partidos;
        
        
    }
    // leer archivos de kits
    public static ArrayList<Kit> leerKits(String ruta, ArrayList<Partido> listaPartidos) {
        ArrayList<Kit> kits = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            br.readLine(); // Saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                String codigo = datos[0];
                String nombre = datos[1];
                String descripcion = datos[2];
                // Partidos incluidos en el kit
                ArrayList<Partido> partidosIncluidos = new ArrayList<>();
                String[] codigosPartidos = datos[3].split(",");
                for (String codigoPartido : codigosPartidos) {
                    codigoPartido = codigoPartido.trim();
                    for (Partido p : listaPartidos) {
                        if (p.getCodigoPartido().equals(codigoPartido)) {
                            partidosIncluidos.add(p);
                            break;
                        }
                    }
                }
                double precio = Double.parseDouble(datos[4]);
                int disponibles = Integer.parseInt(datos[5]);
                kits.add(new Kit(codigo, nombre, descripcion, partidosIncluidos, precio, disponibles));
            }
        } catch (Exception e) {
            System.out.println("Error al leer kits: " + e.getMessage());
        }
        return kits;
    }
}