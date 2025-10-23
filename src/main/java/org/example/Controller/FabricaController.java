package org.example.Controller;

import org.example.Models.Fabrica;
import org.example.Services.FabricaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/fabrica")
public class FabricaController {

    private FabricaService objFabrica;

    @GetMapping
    public List<Fabrica> getAll() {
        return objFabrica.getAllFabrica();
    }
}
