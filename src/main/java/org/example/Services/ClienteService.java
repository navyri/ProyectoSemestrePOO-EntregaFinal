package org.example.Services;

import org.example.Models.Cliente;
import org.example.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllCliente() {
        return clienteRepository.findAll();
    }

}
