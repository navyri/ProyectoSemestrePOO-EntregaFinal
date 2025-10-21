package org.example.Services;

import org.example.Models.Producto;
import org.example.Models.Usuario;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductoService {

    // METODO PARA REGISTRAR PRODUCTOS
    public static void registrarProducto(List<Producto> productos, Scanner scanner, Usuario usuarioAutenticado) {
        if (usuarioAutenticado == null || !usuarioAutenticado.getRol().equalsIgnoreCase("Administrador")) {
            System.out.println("Acceso denegado. Unicamente los administradores pueden registrar productos");
            return;
        }
        System.out.println("\nLlene los siguientes datos para registrar un producto");
        System.out.print("- Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("- Descripcion: ");
        String descripcion = scanner.nextLine();
        System.out.print("- Precio: ");
        double precio = scanner.nextDouble(); scanner.nextLine();

        if (precio < 0) {
            System.out.println("No se permite registrar productos con precio negativo");
            return;
        }

        System.out.print("- Stock: ");
        int cantidadStock = scanner.nextInt(); scanner.nextLine();

        if (cantidadStock < 0) {
            System.out.println("No se permite registrar productos con stock negativo");
            return;
        }

        Producto producto = new Producto(UUID.randomUUID(), nombre, descripcion, precio, cantidadStock, new Date(), null);
        productos.add(producto);
        System.out.println("Producto registrado correctamente");
    }

    // METODO PARA LISTAR PRODUCTOS
    public static void listarProductos(List<Producto> productos) {
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
}
