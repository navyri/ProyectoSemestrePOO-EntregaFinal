package org.example.Services;

import org.example.Models.Duenia;
import org.example.Repositories.DueniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DueniaService {

    @Autowired
    private DueniaRepository dueniaRepository;

    public List<Duenia> getAllDuenia() {
        return dueniaRepository.findAll();
    }
    public Optional<Duenia> findById(Long id) { return dueniaRepository.findById(id); }
    public Duenia save(Duenia d) { return dueniaRepository.save(d); }

    public boolean eliminarDuenia(Long id) {
        if (dueniaRepository.existsById(id)) {
            dueniaRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Duenia actualizarDuenia(Long id, Duenia dueniaActualizada) {
        return dueniaRepository.findById(id).map(duenia -> {
            duenia.setClaveMaestra(dueniaActualizada.getClaveMaestra());
            duenia.setFechaCoronacion(dueniaActualizada.getFechaCoronacion());
            duenia.setRegistros(dueniaActualizada.getRegistros());
            return dueniaRepository.save(duenia);
        }).orElse(null);
    }
}
