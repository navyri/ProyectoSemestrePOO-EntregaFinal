package org.example.Controller;

import org.example.Models.Categoria;
import org.example.Services.CategoriaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    private CategoriaService objCategoria;

    @GetMapping
    public List<Categoria> getAll() {
        return objCategoria.getAllCategoria();
    }
}
