package org.example.Controller;

import org.example.Models.Duenia;
import org.example.Services.DueniaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/duenia")
public class DueniaController {

    private DueniaService objDuenia;

    public DueniaController(DueniaService objDuenia) {
        this.objDuenia = objDuenia;
    }

    @GetMapping
    public List<Duenia> getAll() {
        return objDuenia.getAllDuenia();
    }

    @GetMapping("/{id}")
    public Optional<Duenia> getById(@PathVariable Long id) { return objDuenia.findById(id); }

    @PostMapping
    public Duenia create(@RequestBody Duenia d) { return objDuenia.save(d); }

    @PutMapping("/{id}")
    public ResponseEntity<Duenia> actualizarDuenia(@PathVariable Long id, @RequestBody Duenia duenia) {
        Duenia actualizada = objDuenia.actualizarDuenia(id, duenia);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDuenia(@PathVariable Long id) {
        boolean eliminada = objDuenia.eliminarDuenia(id);
        return eliminada ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
