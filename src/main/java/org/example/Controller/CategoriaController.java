package org.example.Controller;

import org.example.Models.Categoria;
import org.example.Models.Producto;
import org.example.Services.CategoriaService;
import org.example.Services.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    private CategoriaService objCategoria;
    private ProductoService objProducto;

    @GetMapping
    public List<Categoria> getAll() {
        return objCategoria.getAllCategoria();
    }

    @GetMapping("/{id}")
    public Categoria getById(@PathVariable UUID id) {
        return objCategoria.findById(id);
    }


    @PutMapping("/{id}")
    public Categoria update(@PathVariable UUID id, @RequestBody Categoria categoria) {
        return objCategoria.update(id, categoria);
    }

    @PostMapping("/{id}/productos")
    public Categoria agregarProductoACategoria(@PathVariable UUID id, @RequestBody Producto producto) {
        Categoria categoria = objCategoria.findById(id);
        objCategoria.agregarProducto(categoria, producto);
        return categoria;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID categoriaId, @PathVariable UUID productoId) {
        objCategoria.eliminarProducto(objCategoria.findById(categoriaId), objProducto.findById(productoId));
    }
}
