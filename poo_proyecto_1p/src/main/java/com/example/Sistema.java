package com.example;
import java.util.ArrayList;
import java.util.Scanner;
import com.example.enums.TipoZona;

public class Sistema {
    public ArrayList<Usuario> usuarios;
    public ArrayList<Partido> partidos;
    public ArrayList<Kit> Kit;
    public ArrayList<Compra> compras;
    public Scanner sc=new Scanner(System.in);

    public void iniciarSesion(){
        System.out.print("Usuario: ");
        String us=sc.nextLine();
        System.out.println("Contraseña: ");
        String pw=sc.nextLine();
        Usuario u=autenticar(us, pw);
            if (u!=null){
                System.out.println("Bienvenido!!"+u.getUsuario());
                mostrarMenu(u);
            }else{
                System.out.println("Usuario o contraseña incorrectos.");
            }
    }
    public Usuario autenticar(String usuario, String contraseña){
        for(Usuario e: usuarios){
            if(e.getUsuario().equals(usuario) && (e.getContraseña().equals(contraseña))) {
                return e;
            }
        }
        return null;
    }
   /*public boolean verificarIdentidad(Usuario usuario){
   }*/
    public void mostrarMenu(Usuario usuario){
        if(usuario instanceof Aficionado){
            System.out.println("1. Consultar partidos\n2. Consultar mis entradas\n3. Comprar entradas\n4. Salir");
            int opcion=sc.nextInt();
            switch(opcion){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    Aficionado e=(Aficionado)usuario;
                    crearCompra(e);
                    break;
                case 4:
                    System.out.println("Cerrando sistema...");
            }
        }else{
            System.out.println("1. Consultar reportes\n2. Consultar entradas");
        }

    }
    public void mostrarKitDisp(){
        for(Kit k:Kit){
            if(k.disponibilidad()){
                System.out.println(k);
            }
        }
    }
    public boolean validarDisp(String codigo){
        for(Kit k:Kit){
            if(k.getCodigoKit().equals(codigo) && k.disponibilidad()){
                return true;
            }
        }
        for(Partido p:partidos){
            if(p.getCodigoPartido().equals(codigo) && p.getCapacidad()>0){
                return true;
            }
        }
        return false;
    }
    // Sobrecarga de metodos
    public void mostrarPartidos(){
        for(Partido p:partidos){
            p.mostrarInformacion();
        }
    }
    public void mostrarPartidos(Kit kit){
        for(Partido p:kit.getPartidosIncluidos()){
            p.mostrarInformacion();
        }
    }
    //

    public void mostrarPrecio(Compra compra){
        System.out.println("Valor a pagar: $"+compra.getValorPagado());
    }

    // Buscar
    public Partido buscarPartido(String codigo){
        for(Partido p:partidos){
            if(p.getCodigoPartido().equals(codigo)){
                return p;
            }
        }
        return null;
    }
    public Kit buscarKit(String codigo){
        for(Kit k:Kit){
            if(k.getCodigoKit().equals(codigo)){
                return k;
            }
        }
        return null;
    }
    //
    // Sobrecarga de metodos
    public void notificar(Aficionado aficionado, Compra compra){
        System.out.println(aficionado+"ha hecho una compra!");
    }
    public void notificar(Kit kit, Aficionado aficionado, Compra compra){
        System.out.println(aficionado+"ha comprado el kit "+kit.getNombre());
    }
    public void notificar(Organizador organizador, String reporte){
        System.out.println("...");
    }
    // 
    public void crearCompra(Aficionado a){
        System.out.println("1. Comprar entrada\n2. Comprar kit");
        int opcion=sc.nextInt();
        sc.nextLine();
        switch(opcion){
            case 1:
                mostrarPartidos();
                // Verificar si existe el partido
                Partido p=null;
                String codigo;
                while(p==null){
                    System.out.println("Codigo del partido: ");
                    codigo=sc.nextLine();
                    p=buscarPartido(codigo);
                    if(p==null){
                        System.out.println("Código incorrecto. Intente nuevamente.");
                    }
                }
                // Verificar si existe la zona
                Zona z=null;
                while(z==null){
                    System.out.println("Escoja zona\n1. General\n2.Preferencia\n3. VIP");
                    int zona=sc.nextInt();
                    sc.nextLine();
                    TipoZona tipo=null;
                    switch(zona){
                        case 1:
                            tipo=TipoZona.GENERAL;
                            break;
                        case 2:
                            tipo=TipoZona.PREFERENCIA;
                            break;
                        case 3:
                            tipo=TipoZona.VIP;
                            break;
                        default:
                            System.out.println("Opcion invalida");
                            continue;
                    }
                    if (z==null){
                        System.out.println("Esa zona no existe para este partido.");
                    }
                }
                // En caso de que no hayan entradas
                if(z.getDisponible()==0){
                        System.out.println("No hay suficientes entradas.");    
                        return;
                    }
                int cantidad;
                while (true) {
                    System.out.print("Cantidad de entradas: ");
                    cantidad = sc.nextInt();
                    sc.nextLine();
                    if (cantidad<=0){
                        System.out.println("Debe ingresar una cantidad mayor que cero.");
                    }else if(cantidad>z.getDisponible()){
                        System.out.println("Solo quedan "+z.getDisponible()+" entradas disponibles.");
                    }else{
                        break;
                    }
                }
                System.out.print("Ingrese el número de tarjeta: ");
                String tarjeta = sc.nextLine();
                Compra compra = a.comprEntradas(p.getCodigoPartido(),z.getTipo(),cantidad,tarjeta);
                if (compra != null) {
                    compras.add(compra);
                // Descontar entradas disponibles
                    z.setDisponible(z.getDisponible()-cantidad);
                    mostrarPrecio(compra);
                    notificar(a, compra);
                    System.out.println("Compra realizada exitosamente.");
                }

            break;




            case 2:
                mostrarKitDisp();
                Kit kit=null;
                while (kit==null) {
                    System.out.print("Ingrese el código del kit: ");
                    String codigoKit=sc.nextLine();
                    kit=buscarKit(codigoKit);
                    if(kit==null) {
                        System.out.println("Código incorrecto. Intente nuevamente.");
                    }
                }
                if(kit.getDisponible()==0){
                    System.out.println("Lo sentimos, el kit está agotado.");
                    break;
                }

                int cantidadKit;
                while(true){
                    System.out.print("Cantidad de Kit: ");
                    cantidadKit=sc.nextInt();
                    sc.nextLine();
                    if(cantidadKit<=0) {
                        System.out.println("La cantidad debe ser mayor que cero.");
                    }else if(cantidadKit>kit.getDisponible()){
                        System.out.println("Solo quedan " + kit.getDisponible() + " Kit disponibles.");
                    }
                    else{
                        break;
                    }
                }

                System.out.print("Ingrese el número de tarjeta: ");
                String tarjetaKit=sc.nextLine();
                Compra compraKit = a.comprEntradas(kit.getCodigoKit(),cantidadKit,tarjetaKit);
                if(compraKit!=null){
                    compras.add(compraKit);
                    kit.setDisponible(kit.getDisponible()-cantidadKit);
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
