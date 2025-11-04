package org.example.Services;

import org.example.Models.*;
import org.example.Repositories.CompraRepository;
import org.example.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.*;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Compra> getAllCompra() {
        return compraRepository.findAll();
    }

    public void agregarLineaDeCompra(Compra compra, LineaCompras lineaCompras) {
        compra.getLineaCompras().add(lineaCompras);
    }

    public void eliminarLineaDeCompra(Compra compra, LineaCompras lineaCompras) {
        compra.getLineaCompras().remove(lineaCompras);
    }

    // METODO PARA REGISTRAR COMPRA
    public void registrarCompra(Scanner scanner, Usuario usuarioActivo) {
        if (usuarioActivo == null) {
            System.out.println("Por favor inicie sesion para registrar una compra");
            return;
        }
        List<Producto> productos = productoRepository.findAll();
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles para comprar en el momento");
            return;
        }
        System.out.println("\nSeleccione el producto a comprar");
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            System.out.println((i + 1) + ". " + p.getNombre() + " | $" + p.getPrecio() + " | Stock: " + p.getStock());
        }
        System.out.print("- Ingrese el numero de producto: ");
        int numeroProductoElegido = scanner.nextInt();
        scanner.nextLine();

        if (numeroProductoElegido < 1 || numeroProductoElegido > productos.size()) {
            System.out.println("Producto no valido");
            return;
        }
        Producto seleccionado = productos.get(numeroProductoElegido - 1);
        System.out.print("- Cantidad a comprar: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        if (cantidad <= 0 || cantidad > seleccionado.getStock()) {
            System.out.println("La cantidad ingresada no es valida o el stock es insuficiente");
            return;
        }

        seleccionado.setStock(seleccionado.getStock() - cantidad);
        productoRepository.save(seleccionado);

        Compra compra = new Compra(UUID.randomUUID(), new Date(), seleccionado.getPrecio() * cantidad, "Realizada");
        compraRepository.save(compra);

        System.out.println("Compra realizada correctamente");
    }

    //METODO DE REGISTRAR COMPRAS PARA LA INTERFAZ
    public void registrarCompraInterfaz(Usuario usuarioActivo) {
        if (usuarioActivo == null) {
            JOptionPane.showMessageDialog(null, "Por favor inicie sesión para registrar una compra");
            return;
        }
        List<Producto> productos = productoRepository.findAll();
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos disponibles para comprar en el momento");
            return;
        }

        // Armando el mensaje con la lista de productos
        StringBuilder lista = new StringBuilder("LISTA DE PRODUCTOS DISPONIBLES:\n\n");
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            lista.append((i + 1)).append(". ")
                    .append(p.getNombre())
                    .append(" | $").append(p.getPrecio())
                    .append(" | Stock: ").append(p.getStock())
                    .append("\n");
        }
        lista.append("\nIngrese el número del producto a comprar:");

        String entrada = JOptionPane.showInputDialog(null, lista.toString(), "Seleccionar producto", JOptionPane.QUESTION_MESSAGE);

        if (entrada == null) return;

        int numeroProductoElegido = -1;
        try {
            numeroProductoElegido = Integer.parseInt(entrada.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida. Debe ser un número.");
            return;
        }

        if (numeroProductoElegido < 1 || numeroProductoElegido > productos.size()) {
            JOptionPane.showMessageDialog(null, "Producto no válido");
            return;
        }
        Producto seleccionado = productos.get(numeroProductoElegido - 1);

        int cantidad = -1;
        try {
            String cantidadEntrada = JOptionPane.showInputDialog("- Ingrese la cantidad que desea comprar");
            if (cantidadEntrada != null) {
                cantidad = Integer.parseInt(cantidadEntrada.trim());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad no válida / error al ingresar la cantidad");
            return;
        }

        if (cantidad <= 0 || cantidad > seleccionado.getStock()) {
            JOptionPane.showMessageDialog(null, "La cantidad ingresada no es válida o el stock es insuficiente");
            return;
        }

        seleccionado.setStock(seleccionado.getStock() - cantidad);
        productoRepository.save(seleccionado);

        Compra compra = new Compra(UUID.randomUUID(), new Date(), seleccionado.getPrecio() * cantidad, "Realizada");
        compraRepository.save(compra);

        JOptionPane.showMessageDialog(null, "Compra realizada correctamente");
    }
}
