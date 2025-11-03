package org.example.Services;

import org.example.Models.*;
import org.example.Repositories.CompraRepository;
import org.example.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
