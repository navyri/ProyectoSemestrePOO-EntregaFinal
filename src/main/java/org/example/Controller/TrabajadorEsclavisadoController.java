package org.example.Controller;

import org.example.Models.TrabajadorEsclavisado;
import org.example.Services.TrabajadorEsclavisadoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/trabajadoresclavisado")
public class TrabajadorEsclavisadoController {

    private TrabajadorEsclavisadoService objTrabajadorEsclavisado;

    @GetMapping
    public List<TrabajadorEsclavisado> getAll() {
        return objTrabajadorEsclavisado.getAllTrabajadorEsclavisado();
    }



}
