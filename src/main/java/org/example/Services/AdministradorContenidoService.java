package org.example.Services;

import org.example.Models.AdministradorContenido;
import org.example.Models.Producto;
import org.example.Models.Categoria;
import org.example.Repositories.AdministradorContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdministradorContenidoService {

    @Autowired
    private AdministradorContenidoRepository administradorContenidoRepository;

    public List<AdministradorContenido> getAllAdministradoresContenido() {
        return administradorContenidoRepository.findAll();
    }

    public void agregarProducto(AdministradorContenido admin, Producto producto) {
        // Lógica para agregar producto
    }

    public void eliminarProducto(AdministradorContenido admin, Producto producto) {
        // Lógica para eliminar producto
    }

    public void actualizarProducto(AdministradorContenido admin, Producto producto) {
        // Lógica para actualizar producto
    }

    public void gestionarCategorias(AdministradorContenido admin, Categoria categoria) {
        // Lógica para gestionar categorías
    }


}
