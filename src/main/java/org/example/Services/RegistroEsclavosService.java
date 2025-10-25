package org.example.Services;

import org.example.Models.RegistroEsclavos;
import org.example.Repositories.RegistroEsclavosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

public class RegistroEsclavosService {

    @Autowired
    private RegistroEsclavosRepository registroEsclavosRepository;

    public List<RegistroEsclavos> getAllRegistroEsclavos() {
        return registroEsclavosRepository.findAll();
    }

    public Optional<RegistroEsclavos> findById(Long id) { return registroEsclavosRepository.findById(id); }
    public RegistroEsclavos save(RegistroEsclavos r) { return registroEsclavosRepository.save(r); }
    public void delete(Long id) { registroEsclavosRepository.deleteById(id); }
}
