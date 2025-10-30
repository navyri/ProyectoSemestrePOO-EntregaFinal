package org.example.Services;

import org.example.Models.TrabajadorEsclavisado;
import org.example.Repositories.TrabajadorEsclavisadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TrabajadorEsclavisadoService {

    @Autowired
    private TrabajadorEsclavisadoRepository trabajadorEsclavisadoRepository;

    public List<TrabajadorEsclavisado> getAllTrabajadorEsclavisado() {
        return trabajadorEsclavisadoRepository.findAll();
    }
    public Optional<TrabajadorEsclavisado> findById(UUID id) {
        return trabajadorEsclavisadoRepository.findById(id);
    }

    public TrabajadorEsclavisado save(TrabajadorEsclavisado t) {
        return trabajadorEsclavisadoRepository.save(t);
    }

    public void delete(UUID id) {
        trabajadorEsclavisadoRepository.deleteById(id);
    }
}
