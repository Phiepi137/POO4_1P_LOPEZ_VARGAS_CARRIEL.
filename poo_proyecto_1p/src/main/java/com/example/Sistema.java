package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.enums.FasePartido;
import com.example.enums.TipoZona;

public class Sistema {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Partido> partidos;
    private ArrayList<Kit> kits;
    private ArrayList<Compra> compras;
    private Scanner sc;

    public Sistema() {
        usuarios = new ArrayList<>();
        partidos = new ArrayList<>();
        kits = new ArrayList<>();
        compras = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    // #region Metodos de carga de Usuario
    public void cargarUsuarios() {

        ArrayList<String> lineas = ManejoArchivos.LeeFichero("poo_proyecto_1p/src/main/resources/usuarios.txt");

        for (String linea : lineas) {

            String[] datos = linea.split(";");

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
                        "",
                        ""));

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
                        "",
                        ""));

            }

        }

    }
    // #endregion

    // #region Metodo de carga de Aficionados
    public void cargarAficionados() {

        ArrayList<String> usuariosTxt = ManejoArchivos.LeeFichero("poo_proyecto_1p/src/main/resources/usuarios.txt");
        ArrayList<String> aficionadosTxt = ManejoArchivos.LeeFichero("poo_proyecto_1p/src/main/resources/aficionados.txt");

        usuariosTxt.remove(0);
        aficionadosTxt.remove(0);

        for (String lineaUsuario : usuariosTxt) {

            String[] u = lineaUsuario.split("\\|");

            if (!u[7].equals("A"))
                continue;

            for (String lineaAficionado : aficionadosTxt) {

                String[] a = lineaAficionado.split("\\|");

                if (u[0].equals(a[0])) {

                    usuarios.add(new Aficionado(
                            u[0],
                            u[1],
                            u[2],
                            u[3],
                            u[4],
                            u[5],
                            u[6],
                            a[4],
                            a[5]));

                    break;
                }

            }

        }

    }

    // #endregion

    // #region Metodos de carga de Organizador
    public void cargarOrganizadores() {

        ArrayList<String> usuariosTxt = ManejoArchivos.LeeFichero("poo_proyecto_1p/src/main/resources/usuarios.txt");
        ArrayList<String> organizadoresTxt = ManejoArchivos.LeeFichero("poo_proyecto_1p/src/main/resources/organizadores.txt");

        usuariosTxt.remove(0);
        organizadoresTxt.remove(0);

        for (String lineaUsuario : usuariosTxt) {

            String[] u = lineaUsuario.split("\\|");

            if (!u[7].equals("O"))
                continue;

            for (String lineaOrganizador : organizadoresTxt) {

                String[] o = lineaOrganizador.split("\\|");

                if (u[0].equals(o[0])) {

                    usuarios.add(new Organizador(
                            u[0],
                            u[1],
                            u[2],
                            u[3],
                            u[4],
                            u[5],
                            u[6],
                            null,
                            o[4],
                            o[5]));

                    break;
                }

            }

        }

    }
    // #endregion

    // #region Metodo de carga de Partidos
    public void cargarPartidos() {

        ArrayList<String> lineas = ManejoArchivos.LeeFichero("poo_proyecto_1p/src/main/resources/partidos.txt");

        lineas.remove(0); // Eliminar encabezado

        for (String linea : lineas) {

            String[] p = linea.split("\\|");

            ArrayList<Zona> zonas = new ArrayList<>();

            zonas.add(new Zona(
                    TipoZona.GENERAL,
                    Integer.parseInt(p[7])));

            zonas.add(new Zona(
                    TipoZona.PREFERENCIAL,
                    Integer.parseInt(p[8])));

            zonas.add(new Zona(
                    TipoZona.VIP,
                    Integer.parseInt(p[9])));

            Partido partido = new Partido(
                    p[0], // Código
                    p[1], // Local
                    p[2], // Visitante
                    LocalDate.parse(p[3]), // Fecha
                    p[4], // Estadio
                    p[5], // Ciudad
                    Integer.parseInt(p[6]), // Capacidad
                    FasePartido.valueOf(p[10]), // Fase
                    zonas);

            partidos.add(partido);

        }

    }
    // #endregion

    // #region Metodo de carga de Kits
    public void cargarKits() {

        ArrayList<String> lineas = ManejoArchivos.LeeFichero("poo_proyecto_1p/src/main/resources/kits.txt");

        lineas.remove(0);

        for (String linea : lineas) {

            String[] k = linea.split("\\|");

            ArrayList<Partido> partidosKit = new ArrayList<>();

            String[] codigos = k[3].split(",");

            for (String codigo : codigos) {

                Partido partido = buscarPartido(codigo);

                if (partido != null) {
                    partidosKit.add(partido);
                }

            }

            Kit kit = new Kit(
                    k[0],
                    k[1],
                    k[2],
                    partidosKit,
                    Double.parseDouble(k[4]),
                    Integer.parseInt(k[5]));

            kits.add(kit);

        }

    }
    // #endregion

    public void iniciarSesion() {
        System.out.print("Usuario: ");
        String us = sc.nextLine();
        System.out.println("Contraseña: ");
        String pw = sc.nextLine();
        Usuario u = autenticar(us, pw);
        if (u != null) {
            if (verificarIdentidad(u)) {
                System.out.println("Bienvenido, " + u.getNombreCompleto() + ".");
                mostrarMenu(u);
            } else {
                System.out.println("No se pudo verificar la identidad del usuario.");
            }
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    public Usuario autenticar(String usuario, String contraseña) {
        for (Usuario e : usuarios) {
            if (e.autenticar(usuario, contraseña)) {
                return e;
            }
        }
        return null;
    }

    public boolean verificarIdentidad(Usuario usuario) {

        if (usuario instanceof Aficionado) {
            Aficionado a = (Aficionado) usuario;

            System.out.println("Rol detectado: AFICIONADO");
            System.out.println("Bienvenido, " + a.getNombreCompleto());
            System.out.println("Celular registrado: " + a.getCelular());

            System.out.print("¿Este número de celular es correcto? (S/N): ");
            String respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("S")) {
                System.out.println("Identidad confirmada.");
                return true;
            } else {
                System.out.println("Verificación fallida.");
                System.out.println("Por motivos de seguridad se cerrará la sesión.");
                return false;
            }

        } else if (usuario instanceof Organizador) {

            Organizador o = (Organizador) usuario;

            System.out.println("Rol detectado: ORGANIZADOR");
            System.out.println("Bienvenido, " + o.getNombreCompleto());
            System.out.println("Empresa asignada: " + o.getEmpresaOrganizadora());

            System.out.print("¿Esta empresa es correcta? (S/N): ");
            String respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("S")) {
                System.out.println("Identidad confirmada.");
                return true;
            } else {
                System.out.println("Verificación fallida.");
                System.out.println("Por motivos de seguridad se cerrará la sesión.");
                return false;
            }

        }

        return false;
    }

    public void mostrarMenu(Usuario usuario) {
        if (usuario instanceof Aficionado) {
            boolean salir = false;
            Aficionado e = (Aficionado) usuario;

            while (!salir) {
                System.out.println("\n1. Consultar partidos\n2. Consultar mis entradas\n3. Comprar entradas\n4. Salir");
                int opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        mostrarPartidos();
                        break;
                    case 2:
                        e.consultarEntradas(compras);
                        break;
                    case 3:
                        crearCompra(e);
                        break;
                    case 4:
                        System.out.println("Cerrando sistema...");
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción incorrecta. Intente nuevamente.");
                }
            }
        } else {
            System.out.println("1. Consultar reportes\n2. Consultar entradas");
        }

    }

    public void mostrarKitDisp() {
        for (Kit k : kits) {
            if (k.disponibilidad()) {
                System.out.println(k);
            }
        }
    }

    // Sobrecarga de metodos
    public void mostrarPartidos() {
        for (Partido p : partidos) {
            p.mostrarInformacion();
        }
    }

    public void mostrarPartidos(Kit kit) {
        for (Partido p : kit.getPartidosIncluidos()) {
            p.mostrarInformacion();
        }
    }
    //

    public void mostrarPrecio(Compra compra) {
        System.out.println("Valor a pagar: $" + compra.getValorPagado());
    }

    // Buscar
    public Partido buscarPartido(String codigo) {
        for (Partido p : partidos) {
            if (p.getCodigoPartido().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    public Kit buscarKit(String codigo) {
        for (Kit k : kits) {
            if (k.getCodigoKit().equals(codigo)) {
                return k;
            }
        }
        return null;
    }

    //
    // Sobrecarga de metodos notificar
    public void notificar(Aficionado aficionado, Compra compra) {
        System.out.println("\n===== NOTIFICACIÓN =====");
        System.out.println("Para: " + aficionado.getCorreo());
        System.out.println("Estimado(a) " + aficionado.getNombreCompleto());
        System.out.println("Su compra se realizó exitosamente.");
        System.out.println("Código de compra: " + compra.getCodigoReferencia());
        System.out.println("Valor pagado: $" + compra.getValorPagado());
        System.out.println("========================\n");
    }

    public void notificar(Kit kit, Aficionado aficionado, Compra compra) {

        System.out.println("\n===== NOTIFICACIÓN =====");
        System.out.println("Para: " + aficionado.getCorreo());
        System.out.println("Estimado(a) " + aficionado.getNombreCompleto());
        System.out.println("Ha comprado el kit: " + kit.getNombre());
        System.out.println("Descripción: " + kit.getDescripcion());
        System.out.println("Código de compra: " + compra.getCodigoReferencia());
        System.out.println("Valor pagado: $" + compra.getValorPagado());
        System.out.println("========================\n");

    }

    public void notificar(Organizador organizador, Reporte reporte) {
        System.out.println("\n===== REPORTE =====");
        System.out.println("Para: " + organizador.getCorreo());
        System.out.println("Organizador: " + organizador.getNombreCompleto());

        System.out.println(reporte);

        System.out.println("===================\n");
    }

    //
    public void crearCompra(Aficionado a) {
        System.out.println("1. Comprar entrada\n2. Comprar kit");
        int opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1:
                mostrarPartidos();
                // Verificar si existe el partido
                Partido p = null;
                String codigo;
                while (p == null) {
                    System.out.println("Codigo del partido: ");
                    codigo = sc.nextLine();
                    p = buscarPartido(codigo);
                    if (p == null) {
                        System.out.println("Código incorrecto. Intente nuevamente.");
                    }
                }
                // Verificar si existe la zona
                Zona z = null;
                while (z == null) {
                    System.out.println("Escoja zona\n1. General\n2. Preferencia\n3. VIP");
                    int zona = sc.nextInt();
                    sc.nextLine();
                    TipoZona tipo = null;
                    switch (zona) {
                        case 1:
                            tipo = TipoZona.GENERAL;
                            break;
                        case 2:
                            tipo = TipoZona.PREFERENCIAL;
                            break;
                        case 3:
                            tipo = TipoZona.VIP;
                            break;
                        default:
                            System.out.println("Opcion invalida");
                            continue;
                    }
                    z = p.buscarZona(tipo);
                    if (z == null) {
                        System.out.println("Esa zona no existe para este partido.");
                    }
                }
                // En caso de que no hayan entradas
                if (z.getDisponible() == 0) {
                    System.out.println("No hay suficientes entradas.");
                    return;
                }
                int cantidad;
                while (true) {
                    System.out.print("Cantidad de entradas: ");
                    cantidad = sc.nextInt();
                    sc.nextLine();
                    if (cantidad <= 0) {
                        System.out.println("Debe ingresar una cantidad mayor que cero.");
                    } else if (cantidad > z.getDisponible()) {
                        System.out.println("Solo quedan " + z.getDisponible() + " entradas disponibles.");
                    } else {
                        break;
                    }
                }
                System.out.print("Ingrese el número de tarjeta: ");
                String tarjeta = sc.nextLine();
                Compra compra = a.comprarEntradas(p.getCodigoPartido(), z.getTipo(), cantidad, tarjeta);
                if (compra != null) {
                    compras.add(compra);
                    // Descontar entradas disponibles
                    z.setDisponible(z.getDisponible() - cantidad);
                    mostrarPrecio(compra);
                    notificar(a, compra);
                    System.out.println("Compra realizada exitosamente.");
                }

                break;

            case 2:
                mostrarKitDisp();
                Kit kit = null;
                while (kit == null) {
                    System.out.print("Ingrese el código del kit: ");
                    String codigoKit = sc.nextLine();
                    kit = buscarKit(codigoKit);
                    if (kit == null) {
                        System.out.println("Código incorrecto. Intente nuevamente.");
                    }
                }
                if (kit.getDisponibles() == 0) {
                    System.out.println("Lo sentimos, el kit está agotado.");
                    break;
                }

                int cantidadKit;
                while (true) {
                    System.out.print("Cantidad de Kit: ");
                    cantidadKit = sc.nextInt();
                    sc.nextLine();
                    if (cantidadKit <= 0) {
                        System.out.println("La cantidad debe ser mayor que cero.");
                    } else if (cantidadKit > kit.getDisponibles()) {
                        System.out.println("Solo quedan " + kit.getDisponibles() + " Kit disponibles.");
                    } else {
                        break;
                    }
                }

                System.out.print("Ingrese el número de tarjeta: ");
                String tarjetaKit = sc.nextLine();
                Compra compraKit = a.comprarEntradas(kit.getCodigoKit(), cantidadKit, tarjetaKit);
                if (compraKit != null) {
                    compras.add(compraKit);
                    kit.setDisponibles(kit.getDisponibles() - cantidadKit);
                    mostrarPrecio(compraKit);
                    notificar(kit, a, compraKit);
                    System.out.println("Compra realizada exitosamente.");
                }
                break;

            default:
                System.out.println("Opcion incorrecta");
        }

    }

}
