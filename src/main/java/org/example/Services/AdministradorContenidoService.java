package org.example.Services;

import org.example.Models.AdministradorContenido;
import org.example.Models.Producto;
import org.example.Models.Categoria;
import org.example.Repositories.AdministradorContenidoRepository;
import org.example.Repositories.CategoriaRepository;
import org.example.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AdministradorContenidoService {

    @Autowired
    private AdministradorContenidoRepository administradorContenidoRepository;

    // Permitira hacer CRUD dentro de producto
    @Autowired
    private ProductoRepository productoRepository;

    // Permitira hacer CRUD dentro de categoria
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<AdministradorContenido> getAllAdministradoresContenido() {
        return administradorContenidoRepository.findAll();
    }

    // Listar productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto agregarProducto(AdministradorContenido admin, Producto producto) {
        if (!admin.getPermisosEdicion()) {
            throw new IllegalStateException("El administrador no tiene permisos de edicion.");
        }
        return productoRepository.save(producto);
    }

    public void eliminarProducto(AdministradorContenido admin, Producto producto) {
        if (!admin.getPermisosEdicion()) {
            throw new IllegalStateException("El administrador no tiene permisos de edicion.");
        }
        productoRepository.delete(producto);
    }

    public void eliminarProductoID(AdministradorContenido admin, UUID productoId) {
        if (!admin.getPermisosEdicion()) {
            throw new IllegalStateException("El administrador no tiene permisos de edicion.");
        }
        productoRepository.deleteById(productoId);
    }

    public Producto actualizarProducto(AdministradorContenido admin, Producto producto) {
        if (!admin.getPermisosEdicion()) {
            throw new IllegalStateException("El administrador no tiene permisos de edicion.");
        }
        return productoRepository.save(producto);
    }

    public Categoria gestionarCategorias(AdministradorContenido admin, Categoria categoria) {
        if (!admin.getPermisosEdicion()) {
            throw new IllegalStateException("El administrador no tiene permisos de edicion.");
        }
        return categoriaRepository.save(categoria);
    }

    public Producto obtenerProductoPorId(UUID id) {
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

}
