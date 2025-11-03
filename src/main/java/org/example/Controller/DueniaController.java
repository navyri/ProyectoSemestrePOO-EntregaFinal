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
    public Duenia getById(@PathVariable UUID id) {
        return objDuenia.findById(id);
    }

    @PostMapping
    public Duenia create(@RequestBody Duenia d) {
        return objDuenia.save(d);
    }

    @PutMapping("/{id}")
    public Duenia actualizarDuenia(@PathVariable UUID id, @RequestBody Duenia duenia) {
        return objDuenia.actualizarDuenia(id,duenia);
    }

    @DeleteMapping("/{id}")
    public boolean eliminarDuenia(@PathVariable UUID id) {
        return objDuenia.eliminarDuenia(id);
    }
}
