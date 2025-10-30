package org.example;

import org.example.Models.*;
import org.example.Services.*;
import org.example.Views.*;

import java.util.*;

public class Main {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Producto> productos = new ArrayList<>();
    private static List<Compra> compras = new ArrayList<>();
    private static Usuario usuarioActivo = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            int opcion = -1;
            Map<Integer, String> opciones;

            while (true) {
                opciones = MenuView.mostrarMenuYObtenerOpciones(usuarioActivo);
                String entrada = scanner.nextLine();
                try {
                    opcion = Integer.parseInt(entrada);
                    break; // Sale solo si la entrada es numerica
                } catch (NumberFormatException e) {
                    System.out.println("La opcion digitada no es valida. Por favor, ingrese un numero.");
                }
            }

            String accion = opciones.getOrDefault(opcion, "");
            switch (accion) {
                case "registrarUsuario":
                    UsuarioService.registrarUsuario(usuarios, scanner);
                    break;
                case "iniciarSesion":
                    usuarioActivo = UsuarioService.iniciarSesion(usuarios, scanner);
                    break;
                case "gestionarEsclavos":
                    if (usuarioActivo instanceof Duenia) {
                        DueniaView.gestionarEsclavos(
                                new RegistroEsclavosService(),
                                scanner,
                                (Duenia) usuarioActivo);
                    } else {
                        System.out.println("Acceso denegado.");
                    }
                    break;
                case "gestionarFabricas":
                    if (usuarioActivo instanceof Duenia) {
                        DueniaView.gestionarFabricas(
                                new FabricaService(),
                                scanner,
                                (Duenia) usuarioActivo);
                    } else {
                        System.out.println("Acceso denegado.");
                    }
                    break;
                case "gestionarProductos":
                    if (usuarioActivo instanceof Duenia) {
                        DueniaView.gestionarProductos(
                                productos,
                                scanner,
                                (Duenia) usuarioActivo
                        );
                    } else {
                        System.out.println("Acceso denegado.");
                    }
                    break;

                case "gestionarUsuarios":
                    if (usuarioActivo instanceof Duenia) {
                        DueniaView.gestionarUsuarios(
                                usuarios,
                                scanner
                        );
                    } else {
                        System.out.println("Acceso denegado.");
                    }
                    break;
                case "registroProductos":
                    ProductoService.registrarProducto(productos, scanner, usuarioActivo);
                    break;
                case "listarProductos":
                    ProductoService.listarProductos(productos);
                    break;
                case "registrarCompra":
                    CompraService.registrarCompra(compras, productos, usuarioActivo, scanner);
                    break;
                case "suspenderUsuario":
                    AdministradorUsuarioService.suspenderUsuario(usuarios, scanner, usuarioActivo);
                    break;
                case "reactivarUsuario":
                    AdministradorUsuarioService.reactivarUsuario(usuarios, scanner, usuarioActivo);
                    break;
                case "cerrarSesion":
                    usuarioActivo = UsuarioService.cerrarSesion(usuarioActivo);
                    break;
                case "salir": // Salir del menu (termina la ejecucion del programa)
                    salir = true;
                    break;
                default:
                    System.out.println("La opcion digitada no es valida");
                    break;
            }
        }
        scanner.close();
    }
}
