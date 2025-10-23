package org.example.Controller;

import org.example.Models.MetodoPago;
import org.example.Services.MetodoPagoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/metodopago")
public class MetodoPagoController {

    private MetodoPagoService objMetodoPago;

    @GetMapping
    public List<MetodoPago> getAll() {
        return objMetodoPago.getAllMetodoPago();
    }

}
