package org.example.Controller;

import org.example.Models.Usuario;
import org.example.Services.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private UsuarioService objUsuario;

    @GetMapping
    public List<Usuario> getAll() {
        return objUsuario.getAllUsuario();
    }

}
