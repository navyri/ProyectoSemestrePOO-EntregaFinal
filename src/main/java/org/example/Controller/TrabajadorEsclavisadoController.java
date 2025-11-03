package org.example.Controller;

import org.example.Models.TrabajadorEsclavisado;
import org.example.Services.TrabajadorEsclavisadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/trabajadoresclavisado")
public class TrabajadorEsclavisadoController {

    private TrabajadorEsclavisadoService objTrabajadorEsclavisado;

    public TrabajadorEsclavisadoController(TrabajadorEsclavisadoService objTrabajadorEsclavisado) {
        this.objTrabajadorEsclavisado = objTrabajadorEsclavisado;
    }

    @GetMapping
    public List<TrabajadorEsclavisado> getAllTrabajadorEsclavisado() {
        return objTrabajadorEsclavisado.getAllTrabajadorEsclavisado();
    }

    @GetMapping("/{id}")
    public TrabajadorEsclavisado getById(@PathVariable UUID id) { return objTrabajadorEsclavisado.findById(id); }

    @PostMapping
    public TrabajadorEsclavisado create(@RequestBody TrabajadorEsclavisado t) { return objTrabajadorEsclavisado.save(t); }

    @PutMapping("/{id}")
    public TrabajadorEsclavisado update(@PathVariable UUID id, @RequestBody TrabajadorEsclavisado tDetails) {
        return objTrabajadorEsclavisado.update(id, tDetails);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) { objTrabajadorEsclavisado.delete(id); }
}

