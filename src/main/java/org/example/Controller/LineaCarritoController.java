package org.example.Controller;

import org.example.Models.LineaCarrito;
import org.example.Services.LineaCarritoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/lineacarrito")
public class LineaCarritoController {

    private LineaCarritoService objLineaCarrito;

    @GetMapping
    public List<LineaCarrito> getAll() {
        return objLineaCarrito.getAllLineaCarrito();
    }

}
