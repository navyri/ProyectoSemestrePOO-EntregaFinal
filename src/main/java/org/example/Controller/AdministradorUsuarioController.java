package org.example.Controller;

import org.example.Models.AdministradorUsuario;
import org.example.Services.AdministradorUsuarioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/administradorusuario")
public class AdministradorUsuarioController {
    private AdministradorUsuarioService objAdministradorUsuario;

    @GetMapping
    public List<AdministradorUsuario> getAll() {
        return objAdministradorUsuario.getAllAdministradoresUsuario();
    }
}
