package org.example.Services;

import org.example.Models.Categoria;
import org.example.Models.Producto;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    public void agregarProducto(Categoria categoria, Producto p) {
        categoria.getProducto().add(p);
    }

    public void eliminarProducto(Categoria categoria, Producto p) {
        categoria.getProducto().remove(p);
    }
}

