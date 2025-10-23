package org.example.Controller;

import org.example.Models.Producto;
import org.example.Services.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/productocontroller")
public class ProductoController {

    private ProductoService objProducto;

    @GetMapping
    public List<Producto> getAll() {
        return objProducto.getAllProducto();
    }

}
