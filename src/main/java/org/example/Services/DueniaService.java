package org.example.Services;

import org.example.Models.Duenia;
import org.example.Repositories.DueniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class DueniaService {

    @Autowired
    private DueniaRepository dueniaRepository;

    public List<Duenia> getAllDuenia() {
        return dueniaRepository.findAll();
    }

}
