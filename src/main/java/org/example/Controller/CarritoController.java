package org.example.Controller;

import org.example.Models.Carrito;
import org.example.Services.CarritoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private CarritoService objCarrito;

    @GetMapping
    public List<Carrito> getAll() {
        return objCarrito.getAllCarrito();
    }

}
