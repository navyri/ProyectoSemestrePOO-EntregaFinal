package org.example.Services;

import org.example.Models.Duenia;
import org.example.Models.Producto;
import org.example.Models.Usuario;
import org.example.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.*;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProducto() {
        return productoRepository.findAll();
    }

    // METODO PARA REGISTRAR PRODUCTOS
    public void registrarProducto(Scanner scanner, Usuario usuarioAutenticado) {
        if (usuarioAutenticado == null) {
            System.out.println("Acceso denegado. Unicamente los administradores pueden registrar productos");
            return;
        }
        String rolNormalizado = usuarioAutenticado.getRol().replaceAll("\\s", "").toLowerCase();

        boolean esAdministrador = rolNormalizado.equals("administradorcontenido") || rolNormalizado.equals("administrador");
        boolean esDuenia = rolNormalizado.equals("duenia") || usuarioAutenticado instanceof Duenia;

        if (!(esAdministrador || esDuenia)) {
            System.out.println("Acceso denegado. Solo administradores y la dueña pueden registrar productos");
            return;
        }

        System.out.println("\nLlene los siguientes datos para registrar un producto");
        System.out.print("- Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("- Descripcion: ");
        String descripcion = scanner.nextLine();

        double precio = -1;
        while (true) {
            System.out.print("- Precio: ");
            String precioInput = scanner.nextLine();
            try {
                precio = Double.parseDouble(precioInput);
                if (precio <= 0) {
                    System.out.println("No se permite registrar productos con precio negativo");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un valor numerico valido para precio.");
            }
        }

        int cantidadStock = -1;
        while (true) {
            System.out.print("- Stock: ");
            String stockInput = scanner.nextLine();
            try {
                cantidadStock = Integer.parseInt(stockInput);
                if (cantidadStock < 0) {
                System.out.println("No se permite registrar productos con stock negativo");
                continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un valor numerico entero valido para stock.");
            }
        }

        System.out.println("\nResumen de datos ingresados:");
        System.out.println("- Nombre: " + nombre);
        System.out.println("- Descripcion: " + descripcion);
        System.out.println("- Precio: " + precio);
        System.out.println("- Stock: " + cantidadStock);
        System.out.print("¿Desea registrar el producto con estos datos? (Si/No): ");
        String confirmacion = scanner.nextLine();
        if (!confirmacion.equalsIgnoreCase("Si")) {
            System.out.println("Registro cancelado");
            return;
        }

        Producto producto = new Producto(UUID.randomUUID(), nombre, descripcion, precio, cantidadStock, new Date(), null);
        productoRepository.save(producto);
        System.out.println("Producto registrado correctamente");
    }

    //METODO PARA REGISTRAR PRODUCTOS DE LA INTERRFAZ
    public void registrarProductoInterfaz(Usuario usuarioAutenticado) {
        if (usuarioAutenticado == null) {
            JOptionPane.showMessageDialog(null, "Acceso denegado. Unicamente los administradores pueden registrar productos");
            return;
        }
        String rolNormalizado = usuarioAutenticado.getRol().replaceAll("\\s", "").toLowerCase();

        boolean esAdministrador = rolNormalizado.equals("administradorcontenido") || rolNormalizado.equals("administrador");
        boolean esDuenia = rolNormalizado.equals("duenia") || usuarioAutenticado instanceof Duenia;

        if (!(esAdministrador || esDuenia)) {
            JOptionPane.showMessageDialog(null, "Acceso denegado. Solo administradores y la dueña pueden registrar productos");
            return;
        }
        try {
            JOptionPane.showMessageDialog(null, "Llene los siguientes datos para registrar un producto");
            String nombre = JOptionPane.showInputDialog(null, "Nombre del producto");
            if (nombre == null || nombre.trim().isEmpty()) { //quitar espacios al inicio y al final
                JOptionPane.showMessageDialog(null, "el nombre no puede estar vacio");
                return;
            }
            String descripcion = JOptionPane.showInputDialog("Descripcion del producto");
            if (descripcion == null || descripcion.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "la descripcion no puede estar vacia");
                return;
            }

            double precio = -1;
            while (true) {
                String precioInput = JOptionPane.showInputDialog("precio del producto");
                if (precioInput == null) {
                    JOptionPane.showMessageDialog(null, "No se pueden ingresar valores vacios");
                    return;
                }
                try {
                    precio = Double.parseDouble(precioInput);
                    if (precio <= 0) {
                        JOptionPane.showMessageDialog(null, "No se permite registrar productos con precio negativo");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un valor numerico valido para precio.");
                }
            }

            int cantidadStock = -1;
            while (true) {

                String stockInput = JOptionPane.showInputDialog("- Stock: -");
                try {
                    cantidadStock = Integer.parseInt(stockInput);
                    if (cantidadStock < 0) {
                        JOptionPane.showMessageDialog(null, "No se permite registrar productos con stock negativo");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un valor numerico entero valido para stock.");
                }
            }


            String mensaje= "\n"+"-Nombre: "+nombre + "\n"+ "-Descripcion: "+ descripcion +
                    "\n" +"-Precio: "+ precio + "\n" +"-Stock: " +cantidadStock;
            JOptionPane.showMessageDialog(null,"Resumen de datos ingresados: "+mensaje);

            String confirmacion = JOptionPane.showInputDialog(null,"¿Desea registrar el producto con estos datos? (Si/No): ");
            if (!confirmacion.equalsIgnoreCase("Si")) {
                JOptionPane.showMessageDialog(null,"Registro cancelado");
                return;
            }

            Producto producto = new Producto(UUID.randomUUID(), nombre, descripcion, precio, cantidadStock, new Date(), null);
            productoRepository.save(producto);
            JOptionPane.showMessageDialog(null,"Producto registrado correctamente");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al registrar el producto");
        }
    }

    // METODO PARA LISTAR PRODUCTOS
    public void listarProductos() {
        List<Producto> productos = productoRepository.findAll();
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados");
        } else {
            System.out.println("\nProductos disponibles: ");
            for (int i = 0; i < productos.size(); i++) {
                Producto p = productos.get(i);
                System.out.println((i + 1) + ". " + p.getNombre() + " | $" + p.getPrecio() + " | Stock: " + p.getStock());
            }
        }
    }

    public Producto findById(UUID id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro producto con el id: " + id));
    }
}
