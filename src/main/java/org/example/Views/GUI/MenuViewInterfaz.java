package org.example.Views.GUI;

import org.example.Models.AdministradorUsuario;
import org.example.Models.Usuario;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class MenuViewInterfaz {

    private static Map<Integer, String> ultimasOpciones = new HashMap<>();

    public static int mostrarMenuYObtenerOpcion(Usuario usuarioActivo) {
        int numeroOpcion = 1;
        ultimasOpciones = new HashMap<>();
        String menu = "BIENVENIDO AL MENÚ DE GLOW UP\n\n";

        if (usuarioActivo == null) {
            menu += numeroOpcion + ". Registrar usuario\n";
            ultimasOpciones.put(numeroOpcion++, "registrarUsuario");
            menu += numeroOpcion + ". Iniciar sesion\n";
            ultimasOpciones.put(numeroOpcion++, "iniciarSesion");
        } else {
            String rolSinEspacios = usuarioActivo.getRol().replaceAll("\\s", "").toLowerCase();
            switch (rolSinEspacios) {
                case "duenia":
                case "dueña":
                    menu += "MENU SUPREMO DE LA DUEÑA CABRITA SAKURA\n";
                    menu += numeroOpcion + ". Gestionar esclavos\n";
                    ultimasOpciones.put(numeroOpcion++, "gestionarEsclavos");
                    menu += numeroOpcion + ". Gestionar fábricas\n";
                    ultimasOpciones.put(numeroOpcion++, "gestionarFabricas");
                    menu += numeroOpcion + ". Gestionar productos\n";
                    ultimasOpciones.put(numeroOpcion++, "gestionarProductos");
                    menu += numeroOpcion + ". Gestionar usuarios\n";
                    ultimasOpciones.put(numeroOpcion++, "gestionarUsuarios");
                    menu += numeroOpcion + ". Registrar compra\n";
                    ultimasOpciones.put(numeroOpcion++, "registrarCompra");
                    menu += numeroOpcion + ". Cerrar sesión\n";
                    ultimasOpciones.put(numeroOpcion++, "cerrarSesion");
                    break;

                case "administradorcontenido":
                    menu += numeroOpcion + ". Registro de productos\n";
                    ultimasOpciones.put(numeroOpcion++, "registroProductos");
                    menu += numeroOpcion + ". Listar productos\n";
                    ultimasOpciones.put(numeroOpcion++, "listarProductos");
                    menu += numeroOpcion + ". Registrar compra\n";
                    ultimasOpciones.put(numeroOpcion++, "registrarCompra");
                    menu += numeroOpcion + ". Cerrar sesion\n";
                    ultimasOpciones.put(numeroOpcion++, "cerrarSesion");
                    break;

                case "administradorusuario":
                    AdministradorUsuario adminUser = (AdministradorUsuario) usuarioActivo;
                    if (adminUser.getNivelAcceso()) {
                        menu += numeroOpcion + ". Suspender usuario\n";
                        ultimasOpciones.put(numeroOpcion++, "suspenderUsuario");
                        menu += numeroOpcion + ". Reactivar usuario\n";
                        ultimasOpciones.put(numeroOpcion++, "reactivarUsuario");
                        menu += numeroOpcion + ". Listar usuarios\n";
                        ultimasOpciones.put(numeroOpcion++, "listarUsuarios");
                    }
                    menu += numeroOpcion + ". Listar productos\n";
                    ultimasOpciones.put(numeroOpcion++, "listarProductos");
                    menu += numeroOpcion + ". Registrar compra\n";
                    ultimasOpciones.put(numeroOpcion++, "registrarCompra");
                    menu += numeroOpcion + ". Cerrar sesion\n";
                    ultimasOpciones.put(numeroOpcion++, "cerrarSesion");
                    break;

                default:
                    menu += numeroOpcion + ". Listar productos\n";
                    ultimasOpciones.put(numeroOpcion++, "listarProductos");
                    menu += numeroOpcion + ". Registrar compra\n";
                    ultimasOpciones.put(numeroOpcion++, "registrarCompra");
                    menu += numeroOpcion + ". Cerrar sesion\n";
                    ultimasOpciones.put(numeroOpcion++, "cerrarSesion");
            }
        }

        menu += "0. Salir del programa\n";

        int opcionSeleccionada = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                String entrada = JOptionPane.showInputDialog(null, menu + "\nIngrese un numero para continuar:");
                if (entrada == null) {
                    opcionSeleccionada = 0; // cancelar es salir
                    entradaValida = true;
                } else {
                    opcionSeleccionada = Integer.parseInt(entrada.trim());
                    entradaValida = true;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un numero válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            }
        }

        return opcionSeleccionada;
    }

    public static Map<Integer, String> getUltimasOpciones() {
        return ultimasOpciones;
    }

}
