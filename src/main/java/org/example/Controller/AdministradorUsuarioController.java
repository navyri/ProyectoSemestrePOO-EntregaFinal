package org.example.Controller;

import org.example.Models.AdministradorUsuario;
import org.example.Services.AdministradorUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/administradorusuario")
public class AdministradorUsuarioController {

    @Autowired
    private final AdministradorUsuarioService objAdministradorUsuario;

    @Autowired
    public AdministradorUsuarioController(AdministradorUsuarioService service) {
        this.objAdministradorUsuario = service;
    }

    @GetMapping
    public List<AdministradorUsuario> getAll() {
        return objAdministradorUsuario.getAllAdministradoresUsuario();
    }

    @PostMapping
    public AdministradorUsuario crear(@RequestBody AdministradorUsuario admin) {
        return objAdministradorUsuario.crearAdministrador(admin);
    }

    @PutMapping("/{id}")
    public AdministradorUsuario actualizar(@PathVariable UUID id, @RequestBody AdministradorUsuario admin) {
        admin.setId(id);
        return objAdministradorUsuario.actualizarAdministrador(admin);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable UUID id) {
        objAdministradorUsuario.eliminarAdministrador(id);
    }

    @PostMapping("/suspender")
    public boolean suspenderUsuario(@RequestParam String email, @RequestBody AdministradorUsuario admin) {
        return objAdministradorUsuario.suspenderUsuario(email, admin);
    }

    @PostMapping("/reactivar")
    public boolean reactivarUsuario(@RequestParam String email, @RequestBody AdministradorUsuario admin) {
        return objAdministradorUsuario.reactivarUsuario(email, admin);
    }
}
