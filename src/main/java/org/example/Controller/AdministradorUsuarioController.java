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

    @PostMapping
    public AdministradorUsuario crear(@RequestBody AdministradorUsuario admin) {
        return objAdministradorUsuario.crearAdministrador(admin);
    }

    @PutMapping("/{id}")
    public AdministradorUsuario actualizar( @RequestBody AdministradorUsuario admin) {
        return objAdministradorUsuario.actualizarAdministrador( admin);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable AdministradorUsuario admin) {
        objAdministradorUsuario.eliminarAdministrador(admin);
    }
}
