package org.example.Services;

import org.example.Models.Fabrica;
import org.example.Repositories.FabricaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FabricaService {

    @Autowired
    private FabricaRepository fabricaRepository;

    public List<Fabrica> getAllFabrica() {
        return fabricaRepository.findAll();
    }

    public Fabrica findById(UUID id) {
        return fabricaRepository.findById(id).orElseThrow(()->new RuntimeException("No se pudo encontrar la fabrica con id: "+id));
    }

    public Fabrica save(Fabrica f) {
        return fabricaRepository.save(f);
    }

    public void delete(UUID id) {
        fabricaRepository.deleteById(id);
    }
}
