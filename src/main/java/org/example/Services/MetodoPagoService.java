package org.example.Services;

import org.example.Models.MetodoPago;
import org.example.Repositories.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public List<MetodoPago> getAllMetodoPago() {
        return metodoPagoRepository.findAll();
    }
}
