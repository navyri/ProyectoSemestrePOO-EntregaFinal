package org.example.Services;

import org.example.Models.Fabrica;
import org.example.Repositories.FabricaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class FabricaService {

    @Autowired
    private FabricaRepository fabricaRepository;

    public List<Fabrica> getAllFabrica() {
        return fabricaRepository.findAll();
    }
}
