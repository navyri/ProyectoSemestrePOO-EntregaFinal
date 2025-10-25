package org.example.Models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "consejo_sombrio")
public class ConsejoSombrio {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String nombreClave;
    private List<AdministradorContenido> administradorContenidos;
    private List<AdministradorUsuario> administradorUsuarios;

    // CONSTRUCTOR
    public ConsejoSombrio(UUID id, String nombreClave) {
        this.id = id;
        this.nombreClave = nombreClave;
        this.administradorContenidos = new ArrayList<>();
        this.administradorUsuarios = new ArrayList<>();
    }

    public ConsejoSombrio() {

    }
}
