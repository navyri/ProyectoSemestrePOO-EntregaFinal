package org.example.Views.GUI;

import org.example.Models.*;
import org.example.Services.*;
import javax.swing.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DueniaViewInterfaz {

    public static void menuPrincipalDuenia(RegistroEsclavosService esclavosService,
                                           FabricaService fabricaService,
                                           ProductoService productoService,
                                           UsuarioService usuarioService,
                                           Duenia duenia) {

        boolean salir = false;
        while (!salir) {
            String eleccion = JOptionPane.showInputDialog(null,
                    "MENU SUPREMO DE LA DUEÑA CABRITA SAKURA\n" +
                            "1. Gestionar esclavos\n" +
                            "2. Gestionar fabricas\n" +
                            "3. Gestionar productos\n" +
                            "4. Gestionar usuarios\n" +
                            "0. Volver al menu anterior\n\n" +
                            "Ingrese un numero según lo que desee realizar:");

            if (eleccion == null) break;

            switch (eleccion) {
                case "1":
                    gestionarEsclavos(esclavosService, fabricaService, duenia);
                    break;
                case "2":
                    gestionarFabricas(fabricaService, duenia);
                    break;
                case "3":
                    gestionarProductos(productoService, duenia);
                    break;
                case "4":
                    gestionarUsuarios(usuarioService);
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida.");
            }
        }
    }


    public static void gestionarEsclavos(RegistroEsclavosService registroService,
                                         FabricaService fabricaService,
                                         Duenia duenia) {

        boolean salir = false;
        while (!salir) {
            String eleccion = JOptionPane.showInputDialog(null,
                    "GESTION DE ESCLAVOS\n" +
                            "1. Listar registros\n" +
                            "2. Crear registro\n" +
                            "3. Editar registro\n" +
                            "4. Eliminar registro\n" +
                            "0. Volver al menu principal");

            if (eleccion == null) break;

            switch (eleccion) {
                case "1":
                    listarRegistros(registroService);
                    break;
                case "2":
                    crearRegistro(registroService, fabricaService, duenia);
                    break;
                case "3":
                    editarRegistro(registroService, fabricaService);
                    break;
                case "4":
                    eliminarRegistro(registroService);
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida.");
            }
        }
    }

    private static void listarRegistros(RegistroEsclavosService registroService) {
        List<RegistroEsclavos> lista = registroService.getAllRegistroEsclavos();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay registros.");
            return;
        }

        String texto="";
        for (int i = 0; i < lista.size(); i++) {
            RegistroEsclavos r = lista.get(i);
            texto+=((i + 1)) + ". Nivel: "+(r.getNivelCifrado())
                    +(" | Ultimo acceso: ")+(r.getUltimoAcceso())+("\n");
            for (TrabajadorEsclavisado t : r.getTrabajadores()) {
                texto+=("   - ")+(t.getNombre())
                        +(" | Origen: ")+(t.getPaisOrigen())
                        +(" | Salud: ")+(t.getSalud())+("\n");
            }
            texto+="\n";
        }
        JOptionPane.showMessageDialog(null, texto);
    }

    private static void crearRegistro(RegistroEsclavosService registroService,
                                      FabricaService fabricaService,
                                      Duenia duenia) {

        String nivelCifrado;
        do {
            nivelCifrado = JOptionPane.showInputDialog("Nivel de cifrado (Bajo/Medio/Alto):");
            if (nivelCifrado == null) return;
        } while (!nivelCifrado.equalsIgnoreCase("Bajo") &&
                !nivelCifrado.equalsIgnoreCase("Medio") &&
                !nivelCifrado.equalsIgnoreCase("Alto"));

        RegistroEsclavos nuevo = new RegistroEsclavos(new Date(), nivelCifrado);
        nuevo.setDuenia(duenia);

        int cantidad;
        try {
            String cantidadStr = JOptionPane.showInputDialog("¿Cuantos esclavos desea registrar?");
            if (cantidadStr == null) {
                return;
            }
            cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <=0 ){
                JOptionPane.showMessageDialog(null,"No se aceptan valores negativos");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un numero valido.");
            return;
        }

        for (int i = 0; i < cantidad; i++) {
            String nombre = JOptionPane.showInputDialog("Nombre del esclavo #" + (i + 1) + ":");
            if (nombre == null) return;
            String pais = JOptionPane.showInputDialog("Pais de origen:");
            if (pais == null) return;

            int edad, salud;
            try {
                edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
                salud = Integer.parseInt(JOptionPane.showInputDialog("Nivel de salud (0-100):"));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida. Intente nuevamente.");
                return;
            }

            List<Fabrica> fabricas = fabricaService.getAllFabrica();
            Fabrica fabricaSeleccionada = null;
            if (!fabricas.isEmpty()) {
                String textoFabricas = "Seleccione una fabrica:\n";
                for (int f = 0; f < fabricas.size(); f++) {
                    textoFabricas+=(f + 1)+". "+fabricas.get(f).getPais()
                            +(" - ")+fabricas.get(f).getCuidad()+"\n";
                }

                try {
                    String seleccion = JOptionPane.showInputDialog(textoFabricas);
                    if (seleccion != null) {
                        int index = Integer.parseInt(seleccion) - 1;
                        if (index >= 0 && index < fabricas.size()) {
                            fabricaSeleccionada = fabricas.get(index);
                        }
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Seleccion invalida. No se asignara fabrica.");
                }
            }

            TrabajadorEsclavisado trabajador = new TrabajadorEsclavisado(
                    null, nombre, pais, edad, new Date(), salud, fabricaSeleccionada
            );
            trabajador.setRegistro(nuevo);
            nuevo.getTrabajadores().add(trabajador);
        }

        registroService.save(nuevo);
        JOptionPane.showMessageDialog(null, "Registro creado correctamente.");
    }

    private static void editarRegistro(RegistroEsclavosService registroService, FabricaService fabricaService) {
        List<RegistroEsclavos> lista = registroService.getAllRegistroEsclavos();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay registros.");
            return;
        }
        // Mostrar registros y sus esclavos
        StringBuilder texto = new StringBuilder("Seleccione el número de registro a editar:\n");
        for (int i = 0; i < lista.size(); i++) {
            RegistroEsclavos r = lista.get(i);
            texto.append((i + 1)).append(". Nivel: ").append(r.getNivelCifrado())
                    .append(" | Último acceso: ").append(r.getUltimoAcceso());
            List<TrabajadorEsclavisado> trabajadores = r.getTrabajadores();
            if (!trabajadores.isEmpty()) {
                texto.append(" | Esclavos: ");
                for (TrabajadorEsclavisado t : trabajadores) {
                    texto.append(t.getNombre()).append(", ");
                }
                texto.setLength(texto.length() - 2);
            } else {
                texto.append(" | Sin esclavos");
            }
            texto.append("\n");
        }
        try {
            String seleccion = JOptionPane.showInputDialog(texto.toString());
            if (seleccion == null) return;
            int index = Integer.parseInt(seleccion) - 1;
            if (index < 0 || index >= lista.size())  {
                JOptionPane.showMessageDialog(null, "Selección no válida.");
                return;
            }
            RegistroEsclavos r = lista.get(index);
            // Editar nivel de cifrado
            String nuevoNivel = JOptionPane.showInputDialog("Nuevo nivel de cifrado (actual: " + r.getNivelCifrado() + "):");
            if (nuevoNivel != null && !nuevoNivel.trim().isEmpty()) {
                r.setNivelCifrado(nuevoNivel);
            }
            // Editar trabajadores esclavizados
            List<TrabajadorEsclavisado> trabajadores = r.getTrabajadores();
            for (int i = 0; i < trabajadores.size(); i++) {
                    TrabajadorEsclavisado t = trabajadores.get(i);

                    String info = "EDITANDO ESCLAVO #"+(i+1)
                            + " (del registro de nivel: " + r.getNivelCifrado() + ")\n"
                            + "Nombre: " + t.getNombre()
                            + "\nOrigen: " + t.getPaisOrigen()
                            + "\nEdad: " + t.getEdad()
                            + "\nSalud actual: " + t.getSalud()
                            + (t.getAsignadoA() != null
                            ? "\nFábrica actual: " + t.getAsignadoA().getPais() + " - " + t.getAsignadoA().getCuidad()
                            : "\nSin fábrica asignada")
                            + "\n\n(Ingrese nuevo nivel de salud [0-100], vacío para mantener):";

                String inputSalud = JOptionPane.showInputDialog(info);
                if (inputSalud != null && !inputSalud.trim().isEmpty()) {
                    try {
                        int nuevaSalud = Integer.parseInt(inputSalud.trim());
                        if (nuevaSalud < 0 || nuevaSalud > 100) {
                            JOptionPane.showMessageDialog(null, "El nivel de salud debe estar entre 0 y 100. Se mantiene el anterior.");
                        } else {
                            t.setSalud(nuevaSalud);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Valor no válido, manteniendo el anterior.");
                    }
                }

                // Mostrar fábricas disponibles y reasignar si el usuario lo desea
                List<Fabrica> fabricas = fabricaService.getAllFabrica();
                Fabrica actual = t.getAsignadoA();
                if (fabricas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay fábricas disponibles para asignar.");
                } else {
                    StringBuilder sb = new StringBuilder("Fábricas disponibles:\n");
                    for (int j = 0; j < fabricas.size(); j++) {
                        Fabrica f = fabricas.get(j);
                        sb.append((j + 1)).append(". ").append(f.getPais()).append(" - ").append(f.getCuidad());
                        if (actual != null && f.getId().equals(actual.getId())) {
                            sb.append(" (actual)");
                        }
                        sb.append("\n");
                    }
                    sb.append("Ingrese número de fábrica para reasignar (vacío para mantener):");
                    String inputFabrica = JOptionPane.showInputDialog(sb.toString());
                    if (inputFabrica != null && !inputFabrica.trim().isEmpty()) {
                        try {
                            int idxFab = Integer.parseInt(inputFabrica.trim()) - 1;
                            if (idxFab < 0 || idxFab >= fabricas.size()) {
                                JOptionPane.showMessageDialog(null, "Selección inválida. Se mantiene la fábrica actual.");
                            } else {
                                t.setAsignadoA(fabricas.get(idxFab));
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Valor no válido, se mantiene la asignación actual.");
                        }
                    }
                }
            }
            registroService.save(r);
            JOptionPane.showMessageDialog(null, "Registro actualizado correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Seleccion invalida.");
        }
    }

    private static void eliminarRegistro(RegistroEsclavosService registroService) {
        List<RegistroEsclavos> lista = registroService.getAllRegistroEsclavos();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay registros.");
            return;
        }

        StringBuilder texto = new StringBuilder("Seleccione el numero de registro a eliminar:\n");
        for (int i = 0; i < lista.size(); i++) {
            RegistroEsclavos r = lista.get(i);
            texto.append((i + 1)).append(". Nivel: ").append(r.getNivelCifrado())
                    .append(" | Último acceso: ").append(r.getUltimoAcceso());

            List<TrabajadorEsclavisado> trabajadores = r.getTrabajadores();
            if (!trabajadores.isEmpty()) {
                texto.append(" | Esclavos: ");
                for (TrabajadorEsclavisado t : trabajadores) {
                    texto.append(t.getNombre()).append(", ");
                }
                texto.setLength(texto.length() - 2);
            } else {
                texto.append(" | Sin esclavos");
            }
            texto.append("\n");
        }
        try {
            String seleccion = JOptionPane.showInputDialog(texto.toString());
            if (seleccion == null) return;
            int index = Integer.parseInt(seleccion) - 1;
            if (index < 0 || index >= lista.size()) return;

            registroService.delete(lista.get(index).getId());
            JOptionPane.showMessageDialog(null, "Registro eliminado correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada invalida.");
        }
    }

    // Gestionar Fabrica Interfaz

    public static void gestionarFabricas(FabricaService fabricaService, Duenia duenia) {
        boolean salir = false;

        while (!salir) {
            String eleccion = JOptionPane.showInputDialog(null,
                    "GESTION DE FABRICAS\n" +
                            "1. Listar fabricas\n" +
                            "2. Crear fabrica\n" +
                            "3. Editar fabrica\n" +
                            "4. Eliminar fabrica\n" +
                            "0. Volver al menu principal");

            if (eleccion == null) break;

            switch (eleccion) {
                case "1":
                    listarFabricas(fabricaService);
                    break;
                case "2":
                    crearFabrica(fabricaService, duenia);
                    break;
                case "3":
                    editarFabrica(fabricaService, duenia);
                    break;
                case "4":
                    eliminarFabrica(fabricaService);
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida.");
            }
        }
    }

    private static void listarFabricas(FabricaService fabricaService) {
        List<Fabrica> lista = fabricaService.getAllFabrica();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay fabricas registradas.");
            return;
        }

        String textoFabricas= "";
        for (int i = 0; i < lista.size(); i++) {
            Fabrica f = lista.get(i);
            textoFabricas+=((i + 1))+". "+f.getCuidad()+
                    ", "+f.getPais()+
                    " | Capacidad: "+f.getCapacidad()+"\n";
        }

        JOptionPane.showMessageDialog(null, textoFabricas);
    }

    private static void crearFabrica(FabricaService fabricaService, Duenia duenia) {
        String pais = JOptionPane.showInputDialog("Pais:");
        if (pais == null) return;
        String ciudad = JOptionPane.showInputDialog("Ciudad:");
        if (ciudad == null) return;

        try {
            int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Capacidad:"));
            Fabrica nueva = new Fabrica(UUID.randomUUID(), pais, ciudad, capacidad);
            nueva.setDuenia(duenia);
            fabricaService.save(nueva);
            JOptionPane.showMessageDialog(null, "Fabrica creada correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un numero valido para la capacidad.");
        }
    }

    private static void editarFabrica(FabricaService fabricaService, Duenia duenia) {
        List<Fabrica> lista = fabricaService.getAllFabrica();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay fabricas.");
            return;
        }

        String textoFabrica = "Seleccione una fabrica a editar:\n";
        for (int i = 0; i < lista.size(); i++) {
            Fabrica f = lista.get(i);
            textoFabrica+=(i + 1)+". "+f.getPais()+" - "+f.getCuidad()+ "\n";
        }

        try {
            String seleccion = JOptionPane.showInputDialog(textoFabrica);
            if (seleccion == null) return;
            int index = Integer.parseInt(seleccion) - 1;
            if (index < 0 || index >= lista.size()) return;

            Fabrica f = lista.get(index);
            f.setPais(JOptionPane.showInputDialog("Nuevo pais:", f.getPais()));
            f.setCuidad(JOptionPane.showInputDialog("Nueva ciudad:", f.getCuidad()));
            f.setCapacidad(Integer.parseInt(JOptionPane.showInputDialog("Nueva capacidad:", String.valueOf(f.getCapacidad()))));
            f.setDuenia(duenia);
            fabricaService.save(f);
            JOptionPane.showMessageDialog(null, "Fabrica actualizada correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Seleccion o capacidad invalida.");
        }
    }

    private static void eliminarFabrica(FabricaService fabricaService) {
        List<Fabrica> lista = fabricaService.getAllFabrica();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay fabricas registradas.");
            return;
        }

        String textofabrica ="Seleccione fabrica a eliminar:\n";
        for (int i = 0; i < lista.size(); i++) {
            Fabrica f = lista.get(i);
            textofabrica+=(i + 1)+". "+f.getPais()+" - "+f.getCuidad()+"\n";
        }

        try {
            String seleccion = JOptionPane.showInputDialog(textofabrica);
            if (seleccion == null) return;
            int index = Integer.parseInt(seleccion) - 1;
            if (index < 0 || index >= lista.size()) return;

            fabricaService.delete(lista.get(index).getId());
            JOptionPane.showMessageDialog(null, "Fabrica eliminada correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada invalida.");
        }
    }

    //GESTION DE PRODUCTOS Y USUARIOS

    public static void gestionarProductos(ProductoService productoService, Duenia duenia) {
        boolean salir = false;

        while (!salir) {
            String eleccion = JOptionPane.showInputDialog(null,
                    "GESTION DE PRODUCTOS\n" +
                            "1. Registrar producto\n" +
                            "2. Listar productos\n" +
                            "0. Volver al menu principal");

            if (eleccion == null) break;

            switch (eleccion) {
                case "1":
                    productoService.registrarProductoInterfaz(duenia);
                    break;
                case "2":
                    productoService.listarProductosInterfaz();
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida.");
            }
        }
    }

    public static void gestionarUsuarios(UsuarioService usuarioService) {
        boolean salir = false;

        while (!salir) {
            String eleccion = JOptionPane.showInputDialog(null,
                    "GESTION DE USUARIOS\n" +
                            "1. Registrar usuario\n" +
                            "2. Listar usuarios\n" +
                            "0. Volver al menú principal");

            if (eleccion == null) break;

            switch (eleccion) {
                case "1":
                    usuarioService.registrarUsuarioInterfaz();
                    break;
                case "2":
                    usuarioService.listarUsuariosInterfaz();
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida.");
            }
        }
    }
}
