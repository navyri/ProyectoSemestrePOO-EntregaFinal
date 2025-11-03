package org.example.Services;

import org.example.Models.Duenia;
import org.example.Repositories.DueniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DueniaService {

    @Autowired
    private DueniaRepository dueniaRepository;

    public List<Duenia> getAllDuenia() {
        return dueniaRepository.findAll();
    }

    public Duenia findById(UUID id) {
        return dueniaRepository.findById(id).orElseThrow(()->new RuntimeException("No se encontro una duenia cone l id"+id));
    }

    public Duenia save(Duenia d) {
        if(dueniaRepository.count() > 0 && d.getId() == null) {
            throw new IllegalStateException("Solo puede existir una Dueña en el sistema");
        }
        return dueniaRepository.save(d);
    }

    public boolean eliminarDuenia(UUID id) {
        if (dueniaRepository.existsById(id)) {
            dueniaRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Duenia actualizarDuenia(UUID id, Duenia dueniaActualizada) {
        return dueniaRepository.findById(id).map(duenia -> {
            duenia.setClaveMaestra(dueniaActualizada.getClaveMaestra());
            duenia.setFechaCoronacion(dueniaActualizada.getFechaCoronacion());
            duenia.setRegistros(dueniaActualizada.getRegistros());
            return dueniaRepository.save(duenia);
        }).orElseThrow(()-> new RuntimeException("No se pudo actualizar la duenia correctamente "));
    }
}
