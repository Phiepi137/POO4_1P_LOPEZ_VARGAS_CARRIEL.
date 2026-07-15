package com.example;

import java.util.ArrayList;
import java.util.Scanner;
import com.example.enums.TipoZona;

public class Sistema {
    private ArrayList<Usuario> usuarios;
    // private ArrayList<Aficionado> aficionados;
    // private ArrayList<Organizador> organizadores;
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

    // Leer Archivos
    public void cargarDatos() {

        usuarios = ManejoArchivos.leerUsuarios("/workspaces/POO4_1P_LOPEZ_VARGAS_CARRIEL./poo_proyecto_1p/src/resources/usuarios.txt");
        ManejoArchivos.leerAficionados("/workspaces/POO4_1P_LOPEZ_VARGAS_CARRIEL./poo_proyecto_1p/src/resources/aficionados.txt",usuarios);
        ManejoArchivos.leerOrganizadores("/workspaces/POO4_1P_LOPEZ_VARGAS_CARRIEL./poo_proyecto_1p/src/resources/organizadores.txt", usuarios);
        partidos = ManejoArchivos.leerPartidos("/workspaces/POO4_1P_LOPEZ_VARGAS_CARRIEL./poo_proyecto_1p/src/resources/partidos.txt");
        kits = ManejoArchivos.leerKits("/workspaces/POO4_1P_LOPEZ_VARGAS_CARRIEL./poo_proyecto_1p/src/resources/kits.txt", partidos);
System.out.println("Partidos cargados: " + partidos.size());
System.out.println("Kits cargados: " + kits.size());
    }

    public void iniciarSesion() {
        System.out.println("Bienvenido al Sistema");
        System.out.println("--------------------------");
        System.out.print("Usuario: ");
        String us = sc.nextLine();
        System.out.print("Contraseña: ");
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
            System.out.println("--------------------------");
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
                try{
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
                        iniciarSesion();
                        break;
                    default:
                        System.out.println("Opción incorrecta. Intente nuevamente.");
                }
                }catch(Exception ex1){
                    System.out.println("Debe ingresar un número válido.");
                    sc.nextLine();
                }
            }
        } else {
            boolean salir = false;
            Organizador or = (Organizador) usuario;
            while (!salir) {
            try{
            System.out.println("1. Consultar entradas\n2. Generar reportes\n3. Salir");
            int opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        or.consultarEntradas(compras);
                        break;
                    case 2:
                        or.generarReporte(compras);
                        break;
                    case 3:
                        System.out.println("Cerrando sistema...");
                        salir = true;
                        iniciarSesion();
                        break;
                    default:
                        System.out.println("Opción incorrecta. Intente nuevamente.");
                }
                }catch(Exception ex1){
                    System.out.println("Debe ingresar un número válido.");
                    sc.nextLine();
                }
                 }
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
                    compra.setValorPagado(cantidad*z.getPrecio());
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
                    compraKit.setValorPagado(cantidadKit*kit.getPrecio());
                }
                break;

            default:
                System.out.println("Opcion incorrecta");
        }

    }

}
