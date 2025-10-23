package org.example.Controller;

import org.example.Models.Compra;
import org.example.Services.CompraService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/compra")
public class CompraController {

    private CompraService objCompra;

    @GetMapping
    public List<Compra> getAll() {
        return objCompra.getAllCompra();
    }

}
