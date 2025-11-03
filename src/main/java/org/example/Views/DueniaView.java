package org.example.Views;

import org.example.Models.*;
import org.example.Services.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class DueniaView {

    public static void menuPrincipalDuenia(RegistroEsclavosService esclavosService, FabricaService fabricaService, ProductoService productoService, UsuarioService usuarioService, Scanner scanner, Duenia duenia) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nMENU SUPREMO DE LA DUEÑA CABRITA SAKURA");
            System.out.println("1. Gestionar esclavos");
            System.out.println("2. Gestionar fabricas");
            System.out.println("3. Gestionar productos");
            System.out.println("4. Gestionar usuarios");
            System.out.println("0. Volver al menu anterior");
            System.out.print("Ingrese un numero en base a lo que desee realizar:: ");
            String eleccion = scanner.nextLine();
            switch (eleccion) {
                case "1":
                    gestionarEsclavos(esclavosService, fabricaService, scanner, duenia);
                    break;
                case "2":
                    gestionarFabricas(fabricaService, scanner, duenia);
                    break;
                case "3":
                    gestionarProductos(productoService, scanner, duenia);
                    break;
                case "4":
                    gestionarUsuarios(usuarioService, scanner);
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    public static void gestionarEsclavos(
            RegistroEsclavosService registroService, FabricaService fabricaService,Scanner scanner, Duenia duenia) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nGESTION DE ESCLAVOS");
            System.out.println("1. Listar registros de esclavos");
            System.out.println("2. Crear registro de esclavos");
            System.out.println("3. Editar registro de esclavos");
            System.out.println("4. Eliminar registro de esclavos");
            System.out.println("0. Volver al menu principal");
            System.out.print("Ingrese un numero en base a lo que desee realizar: ");
            String eleccion = scanner.nextLine();
            switch (eleccion) {
                case "1":
                    listarRegistros(registroService);
                    break;
                case "2":
                    crearRegistro(registroService, fabricaService, scanner, duenia);
                    break;
                case "3":
                    editarRegistro(registroService, fabricaService, scanner);
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
                System.out.println((i + 1) + ". Nivel: " + r.getNivelCifrado() + " | Ultimo acceso: " + r.getUltimoAcceso());
                List<TrabajadorEsclavisado> trabajadores = r.getTrabajadores();
                if (trabajadores.isEmpty()) {
                    System.out.println("   No hay esclavos registrados en este registro.");
                } else {
                    for (TrabajadorEsclavisado t : trabajadores) {
                        System.out.println("   - Nombre: " + t.getNombre() + " | Origen: " + t.getPaisOrigen() + " | Salud: " + t.getSalud());
                    }
                }
                System.out.println();
            }
        }
    }

    private static void crearRegistro(RegistroEsclavosService registroService, FabricaService fabricaService, Scanner scanner, Duenia duenia) {
        String nivelCifrado = "";
        while (true) {
            System.out.print("Nivel de cifrado (Bajo/Medio/Alto): ");
            nivelCifrado = scanner.nextLine().trim();
            if (nivelCifrado.equalsIgnoreCase("Bajo") ||
                    nivelCifrado.equalsIgnoreCase("Medio") ||
                    nivelCifrado.equalsIgnoreCase("Alto")) {
                break;
            }
            System.out.println("Debe ingresar Bajo, Medio, o Alto.");
        }
        Date ultimoAcceso = new Date();
        RegistroEsclavos nuevo = new RegistroEsclavos(ultimoAcceso, nivelCifrado);
        nuevo.setDuenia(duenia);

        System.out.print("¿Cuantos esclavos desea registrar en este grupo?: ");
        int cantidad = Integer.parseInt(scanner.nextLine().trim());

        for (int i = 0; i < cantidad; i++) {
            System.out.println("Ingresando datos del esclavo #" + (i + 1));

            System.out.print("Nombre del esclavo: ");
            String nombre = scanner.nextLine().trim();
            System.out.print("Pais de origen: ");
            String pais = scanner.nextLine().trim();
            System.out.print("Edad: ");
            int edad = Integer.parseInt(scanner.nextLine());
            System.out.print("Nivel de salud (0-100): ");
            int salud = Integer.parseInt(scanner.nextLine());

            Date fechaCaptura = new Date();

            List<Fabrica> listaFabrica = fabricaService.getAllFabrica();
            Fabrica fabricaSeleccionada = null;

            if (listaFabrica.isEmpty()) {
                System.out.println("No hay fabricas registradas. El trabajador no sera asignado a ninguna.");
            } else {
                System.out.println("Seleccione la fabrica para asignar al trabajador:");
                for (int f = 0; f < listaFabrica.size(); f++) {
                    Fabrica fab = listaFabrica.get(f);
                    System.out.println((f + 1) + ". " + fab.getPais() + " - " + fab.getCuidad() + " (ID: " + fab.getId() + ")");
                }
                System.out.print("Numero de fabrica: ");
                int opcionFabrica = Integer.parseInt(scanner.nextLine()) - 1;
                if (opcionFabrica < 0 || opcionFabrica >= listaFabrica.size()) {
                    System.out.println("Opcion no valida. El trabajador no tendra fabrica asignada.");
                } else {
                    fabricaSeleccionada = listaFabrica.get(opcionFabrica);
                }
            }

            TrabajadorEsclavisado trabajador = new TrabajadorEsclavisado(null, nombre, pais, edad, fechaCaptura, salud, fabricaSeleccionada);
            trabajador.setRegistro(nuevo);

            nuevo.getTrabajadores().add(trabajador);
        }
        registroService.save(nuevo);
        System.out.println("Registro creado correctamente.");
    }

    private static void editarRegistro(RegistroEsclavosService registroService, FabricaService fabricaService, Scanner scanner) {
        List<RegistroEsclavos> lista = registroService.getAllRegistroEsclavos();
        if (lista.isEmpty()) {
            System.out.println("No hay registros.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            RegistroEsclavos r = lista.get(i);
            System.out.println((i + 1) + ". Nivel: " + r.getNivelCifrado()
                    + " | Ultimo acceso: " + r.getUltimoAcceso());
            List<TrabajadorEsclavisado> trabajadores = r.getTrabajadores();
            if (trabajadores.isEmpty()) {
                System.out.println("   No hay esclavos en este registro.");
            } else {
                for (TrabajadorEsclavisado t : trabajadores) {
                    System.out.println("   - Nombre: " + t.getNombre()
                            + " | Origen: " + t.getPaisOrigen()
                            + " | Edad: " + t.getEdad()
                            + " | Salud: " + t.getSalud()
                            + (t.getAsignadoA() != null
                            ? " | Fabrica actual: " + t.getAsignadoA().getPais() + " - " + t.getAsignadoA().getCuidad()
                            : " | Sin fabrica asignada"));
                }
            }
            System.out.println();
        }

        System.out.print("Seleccione el numero de registro a editar: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= lista.size()) {
            System.out.println("Seleccion no valida.");
            return;
        }
        RegistroEsclavos r = lista.get(index);

        System.out.print("Nuevo nivel de cifrado (actual: " + r.getNivelCifrado() + "): ");
        String nuevoNivel = scanner.nextLine();
        if (!nuevoNivel.trim().isEmpty()) {
            r.setNivelCifrado(nuevoNivel);
        }

        List<TrabajadorEsclavisado> trabajadores = r.getTrabajadores();
        for (TrabajadorEsclavisado t : trabajadores) {
            System.out.println("Editar datos de esclavo: " + t.getNombre()
                    + " | Origen: " + t.getPaisOrigen() + " | Edad: " + t.getEdad());
            System.out.println("Unicamente se puede editar el nivel de salud y fabrica asignada");
            System.out.print("Ingrese nuevo nivel de salud (actual: " + t.getSalud() + "): ");
            String inputSalud = scanner.nextLine();
            if (!inputSalud.trim().isEmpty()) {
                try {
                    int nuevaSalud = Integer.parseInt(inputSalud);
                    t.setSalud(nuevaSalud);
                } catch (NumberFormatException e) {
                    System.out.println("Valor no valido, manteniendo el anterior.");
                }
            }

            List<Fabrica> fabricas = fabricaService.getAllFabrica();
            if (fabricas.isEmpty()) {
                System.out.println("No hay fabricas disponibles para asignar.");
            } else {
                System.out.println("Seleccione una nueva fabrica para este trabajador (enter para mantener la actual):");
                for (int i = 0; i < fabricas.size(); i++) {
                    Fabrica f = fabricas.get(i);
                    System.out.println((i + 1) + ". " + f.getPais() + " - " + f.getCuidad() +
                            (t.getAsignadoA() != null && f.getId().equals(t.getAsignadoA().getId()) ? " (actual)" : ""));
                }
                System.out.print("Numero de fabrica: ");
                String inputFabrica = scanner.nextLine();
                if (!inputFabrica.trim().isEmpty()) {
                    try {
                        int indiceFabrica = Integer.parseInt(inputFabrica) - 1;
                        if (indiceFabrica >= 0 && indiceFabrica < fabricas.size()) {
                            t.setAsignadoA(fabricas.get(indiceFabrica));
                        } else {
                            System.out.println("Seleccion invalida, se mantiene la asignacion actual.");
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Valor no valido, se mantiene la asignacion actual.");
                    }
                }
            }
        }

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

            String trabajadoresResumen = "";
            List<TrabajadorEsclavisado> trabajadores = r.getTrabajadores();
            if (trabajadores.isEmpty()) {
                trabajadoresResumen = "Sin esclavos";
            } else {
                trabajadoresResumen = trabajadores.stream()
                        .map(TrabajadorEsclavisado::getNombre)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("");
            }
            System.out.println((i + 1) + ". Nivel: " + r.getNivelCifrado() +
                    " | Ultimo acceso: " + r.getUltimoAcceso() +
                    " | Esclavos: " + trabajadoresResumen);
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

    public static void gestionarFabricas(
            FabricaService fabricaService, Scanner scanner, Duenia duenia) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nGESTION DE FABRICAS");
            System.out.println("1. Listar fabricas");
            System.out.println("2. Crear fabrica");
            System.out.println("3. Editar fabrica");
            System.out.println("4. Eliminar fabrica");
            System.out.println("0. Volver al menu principal");
            System.out.print("Ingrese un numero en base a lo que desee realizar: ");
            String eleccion = scanner.nextLine();

            switch (eleccion) {
                case "1":
                    listarFabricas(fabricaService);
                    break;
                case "2":
                    crearFabrica(fabricaService, scanner, duenia);
                    break;
                case "3":
                    editarFabrica(fabricaService, scanner, duenia);
                    break;
                case "4":
                    eliminarFabrica(fabricaService, scanner);
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    private static void listarFabricas(FabricaService fabricaService) {
        List<Fabrica> lista = fabricaService.getAllFabrica();
        if (lista.isEmpty()) {
            System.out.println("No hay fabricas registradas.");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                Fabrica f = lista.get(i);
                String nombrePersonalizado = "Glow Up - " + f.getCuidad() + ", " + f.getPais() + " | Capacidad: " + f.getCapacidad();
                System.out.println((i + 1) + ". " + nombrePersonalizado);
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
            String nombrePersonalizado = "Glow Up - " + f.getPais() + " " + f.getCuidad() + " | Capacidad: " + f.getCapacidad();
            System.out.println((i + 1) + ". " + nombrePersonalizado);
            if (!f.getTrabajadores().isEmpty()) {
                System.out.print("   Trabajadores: ");
                System.out.println(f.getTrabajadores().stream()
                        .map(TrabajadorEsclavisado::getNombre)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse(""));
            } else {
                System.out.println("   Sin trabajadores asignados");
            }
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
        System.out.println("Fabrica actualizada correctamente");
    }

    private static void eliminarFabrica(FabricaService fabricaService, Scanner scanner) {
        List<Fabrica> lista = fabricaService.getAllFabrica();
        if (lista.isEmpty()) {
            System.out.println("No hay fabricas registradas.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            Fabrica f = lista.get(i);
            String nombrePersonalizado = "Glow Up - " + f.getPais() + " " + f.getCuidad() + " | Capacidad: " + f.getCapacidad();
            System.out.println((i + 1) + ". " + nombrePersonalizado);
            if (!f.getTrabajadores().isEmpty()) {
                System.out.print("   Trabajadores: ");
                System.out.println(f.getTrabajadores().stream()
                        .map(TrabajadorEsclavisado::getNombre)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse(""));
            } else {
                System.out.println("   Sin trabajadores asignados");
            }
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

    public static void gestionarProductos(ProductoService productoService, Scanner scanner, Duenia duenia) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nGESTION DE PRODUCTOS");
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar productos");
            System.out.println("0. Volver al menu principal");
            System.out.print("Ingrese un numero en base a lo que desee realizar: ");
            String eleccion = scanner.nextLine();
            switch (eleccion) {
                case "1":
                    productoService.registrarProducto(scanner, duenia);
                    break;
                case "2":
                    productoService.listarProductos();
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida para productos.");
            }
        }
    }

    public static void gestionarUsuarios(UsuarioService usuarioService, Scanner scanner) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nGESTION DE USUARIOS");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("0. Volver al menu principal");
            System.out.print("Ingrese un numero en base a lo que desee realizar: ");
            String eleccion = scanner.nextLine();
            switch (eleccion) {
                case "1":
                    usuarioService.registrarUsuario(scanner);
                    break;
                case "2":
                    usuarioService.listarUsuarios();
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida para usuarios.");
            }
        }
    }
}
