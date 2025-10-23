package org.example.Controller;

import org.example.Models.DesarrolladorProducto;
import org.example.Services.DesarrolladorProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/desarrolladorproducto")
public class DesarrolladorProductoController {

    private DesarrolladorProductoService objDesarrolladorProducto;

    @GetMapping
    public List<DesarrolladorProducto> getAll() {
        return objDesarrolladorProducto.getAllDesarrolladorProducto();
    }


}
