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
        public List<TrabajadorEsclavisado> getAllTrabajadorEsclavisado() { return objTrabajadorEsclavisado.getAllTrabajadorEsclavisado(); }

        @GetMapping("/{id}")
        public Optional<TrabajadorEsclavisado> getById(@PathVariable UUID id) { return objTrabajadorEsclavisado.findById(id); }

        @PostMapping
        public TrabajadorEsclavisado create(@RequestBody TrabajadorEsclavisado t) { return objTrabajadorEsclavisado.save(t); }

        @PutMapping("/{id}")
        public ResponseEntity<TrabajadorEsclavisado> update(@PathVariable UUID id, @RequestBody TrabajadorEsclavisado tDetails) {
            // Corregido el tipo y mejorada la lógica de actualización
            Optional<TrabajadorEsclavisado> optionalT = objTrabajadorEsclavisado.findById(id);

            if (optionalT.isPresent()) {
                TrabajadorEsclavisado existingT = optionalT.get();

                // Copiamos los campos del objeto de la petición (tDetails) al objeto existente
                existingT.setNombre(tDetails.getNombre());
                existingT.setPaisOrigen(tDetails.getPaisOrigen());
                existingT.setEdad(tDetails.getEdad());
                existingT.setFechaCaptura(tDetails.getFechaCaptura());
                existingT.setSalud(tDetails.getSalud());
                existingT.setAsignadoA(tDetails.getAsignadoA());
                existingT.setRegistro(tDetails.getRegistro());

                // Guardamos la entidad existente actualizada
                return ResponseEntity.ok(objTrabajadorEsclavisado.save(existingT));
            } else {
                // Si no se encuentra, devuelve 404
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable UUID id) { objTrabajadorEsclavisado.delete(id); }
    }

