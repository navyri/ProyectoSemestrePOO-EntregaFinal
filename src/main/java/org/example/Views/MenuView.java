package org.example.Views;

import org.example.Models.Usuario;
import java.util.HashMap;
import java.util.Map;

public class MenuView {
    public static Map<Integer, String> mostrarMenuYObtenerOpciones(Usuario usuarioActivo) {
        int numeroOpcion = 1;
        Map<Integer, String> opciones = new HashMap<>();
        System.out.println("\nBIENVENIDO AL MENU DE GLOW UP");

        if (usuarioActivo == null) {
            System.out.println(numeroOpcion + ". Registrar usuario");
            opciones.put(numeroOpcion++, "registrarUsuario");
            System.out.println(numeroOpcion + ". Iniciar sesion");
            opciones.put(numeroOpcion++, "iniciarSesion");
        } else {
            // Si es administrador, mostrar "registro de productos"
            if (usuarioActivo.getRol().equalsIgnoreCase("Administrador")) {
                System.out.println(numeroOpcion + ". Registro de productos");
                opciones.put(numeroOpcion++, "registroProductos");
            }
            System.out.println(numeroOpcion + ". Listar productos");
            opciones.put(numeroOpcion++, "listarProductos");
            System.out.println(numeroOpcion + ". Registrar compra");
            opciones.put(numeroOpcion++, "registrarCompra");
            System.out.println(numeroOpcion + ". Cerrar sesion");
            opciones.put(numeroOpcion++, "cerrarSesion");
        }

        System.out.println("0. Salir del programa");
        opciones.put(0, "salir");
        System.out.print("Ingrese un numero en base a lo que desee realizar: ");
        return opciones;
    }
}
