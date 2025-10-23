package org.example.Services;

import org.example.Models.ConsejoSombrio;
import org.example.Repositories.ConsejoSombrioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class ConsejoSombrioService {

    @Autowired
    private ConsejoSombrioRepository consejoSombrioRepository;

    public List<ConsejoSombrio> getAllConsejoSombrio() {
        return consejoSombrioRepository.findAll();
    }

}
