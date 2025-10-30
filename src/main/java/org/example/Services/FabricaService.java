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
    public Fabrica findById(UUID id) { return fabricaRepository.findById(id).orElseThrow(() ->new RuntimeException("No se encontro la fabrica con el id: "+id)); }
    public Fabrica save(Fabrica f) { return fabricaRepository.save(f); }
    public void delete(UUID id) { findById(id);fabricaRepository.deleteById(id); } //Llamar al metodo, verificar que existe, si no lanza la excepcion
}
