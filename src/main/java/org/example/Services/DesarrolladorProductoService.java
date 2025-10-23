package org.example.Services;

import org.example.Models.DesarrolladorProducto;
import org.example.Repositories.DesarrolladorProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class DesarrolladorProductoService {

    @Autowired
    private DesarrolladorProductoRepository desarrolladorProductoRepository;

    public List<DesarrolladorProducto> getAllDesarrolladorProducto() {
        return desarrolladorProductoRepository.findAll();
    }

}
