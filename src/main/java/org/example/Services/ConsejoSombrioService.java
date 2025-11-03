package org.example.Services;

import org.example.Models.AdministradorContenido;
import org.example.Models.AdministradorUsuario;
import org.example.Models.ConsejoSombrio;
import org.example.Repositories.ConsejoSombrioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConsejoSombrioService {

    @Autowired
    private ConsejoSombrioRepository consejoSombrioRepository;

    public List<ConsejoSombrio> getAllConsejoSombrio() {
        return consejoSombrioRepository.findAll();
    }

    public Optional<ConsejoSombrio> getConsejoById(UUID id) {
        return consejoSombrioRepository.findById(id);
    }

    public ConsejoSombrio crearConsejo(ConsejoSombrio consejo) {
        return consejoSombrioRepository.save(consejo);
    }

    public ConsejoSombrio actualizarConsejo(UUID id, ConsejoSombrio consejoActualizado) {
        return consejoSombrioRepository.findById(id)
                .map(consejo -> {
                    consejo.setNombreClave(consejoActualizado.getNombreClave());
                    consejo.setAdministradoresContenido(consejoActualizado.getAdministradoresContenido());
                    consejo.setAdministradoresUsuario(consejoActualizado.getAdministradoresUsuario());
                    return consejoSombrioRepository.save(consejo);
                })
                .orElseThrow(() -> new RuntimeException("Consejo no encontrado con ID: " + id));
    }

    public void eliminarConsejo(UUID id) {
        consejoSombrioRepository.deleteById(id);
    }

    public ConsejoSombrio agregarAdministradorContenido(UUID consejoId, AdministradorContenido adminContenido) {
        ConsejoSombrio consejo = consejoSombrioRepository.findById(consejoId)
                .orElseThrow(() -> new RuntimeException("Consejo no encontrado"));
        consejo.getAdministradoresContenido().add(adminContenido);
        return consejoSombrioRepository.save(consejo);
    }

    public ConsejoSombrio agregarAdministradorUsuario(UUID consejoId, AdministradorUsuario adminUsuario) {
        ConsejoSombrio consejo = consejoSombrioRepository.findById(consejoId)
                .orElseThrow(() -> new RuntimeException("Consejo no encontrado"));
        consejo.getAdministradoresUsuario().add(adminUsuario);
        return consejoSombrioRepository.save(consejo);
    }
}
