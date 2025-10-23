package org.example.Controller;

import org.example.Models.Cliente;
import org.example.Services.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private ClienteService objCliente;

    @GetMapping
    public List<Cliente> getAll() {
        return objCliente.getAllCliente();
    }

}
