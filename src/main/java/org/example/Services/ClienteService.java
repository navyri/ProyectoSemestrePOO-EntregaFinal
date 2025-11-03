package org.example.Services;

import org.example.Models.Cliente;
import org.example.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllCliente() {
        return clienteRepository.findAll();
    }

}
