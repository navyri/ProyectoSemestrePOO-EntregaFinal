package org.example.Services;

import org.example.Models.LineaCompras;
import org.example.Repositories.LineaComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineaComprasService {

    @Autowired
    private LineaComprasRepository lineaComprasRepository;

    public List<LineaCompras> getAllLineaCompras() {
        return lineaComprasRepository.findAll();
    }

}
