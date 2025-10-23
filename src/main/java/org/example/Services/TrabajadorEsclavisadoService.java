package org.example.Services;

import org.example.Models.TrabajadorEsclavisado;
import org.example.Repositories.TrabajadorEsclavisadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class TrabajadorEsclavisadoService {

    @Autowired
    private TrabajadorEsclavisadoRepository trabajadorEsclavisadoRepository;

    public List<TrabajadorEsclavisado> getAllTrabajadorEsclavisado() {
        return trabajadorEsclavisadoRepository.findAll();
    }
}
