package org.example.Controller;

import org.example.Models.Fabrica;
import org.example.Services.FabricaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/fabrica")
public class FabricaController {

    private FabricaService objFabrica;

    @GetMapping
    public List<Fabrica> getAll() {
        return objFabrica.getAllFabrica();
    }


    @GetMapping("/{id}")
    public Optional<Fabrica> getById(@PathVariable UUID id) {
        return objFabrica.findById(id);
    }

    @PostMapping
    public Fabrica create(@RequestBody Fabrica f) {
        return objFabrica.save(f);
    }

    @PutMapping("/{id}")
    public Fabrica update(@PathVariable UUID id, @RequestBody Fabrica f) {
        f.setId(id); return objFabrica.save(f);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        objFabrica.delete(id);
    }

}
