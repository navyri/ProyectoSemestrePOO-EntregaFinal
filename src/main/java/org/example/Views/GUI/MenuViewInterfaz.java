package org.example.Views.GUI;

import org.example.Models.AdministradorUsuario;
import org.example.Models.Usuario;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class MenuViewInterfaz {

    public static Map<Integer, String> mostrarMenuYObtenerOpciones(Usuario usuarioActivo) {
        int numeroOpcion = 1;
        Map<Integer, String> opciones = new HashMap<>();
        String menu = "BIENVENIDO AL MENÚ DE GLOW UP\n\n";

        if (usuarioActivo == null) {
            menu += numeroOpcion + ". Registrar usuario\n";
            opciones.put(numeroOpcion++, "registrarUsuario");
            menu += numeroOpcion + ". Iniciar sesion\n";
            opciones.put(numeroOpcion++, "iniciarSesion");
        } else {
            String rolSinEspacios = usuarioActivo.getRol().replaceAll("\\s", "").toLowerCase();
            switch (rolSinEspacios) {
                case "duenia":
                case "dueña":
                    menu += "MENU SUPREMO DE LA DUEÑA CABRITA SAKURA\n";
                    menu += numeroOpcion + ". Gestionar esclavos\n";
                    opciones.put(numeroOpcion++, "gestionarEsclavos");
                    menu += numeroOpcion + ". Gestionar fábricas\n";
                    opciones.put(numeroOpcion++, "gestionarFabricas");
                    menu += numeroOpcion + ". Gestionar productos\n";
                    opciones.put(numeroOpcion++, "gestionarProductos");
                    menu += numeroOpcion + ". Gestionar usuarios\n";
                    opciones.put(numeroOpcion++, "gestionarUsuarios");
                    menu += numeroOpcion + ". Registrar compra\n";
                    opciones.put(numeroOpcion++, "registrarCompra");
                    menu += numeroOpcion + ". Cerrar sesión\n";
                    opciones.put(numeroOpcion++, "cerrarSesion");
                    break;

                case "administradorcontenido":
                    menu += numeroOpcion + ". Registro de productos\n";
                    opciones.put(numeroOpcion++, "registroProductos");
                    menu += numeroOpcion + ". Listar productos\n";
                    opciones.put(numeroOpcion++, "listarProductos");
                    menu += numeroOpcion + ". Registrar compra\n";
                    opciones.put(numeroOpcion++, "registrarCompra");
                    menu += numeroOpcion + ". Cerrar sesion\n";
                    opciones.put(numeroOpcion++, "cerrarSesion");
                    break;

                case "administradorusuario":
                    AdministradorUsuario adminUser = (AdministradorUsuario) usuarioActivo;
                    if (adminUser.getNivelAcceso()) {
                        menu += numeroOpcion + ". Suspender usuario\n";
                        opciones.put(numeroOpcion++, "suspenderUsuario");
                        menu += numeroOpcion + ". Reactivar usuario\n";
                        opciones.put(numeroOpcion++, "reactivarUsuario");
                        menu += numeroOpcion + ". Listar usuarios\n";
                        opciones.put(numeroOpcion++, "listarUsuarios");
                    }
                    menu += numeroOpcion + ". Listar productos\n";
                    opciones.put(numeroOpcion++, "listarProductos");
                    menu += numeroOpcion + ". Registrar compra\n";
                    opciones.put(numeroOpcion++, "registrarCompra");
                    menu += numeroOpcion + ". Cerrar sesion\n";
                    opciones.put(numeroOpcion++, "cerrarSesion");
                    break;

                default:
                    menu += numeroOpcion + ". Listar productos\n";
                    opciones.put(numeroOpcion++, "listarProductos");
                    menu += numeroOpcion + ". Registrar compra\n";
                    opciones.put(numeroOpcion++, "registrarCompra");
                    menu += numeroOpcion + ". Cerrar sesion\n";
                    opciones.put(numeroOpcion++, "cerrarSesion");
            }
        }

        menu += "0. Salir del programa\n";

        int opcionSeleccionada = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                String entrada = JOptionPane.showInputDialog(null, menu + "\nIngrese un numero para continuar:");
                if (entrada == null) {
                    opcionSeleccionada = 0;
                    entradaValida = true;
                } else {
                    opcionSeleccionada = Integer.parseInt(entrada.trim());
                    entradaValida = true;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un numero válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            }
        }

        opciones.put(-1, String.valueOf(opcionSeleccionada));
        return opciones;
    }
}
