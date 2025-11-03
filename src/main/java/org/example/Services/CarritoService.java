package org.example.Services;

import org.example.Models.Carrito;
import org.example.Repositories.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> getAllCarrito() {
        return carritoRepository.findAll();
    }

}
