package org.example.Services;

import org.example.Models.Categoria;
import org.example.Models.Producto;
import org.example.Repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategoria() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(UUID id){ return categoriaRepository.findById(id).orElseThrow(() ->new RuntimeException("No se encontro una categoria con el id: "+id));}

    public Categoria update(UUID id, Categoria nuevaCategoria) {
        Categoria existente = findById(id);
        existente.setNombre(nuevaCategoria.getNombre());
        existente.setDescripcion(nuevaCategoria.getDescripcion());
        return categoriaRepository.save(existente);
    }

    public void agregarProducto(Categoria categoria, Producto p) {
        categoria.getProducto().add(p);
    }

    public void eliminarProducto(Categoria categoria, Producto p) {
        if (!categoria.getProducto().contains(p)) {
            throw new RuntimeException("El producto no pertenece a esta categoría");
        }
        categoria.getProducto().remove(p);
    }
}

