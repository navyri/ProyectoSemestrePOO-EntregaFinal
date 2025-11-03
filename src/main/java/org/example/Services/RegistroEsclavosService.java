package org.example.Services;

import org.example.Models.RegistroEsclavos;
import org.example.Repositories.RegistroEsclavosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegistroEsclavosService {

    @Autowired
    private RegistroEsclavosRepository registroEsclavosRepository;

    public List<RegistroEsclavos> getAllRegistroEsclavos() {
        return registroEsclavosRepository.findAll();
    }

    public RegistroEsclavos findById(UUID id) {
        return registroEsclavosRepository.findById(id).orElseThrow(() -> new RuntimeException("Registro de esclavos no encontrado con id: "+id));
    }

    public RegistroEsclavos save(RegistroEsclavos r) {
        return registroEsclavosRepository.save(r);
    }

    public RegistroEsclavos update(UUID id, RegistroEsclavos detalles) {
        RegistroEsclavos existente = findById(id);
        existente.setUltimoAcceso(detalles.getUltimoAcceso());
        existente.setNivelCifrado(detalles.getNivelCifrado());
        existente.setTrabajadores(detalles.getTrabajadores());
        existente.setDuenia(detalles.getDuenia());
        return registroEsclavosRepository.save(existente);
    }

    public void delete(UUID id) {
        findById(id); registroEsclavosRepository.deleteById(id);
    }
}
