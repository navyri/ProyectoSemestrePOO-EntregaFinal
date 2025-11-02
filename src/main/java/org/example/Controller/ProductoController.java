package org.example.Controller;

import org.example.Models.Categoria;
import org.example.Models.Producto;
import org.example.Services.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/productocontroller")
public class ProductoController {

    private ProductoService objProducto;

    @GetMapping
    public List<Producto> getAll() {
        return objProducto.getAllProducto();
    }

    @GetMapping("/{id}")
    public Categoria getById(@PathVariable UUID id) {
        return objProducto.findById(id).getCategoria(); //revisar
    }
}
