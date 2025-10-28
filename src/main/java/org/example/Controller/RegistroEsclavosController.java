package org.example.Controller;

import org.example.Models.RegistroEsclavos;
import org.example.Services.RegistroEsclavosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/registros")
public class RegistroEsclavosController {

    private RegistroEsclavosService service;

    public RegistroEsclavosController(RegistroEsclavosService service) {
        this.service = service;
    }

    @GetMapping
    public List<RegistroEsclavos> getAll() {
        return service.getAllRegistroEsclavos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroEsclavos> getById(@PathVariable UUID id) {
        Optional<RegistroEsclavos> registro = service.findById(id);
        return registro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RegistroEsclavos create(@RequestBody RegistroEsclavos r) {
        return service.save(r);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroEsclavos> update(@PathVariable UUID id, @RequestBody RegistroEsclavos rDetalles) {
        Optional<RegistroEsclavos> optionalRegistro = service.findById(id);

        if (optionalRegistro.isPresent()) {
            RegistroEsclavos existingRegistro = optionalRegistro.get();
            existingRegistro.setUltimoAcceso(rDetalles.getUltimoAcceso());
            existingRegistro.setNivelCifrado(rDetalles.getNivelCifrado());
            existingRegistro.setTrabajadores(rDetalles.getTrabajadores());
            existingRegistro.setDuenia(rDetalles.getDuenia());

            return ResponseEntity.ok(service.save(existingRegistro));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}