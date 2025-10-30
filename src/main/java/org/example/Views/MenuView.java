package org.example.Views;

import org.example.Models.AdministradorUsuario;
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
            String rolSinEspacios = usuarioActivo.getRol().replaceAll("\\s", "").toLowerCase();

            // Opciones exclusivas para la Dueña
            if (rolSinEspacios.equals("duenia") || rolSinEspacios.equals("dueña")) {
                System.out.println("MENU SUPREMO DE LA DUEÑA CABRITA SAKURA");
                System.out.println(numeroOpcion + ". Gestionar esclavos");
                opciones.put(numeroOpcion++, "gestionarEsclavos");
                System.out.println(numeroOpcion + ". Gestionar fabricas");
                opciones.put(numeroOpcion++, "gestionarFabricas");
                System.out.println(numeroOpcion + ". Gestionar productos");
                opciones.put(numeroOpcion++, "gestionarProductos");
                System.out.println(numeroOpcion + ". Gestionar usuarios");
                opciones.put(numeroOpcion++, "gestionarUsuarios");
                System.out.println(numeroOpcion + ". Registrar compra");
                opciones.put(numeroOpcion++, "registrarCompra");
                System.out.println(numeroOpcion + ". Cerrar sesion");
                opciones.put(numeroOpcion++, "cerrarSesion");
            } else if (rolSinEspacios.equals("administradorcontenido")) {
                System.out.println(numeroOpcion + ". Registro de productos");
                opciones.put(numeroOpcion++, "registroProductos");
                System.out.println(numeroOpcion + ". Listar productos");
                opciones.put(numeroOpcion++, "listarProductos");
                System.out.println(numeroOpcion + ". Registrar compra");
                opciones.put(numeroOpcion++, "registrarCompra");
                System.out.println(numeroOpcion + ". Cerrar sesion");
                opciones.put(numeroOpcion++, "cerrarSesion");
            } else if (rolSinEspacios.equals("administradorusuario")) {
                AdministradorUsuario adminUser = (AdministradorUsuario) usuarioActivo;
                if (adminUser.getNivelAcceso() == 1 || adminUser.getNivelAcceso() == 3) {
                    System.out.println(numeroOpcion + ". Suspender usuario");
                    opciones.put(numeroOpcion++, "suspenderUsuario");
                }
                if (adminUser.getNivelAcceso() == 2 || adminUser.getNivelAcceso() == 3) {
                    System.out.println(numeroOpcion + ". Reactivar usuario");
                    opciones.put(numeroOpcion++, "reactivarUsuario");
                }
                System.out.println(numeroOpcion + ". Listar productos");
                opciones.put(numeroOpcion++, "listarProductos");
                System.out.println(numeroOpcion + ". Registrar compra");
                opciones.put(numeroOpcion++, "registrarCompra");
                System.out.println(numeroOpcion + ". Cerrar sesion");
                opciones.put(numeroOpcion++, "cerrarSesion");
            } else {
                System.out.println(numeroOpcion + ". Listar productos");
                opciones.put(numeroOpcion++, "listarProductos");
                System.out.println(numeroOpcion + ". Registrar compra");
                opciones.put(numeroOpcion++, "registrarCompra");
                System.out.println(numeroOpcion + ". Cerrar sesion");
                opciones.put(numeroOpcion++, "cerrarSesion");
            }
        }

        System.out.println("0. Salir del programa");
        opciones.put(0, "salir");
        System.out.print("Ingrese un numero en base a lo que desee realizar: ");
        return opciones;
    }
}
