package org.example.Services;

import org.example.Models.LineaCarrito;
import org.example.Repositories.LineaCarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineaCarritoService {

    @Autowired
    private LineaCarritoRepository lineaCarritoService;

    public List<LineaCarrito> getAllLineaCarrito() {
        return lineaCarritoService.findAll();
    }
}
