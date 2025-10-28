package org.example.Controller;

import org.example.Models.AdministradorContenido;
import org.example.Models.AdministradorUsuario;
import org.example.Models.ConsejoSombrio;
import org.example.Services.ConsejoSombrioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/consejosombrio")
public class ConsejoSombrioController {

    private ConsejoSombrioService objConsejoSombrio;

    @GetMapping
    public List<ConsejoSombrio> getAll() {
        return objConsejoSombrio.getAllConsejoSombrio();
    }

    @GetMapping("/{id}")
    public ConsejoSombrio obtenerConsejoPorId(@PathVariable UUID id) {
        return objConsejoSombrio.getConsejoById(id)
                .orElseThrow(() -> new RuntimeException("Consejo no encontrado con ID: " + id));
    }

    @PostMapping
    public ConsejoSombrio crearConsejo(@RequestBody ConsejoSombrio consejo) {
        return objConsejoSombrio.crearConsejo(consejo);
    }

    @PutMapping("/{id}")
    public ConsejoSombrio actualizarConsejo(@PathVariable UUID id, @RequestBody ConsejoSombrio consejo) {
        return objConsejoSombrio.actualizarConsejo(id, consejo);
    }

    @DeleteMapping("/{id}")
    public void eliminarConsejo(@PathVariable UUID id) {
        objConsejoSombrio.eliminarConsejo(id);
    }

    // Relacion entre consejos y administradores

    @PostMapping("/{id}/agregar-admin-contenido")
    public ConsejoSombrio agregarAdminContenido(@PathVariable UUID id, @RequestBody AdministradorContenido admin) {
        return objConsejoSombrio.agregarAdministradorContenido(id, admin);
    }

    @PostMapping("/{id}/agregar-admin-usuario")
    public ConsejoSombrio agregarAdminUsuario(@PathVariable UUID id, @RequestBody AdministradorUsuario admin) {
        return objConsejoSombrio.agregarAdministradorUsuario(id, admin);
    }
}
