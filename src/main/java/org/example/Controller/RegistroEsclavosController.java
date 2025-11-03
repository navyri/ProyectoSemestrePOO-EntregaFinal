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
    public RegistroEsclavos getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public RegistroEsclavos create(@RequestBody RegistroEsclavos r) {
        return service.save(r);
    }

    @PutMapping("/{id}")
    public RegistroEsclavos update(@PathVariable UUID id, @RequestBody RegistroEsclavos rDetalles) {
        return service.update(id, rDetalles);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}