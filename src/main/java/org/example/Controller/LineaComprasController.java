package org.example.Controller;

import org.example.Models.LineaCompras;
import org.example.Services.LineaComprasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/lineacompras")
public class LineaComprasController {

    private LineaComprasService objLineaCompras;

    @GetMapping
    public List<LineaCompras> getAll() {
        return objLineaCompras.getAllLineaCompras();
    }


}
