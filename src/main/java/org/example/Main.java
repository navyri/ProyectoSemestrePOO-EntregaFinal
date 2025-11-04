package org.example;

import org.example.Models.*;
import org.example.Services.*;
import org.example.Views.*;
import org.example.Views.GUI.DueniaViewInterfaz;
import org.example.Views.GUI.MenuViewInterfaz;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.util.*;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner run(
            AdministradorUsuarioService adminUsuarioService,
            UsuarioService usuarioService,
            RegistroEsclavosService registroEsclavosService,
            FabricaService fabricaService,
            ProductoService productoService,
            CompraService compraService) {

        return args -> {
            Scanner scanner = new Scanner(System.in);
            boolean salir = false;
            Usuario usuarioActivo = null;

            // true si quieremos usar interfaz gráfica
            boolean usarInterfaz = true;

            while (!salir) {
                int opcion = -1;
                Map<Integer, String> opciones;

                if (usarInterfaz) {
                    // Mostrar menú por interfaz
                    int opcionMenu = MenuViewInterfaz.mostrarMenuYObtenerOpcion(usuarioActivo);
                    if (opcionMenu == 0) { // o -1 si así lo prefieres para cancelar
                        mostrarMensaje("Saliendo del programa.", usarInterfaz);
                        break;
                    }
                    opcion = opcionMenu;
                    opciones = MenuViewInterfaz.getUltimasOpciones();
                } else {
                    // Mostrar menú por consola
                    opciones = MenuView.mostrarMenuYObtenerOpciones(usuarioActivo);
                    try {
                        String entrada = scanner.nextLine();
                        opcion = Integer.parseInt(entrada);
                    } catch (NumberFormatException e) {
                        System.out.println("La opcion digitada no es valida. Por favor, ingrese un numero.");
                        continue;
                    }
                }

                String accion = opciones.getOrDefault(opcion, "");

                try {
                    switch (accion) {
                        case "registrarUsuario":
                            if (usarInterfaz)
                                usuarioService.registrarUsuarioInterfaz();
                            else
                                usuarioService.registrarUsuario(scanner);
                            break;

                        case "iniciarSesion":
                            if (usarInterfaz)
                                usuarioActivo = usuarioService.iniciarSesionInterfaz();
                            else
                                usuarioActivo = usuarioService.iniciarSesion(scanner);
                            break;

                        case "gestionarEsclavos":
                            if (usuarioActivo instanceof Duenia) {
                                if (usarInterfaz)
                                    DueniaViewInterfaz.gestionarEsclavos(registroEsclavosService, fabricaService, (Duenia) usuarioActivo);
                                else
                                    DueniaView.gestionarEsclavos(registroEsclavosService, fabricaService, scanner, (Duenia) usuarioActivo);
                            } else {
                                mostrarMensaje("Acceso denegado.", usarInterfaz);
                            }
                            break;

                        case "gestionarFabricas":
                            if (usuarioActivo instanceof Duenia) {
                                if (usarInterfaz)
                                    DueniaViewInterfaz.gestionarFabricas(fabricaService, (Duenia) usuarioActivo);
                                else
                                    DueniaView.gestionarFabricas(fabricaService, scanner, (Duenia) usuarioActivo);
                            } else {
                                mostrarMensaje("Acceso denegado.", usarInterfaz);
                            }
                            break;

                        case "gestionarProductos":
                            if (usuarioActivo instanceof Duenia) {
                                if (usarInterfaz)
                                    DueniaViewInterfaz.gestionarProductos(productoService, (Duenia) usuarioActivo);
                                else
                                    DueniaView.gestionarProductos(productoService, scanner, (Duenia) usuarioActivo);
                            } else {
                                mostrarMensaje("Acceso denegado.", usarInterfaz);
                            }
                            break;

                        case "gestionarUsuarios":
                            if (usuarioActivo instanceof Duenia) {
                                if (usarInterfaz)
                                    DueniaViewInterfaz.gestionarUsuarios(usuarioService);
                                else
                                    DueniaView.gestionarUsuarios(usuarioService, scanner);
                            } else {
                                mostrarMensaje("Acceso denegado.", usarInterfaz);
                            }
                            break;

                        case "registroProductos":
                            if (usarInterfaz)
                                productoService.registrarProductoInterfaz(usuarioActivo);
                            else
                                productoService.registrarProducto(scanner, usuarioActivo);
                            break;

                        case "listarProductos":
                            if (usarInterfaz)
                                productoService.listarProductosInterfaz();
                            else
                                productoService.listarProductos();
                            break;
                        case "listarUsuarios":
                            if (usarInterfaz)
                                usuarioService.listarUsuariosInterfaz();
                            else
                                usuarioService.listarUsuarios();
                            break;
                        case "registrarCompra":
                            if (usarInterfaz)
                                compraService.registrarCompraInterfaz(usuarioActivo);
                            else
                                compraService.registrarCompra(scanner, usuarioActivo);
                            break;

                        case "suspenderUsuario":
                            if (usarInterfaz)
                                adminUsuarioService.suspenderUsuarioInterfaz(usuarioActivo);
                            else
                                adminUsuarioService.suspenderUsuario(scanner, usuarioActivo);
                            break;

                        case "reactivarUsuario":
                            if (usarInterfaz)
                                adminUsuarioService.reactivarUsuarioInterfaz(usuarioActivo);
                            else
                                adminUsuarioService.reactivarUsuario(scanner, usuarioActivo);
                            break;

                        case "cerrarSesion":
                            usuarioActivo = usuarioService.cerrarSesion(usuarioActivo);
                            mostrarMensaje("Sesión cerrada correctamente.", usarInterfaz);
                            break;
                        case "salir":
                            salir = true;
                            break;

                        default:
                            mostrarMensaje("La opción digitada no es válida.", usarInterfaz);
                            break;
                    }
                } catch (Exception e) {
                    mostrarMensaje("Ocurrio un error: " + e.getMessage(), usarInterfaz);
                }
            }

            scanner.close();
            mostrarMensaje("¡Gracias por usar Glow Up! El programa ha terminado correctamente.", usarInterfaz);
            System.exit(0);
        };
    }

    private void mostrarMensaje(String mensaje, boolean interfaz) {
        if (interfaz) {
            JOptionPane.showMessageDialog(null, mensaje);
        } else {
            System.out.println(mensaje);
        }
    }
}
