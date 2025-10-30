package org.example.Views;

import org.example.Models.*;
import org.example.Services.ProductoService;
import org.example.Services.RegistroEsclavosService;
import org.example.Services.FabricaService;
import org.example.Services.UsuarioService;

import java.util.Scanner;
import java.util.List;
import java.util.Date;
import java.util.UUID;

public class DueniaView {
    public static void menuPrincipalDuenia(
            RegistroEsclavosService esclavosService,
            FabricaService fabricaService,
            ProductoService productoService,
            UsuarioService usuarioService,
            List<Producto> productos,
            List<Usuario> usuarios,
            Scanner scanner,
            Duenia duenia
    ) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nMENU SUPREMO DE LA DUEÑA CABRITA SAKURA");
            System.out.println("1. Gestionar esclavos");
            System.out.println("2. Gestionar fabricas");
            System.out.println("3. Gestionar productos");
            System.out.println("4. Gestionar usuarios");
            System.out.println("0. Volver al menu anterior");
            System.out.print("Su opcion: ");
            String eleccion = scanner.nextLine();
            switch (eleccion) {
                case "1":
                    gestionarEsclavos(esclavosService, scanner, duenia);
                    break;
                case "2":
                    gestionarFabricas(fabricaService, scanner, duenia);
                    break;
                case "3":
                    gestionarProductos(productos, scanner, duenia);
                    break;
                case "4":
                    gestionarUsuarios(usuarios, scanner);
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    // GESTION DE ESCLAVOS
    public static void gestionarEsclavos(RegistroEsclavosService registroService, Scanner scanner, Duenia duenia) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nGESTION DE ESCLAVOS");
            System.out.println("1. Listar registros de esclavos");
            System.out.println("2. Crear registro de esclavos");
            System.out.println("3. Editar registro de esclavos");
            System.out.println("4. Eliminar registro de esclavos");
            System.out.println("0. Volver al menu principal");
            System.out.print("Su opcion: ");
            String eleccion = scanner.nextLine();
            switch (eleccion) {
                case "1":
                    listarRegistros(registroService);
                    break;
                case "2":
                    crearRegistro(registroService, scanner, duenia);
                    break;
                case "3":
                    editarRegistro(registroService, scanner);
                    break;
                case "4":
                    eliminarRegistro(registroService, scanner);
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    private static void listarRegistros(RegistroEsclavosService registroService) {
        List<RegistroEsclavos> lista = registroService.getAllRegistroEsclavos();
        if (lista.isEmpty()) {
            System.out.println("No hay registros.");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                RegistroEsclavos r = lista.get(i);
                System.out.println((i + 1) + ". ID: " + r.getId() +
                        " | Nivel: " + r.getNivelCifrado() +
                        " | Ultimo acceso: " + r.getUltimoAcceso());
            }
        }
    }

    private static void crearRegistro(RegistroEsclavosService registroService, Scanner scanner, Duenia duenia) {
        System.out.print("Nivel de cifrado: ");
        String nivelCifrado = scanner.nextLine();
        Date ultimoAcceso = new Date();
        RegistroEsclavos nuevo = new RegistroEsclavos(ultimoAcceso, nivelCifrado);
        nuevo.setDuenia(duenia);
        registroService.save(nuevo);
        System.out.println("Registro creado correctamente.");
    }

    private static void editarRegistro(RegistroEsclavosService registroService, Scanner scanner) {
        List<RegistroEsclavos> lista = registroService.getAllRegistroEsclavos();
        if (lista.isEmpty()) {
            System.out.println("No hay registros.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            RegistroEsclavos r = lista.get(i);
            System.out.println((i + 1) + ". " + r.getId());
        }
        System.out.print("Seleccione el numero de registro a editar: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= lista.size()) {
            System.out.println("Seleccion no valida.");
            return;
        }
        RegistroEsclavos r = lista.get(index);
        System.out.print("Nuevo nivel de cifrado: ");
        String nivel = scanner.nextLine();
        r.setNivelCifrado(nivel);
        registroService.save(r);
        System.out.println("Registro actualizado.");
    }

    private static void eliminarRegistro(RegistroEsclavosService registroService, Scanner scanner) {
        List<RegistroEsclavos> lista = registroService.getAllRegistroEsclavos();
        if (lista.isEmpty()) {
            System.out.println("No hay registros.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            RegistroEsclavos r = lista.get(i);
            System.out.println((i + 1) + ". " + r.getId());
        }
        System.out.print("Seleccione el numero de registro a eliminar: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= lista.size()) {
            System.out.println("Seleccion no valida.");
            return;
        }
        registroService.delete(lista.get(index).getId());
        System.out.println("Registro eliminado correctamente.");
    }

    // GESTION DE FABRICAS
    public static void gestionarFabricas(FabricaService fabricaService, Scanner scanner, Duenia duenia) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nGESTION DE FABRICAS");
            System.out.println("1. Listar fabricas");
            System.out.println("2. Crear fabrica");
            System.out.println("3. Editar fabrica");
            System.out.println("4. Eliminar fabrica");
            System.out.println("0. Volver al menu principal");
            System.out.print("Su opcion: ");
            String eleccion = scanner.nextLine();

            switch (eleccion) {
                case "1":
                    listarFabricas(fabricaService, duenia);
                    break;
                case "2":
                    crearFabrica(fabricaService, scanner, duenia);
                    break;
                case "3":
                    editarFabrica(fabricaService, scanner, duenia);
                    break;
                case "4":
                    eliminarFabrica(fabricaService, scanner, duenia);
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    private static void listarFabricas(FabricaService fabricaService, Duenia duenia) {
        List<Fabrica> lista = fabricaService.getAllFabrica();
        if (lista.isEmpty()) {
            System.out.println("No hay fabricas registradas.");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                Fabrica f = lista.get(i);
                System.out.println((i + 1) + ". ID: " + f.getId() +
                        " | Pais: " + f.getPais() +
                        " | Ciudad: " + f.getCuidad() +
                        " | Capacidad: " + f.getCapacidad());
            }
        }
    }

    private static void crearFabrica(FabricaService fabricaService, Scanner scanner, Duenia duenia) {
        System.out.print("Pais: ");
        String pais = scanner.nextLine();
        System.out.print("Ciudad: ");
        String cuidad = scanner.nextLine();
        System.out.print("Capacidad: ");
        int capacidad = Integer.parseInt(scanner.nextLine());
        Fabrica nueva = new Fabrica(UUID.randomUUID(), pais, cuidad, capacidad);
        nueva.setDuenia(duenia);
        fabricaService.save(nueva);
        System.out.println("Fabrica creada correctamente.");
    }

    private static void editarFabrica(FabricaService fabricaService, Scanner scanner, Duenia duenia) {
        List<Fabrica> lista = fabricaService.getAllFabrica();
        if (lista.isEmpty()) {
            System.out.println("No hay fabricas registradas.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            Fabrica f = lista.get(i);
            System.out.println((i + 1) + ". " + f.getId());
        }
        System.out.print("Seleccione el numero de fabrica a editar: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= lista.size()) {
            System.out.println("Seleccion no valida.");
            return;
        }
        Fabrica f = lista.get(index);
        System.out.print("Nuevo pais: ");
        f.setPais(scanner.nextLine());
        System.out.print("Nueva ciudad: ");
        f.setCuidad(scanner.nextLine());
        System.out.print("Nueva capacidad: ");
        f.setCapacidad(Integer.parseInt(scanner.nextLine()));
        f.setDuenia(duenia);
        fabricaService.save(f);
        System.out.println("Fabrica actualizada.");
    }

    private static void eliminarFabrica(FabricaService fabricaService, Scanner scanner, Duenia duenia) {
        List<Fabrica> lista = fabricaService.getAllFabrica();
        if (lista.isEmpty()) {
            System.out.println("No hay fabricas registradas.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            Fabrica f = lista.get(i);
            System.out.println((i + 1) + ". " + f.getId());
        }
        System.out.print("Seleccione el numero de fabrica a eliminar: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= lista.size()) {
            System.out.println("Seleccion no valida.");
            return;
        }
        fabricaService.delete(lista.get(index).getId());
        System.out.println("Fabrica eliminada correctamente.");
    }

    // GESTION DE PRODUCTOS
    public static void gestionarProductos(
            List<Producto> productos,
            Scanner scanner,
            Duenia duenia
    ) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nGESTION DE PRODUCTOS");
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar productos");
            System.out.println("0. Volver al menu principal");
            System.out.print("Su opcion: ");
            String eleccion = scanner.nextLine();
            switch (eleccion) {
                case "1":
                    ProductoService.registrarProducto(productos, scanner, duenia);
                    break;
                case "2":
                    ProductoService.listarProductos(productos);
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida para productos.");
            }
        }
    }
    // GESTION DE USUARIOS
    public static void gestionarUsuarios(
            List<Usuario> usuarios,
            Scanner scanner
    ) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nGESTION DE USUARIOS");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("0. Volver al menu principal");
            System.out.print("Su opcion: ");
            String eleccion = scanner.nextLine();
            switch (eleccion) {
                case "1":
                    UsuarioService.registrarUsuario(usuarios, scanner);
                    break;
                case "2":
                    listarUsuarios(usuarios);
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida para usuarios.");
            }
        }
    }

    private static void listarUsuarios(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados");
        } else {
            System.out.println("\nUsuarios registrados:");
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario u = usuarios.get(i);
                System.out.println((i + 1) + ". " + u.getNombre() + " | " + u.getEmail() + " | Rol: " + u.getRol());
            }
        }
    }
}
