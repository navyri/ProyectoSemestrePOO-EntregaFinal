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
import java.util.Optional;
import java.util.UUID;

@Service
public class AdministradorContenidoService {

    @Autowired
    private AdministradorContenidoRepository administradorContenidoRepository;

    //permititra hacer crud dentro de producto
    @Autowired
    private ProductoRepository productoRepository;

    //permitira hacer crud dentro de categoria
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<AdministradorContenido> getAllAdministradoresContenido() {
        return administradorContenidoRepository.findAll();
    }

    //listar productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto agregarProducto(AdministradorContenido admin, Producto producto) {
        // Logica para agregar producto
        if ((admin.getPermisosEdicion() == false)) {
            throw new IllegalStateException("El administrador no tiene permisos de edicion.");
        }else{
            return productoRepository.save(producto);
        }
    }

    public void eliminarProducto(AdministradorContenido admin, Producto producto) {
        // Logica para eliminar producto
        if (admin.getPermisosEdicion() ==false) {
            throw new IllegalStateException("El administrador no tiene permisos de edicion.");
        }
        productoRepository.delete(producto);
    }

    public void eliminarProductoID(AdministradorContenido admin, UUID productoId) {
        if (admin.getPermisosEdicion() == false) {
            throw new IllegalStateException("El administrador no tiene permisos de edicion.");
        } else {
            productoRepository.deleteById(productoId);
        }
    }

    public Producto actualizarProducto(AdministradorContenido admin, Producto producto) {
        if (admin.getPermisosEdicion() == false) {
            throw new IllegalStateException("El administrador no tiene permisos de edicion.");
        }else{
            return productoRepository.save(producto);
        }
    }

    public Categoria gestionarCategorias(AdministradorContenido admin, Categoria categoria) {
        if (admin.getPermisosEdicion() == false) {
            throw new IllegalStateException("El administrador no tiene permisos de edicion.");
        } else {
            return categoriaRepository.save(categoria);
        }
    }

    public Optional<Producto> obtenerProductoPorId(UUID id) {
        return productoRepository.findById(id);
    }

}
