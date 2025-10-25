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
            Map<Integer, String> opciones = MenuView.mostrarMenuYObtenerOpciones(usuarioActivo);
            int opcion = scanner.nextInt();
            scanner.nextLine();

            String accion = opciones.getOrDefault(opcion, "");
            switch (accion) {
                case "registrarUsuario":
                    UsuarioService.registrarUsuario(usuarios, scanner);
                    break;
                case "iniciarSesion":
                    usuarioActivo = UsuarioService.iniciarSesion(usuarios, scanner);
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
