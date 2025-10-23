package org.example.Services;

import org.example.Models.RegistroEsclavos;
import org.example.Repositories.RegistroEsclavosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class RegistroEsclavosService {

    @Autowired
    private RegistroEsclavosRepository registroEsclavosRepository;

    public List<RegistroEsclavos> getAllRegistroEsclavos() {
        return registroEsclavosRepository.findAll();
    }

}
