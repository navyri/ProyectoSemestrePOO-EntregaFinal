package org.example.Controller;

import org.example.Models.AdministradorContenido;
import org.example.Services.AdministradorContenidoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/administradorcontenido")
public class AdministradorContenidoController {

    private AdministradorContenidoService objAdministradorContenido;

    @GetMapping
    public List<AdministradorContenido> getAll() {
        return objAdministradorContenido.getAllAdministradoresContenido();
    }
}
