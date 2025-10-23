package org.example.Services;

import org.example.Models.Categoria;
import org.example.Models.Producto;
import org.example.Repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategoria() {
        return categoriaRepository.findAll();
    }

    public void agregarProducto(Categoria categoria, Producto p) {
        categoria.getProducto().add(p);
    }

    public void eliminarProducto(Categoria categoria, Producto p) {
        categoria.getProducto().remove(p);
    }
}

