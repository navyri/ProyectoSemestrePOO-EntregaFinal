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
    public TrabajadorEsclavisado findById(UUID id) { return trabajadorEsclavisadoRepository.findById(id).orElseThrow(() ->new RuntimeException("No se encontro trabajador con el id"+id)); }
    public TrabajadorEsclavisado save(TrabajadorEsclavisado t) { return trabajadorEsclavisadoRepository.save(t); }
    public void delete(UUID id) { findById(id);trabajadorEsclavisadoRepository.deleteById(id); }
    public TrabajadorEsclavisado update(UUID id, TrabajadorEsclavisado tDetails) {
        TrabajadorEsclavisado nuevoT = findById(id);

        nuevoT.setNombre(tDetails.getNombre());
        nuevoT.setPaisOrigen(tDetails.getPaisOrigen());
        nuevoT.setEdad(tDetails.getEdad());
        nuevoT.setFechaCaptura(tDetails.getFechaCaptura());
        nuevoT.setSalud(tDetails.getSalud());
        nuevoT.setAsignadoA(tDetails.getAsignadoA());
        nuevoT.setRegistro(tDetails.getRegistro());

        return trabajadorEsclavisadoRepository.save(nuevoT);
    }
}
