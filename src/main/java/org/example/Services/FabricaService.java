package org.example.Services;

import org.example.Models.Fabrica;
import org.example.Repositories.FabricaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FabricaService {

    @Autowired
    private FabricaRepository fabricaRepository;

    public List<Fabrica> getAllFabrica() {
        return fabricaRepository.findAll();
    }
    public Optional<Fabrica> findById(UUID id) { return fabricaRepository.findById(id); }
    public Fabrica save(Fabrica f) { return fabricaRepository.save(f); }
    public void delete(UUID id) { fabricaRepository.deleteById(id); }
}
