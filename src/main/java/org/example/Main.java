package org.example;

import org.example.Models.*;
import org.example.Services.*;
import org.example.Views.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner run(AdministradorUsuarioService adminUsuarioService, UsuarioService usuarioService, RegistroEsclavosService registroEsclavosService, FabricaService fabricaService, ProductoService productoService, CompraService compraService) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            boolean salir = false;
            Usuario usuarioActivo = null;

            while (!salir) {
                int opcion = -1;
                Map<Integer, String> opciones;

                while (true) {
                    opciones = MenuView.mostrarMenuYObtenerOpciones(usuarioActivo);
                    String entrada = scanner.nextLine();
                    try {
                        opcion = Integer.parseInt(entrada);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("La opcion digitada no es valida. Por favor, ingrese un numero.");
                    }
                }

                String accion = opciones.getOrDefault(opcion, "");
                switch (accion) {
                    case "registrarUsuario":
                        usuarioService.registrarUsuario(scanner);
                        break;
                    case "iniciarSesion":
                        usuarioActivo = usuarioService.iniciarSesion(scanner);
                        break;
                    case "gestionarEsclavos":
                        if (usuarioActivo instanceof Duenia) {
                            DueniaView.gestionarEsclavos(registroEsclavosService, fabricaService, scanner, (Duenia) usuarioActivo);
                        } else {
                            System.out.println("Acceso denegado.");
                        }
                        break;
                    case "gestionarFabricas":
                        if (usuarioActivo instanceof Duenia) {
                            DueniaView.gestionarFabricas(fabricaService, scanner, (Duenia) usuarioActivo);
                        } else {
                            System.out.println("Acceso denegado.");
                        }
                        break;
                    case "gestionarProductos":
                        if (usuarioActivo instanceof Duenia) {
                            DueniaView.gestionarProductos(productoService, scanner, (Duenia) usuarioActivo);
                        } else {
                            System.out.println("Acceso denegado.");
                        }
                        break;
                    case "gestionarUsuarios":
                        if (usuarioActivo instanceof Duenia) {
                            DueniaView.gestionarUsuarios(usuarioService, scanner);
                        } else {
                            System.out.println("Acceso denegado.");
                        }
                        break;
                    case "registroProductos":
                        productoService.registrarProducto(scanner, usuarioActivo);
                        break;
                    case "listarProductos":
                        productoService.listarProductos();
                        break;
                    case "listarUsuarios":
                        usuarioService.listarUsuarios();
                        break;
                    case "registrarCompra":
                        compraService.registrarCompra(scanner, usuarioActivo);
                        break;
                    case "suspenderUsuario":
                        adminUsuarioService.suspenderUsuario(scanner, usuarioActivo);
                        break;
                    case "reactivarUsuario":
                        adminUsuarioService.reactivarUsuario(scanner, usuarioActivo);
                        break;
                    case "cerrarSesion":
                        usuarioActivo = usuarioService.cerrarSesion(usuarioActivo);
                        break;
                    case "salir":
                        salir = true;
                        break;
                    default:
                        System.out.println("La opcion digitada no es valida");
                        break;
                }
            }
            scanner.close();
            System.out.println("¡Gracias por usar Glow Up! El programa ha terminado correctamente.");
            System.exit(0);
        };
    }
}