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

    public RegistroEsclavos findById(Long id) { return registroEsclavosRepository.findById(id).orElseThrow(() -> new RuntimeException("Registro de esclavos no encontrado con id: "+id)); }
    public RegistroEsclavos save(RegistroEsclavos r) { return registroEsclavosRepository.save(r); }
    public RegistroEsclavos update(Long id, RegistroEsclavos detalles) {
        RegistroEsclavos existente = findById(id);
        existente.setUltimoAcceso(detalles.getUltimoAcceso());
        existente.setNivelCifrado(detalles.getNivelCifrado());
        existente.setTrabajadores(detalles.getTrabajadores());
        existente.setDuenia(detalles.getDuenia());
        return registroEsclavosRepository.save(existente);
    }
    public void delete(Long id) { findById(id); registroEsclavosRepository.deleteById(id); } //llamo al metodo por si ocurre un error y para validar que existe
}
