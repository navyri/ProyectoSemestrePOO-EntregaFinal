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

    @ManyToMany
    @JoinTable(
            name = "consejo_admin_contenido",
            joinColumns = @JoinColumn(name = "consejo_id"),
            inverseJoinColumns = @JoinColumn(name = "admin_contenido_id")
    )

    private List<AdministradorContenido> administradorContenidos;

    @ManyToMany
    @JoinTable(
            name = "consejo_admin_usuario",
            joinColumns = @JoinColumn(name = "consejo_id"),
            inverseJoinColumns = @JoinColumn(name = "admin_usuario_id")
    )

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombreClave() {
        return nombreClave;
    }

    public void setNombreClave(String nombreClave) {
        this.nombreClave = nombreClave;
    }

    public List<AdministradorContenido> getAdministradoresContenido() {
        return administradorContenidos;
    }

    public void setAdministradoresContenido(List<AdministradorContenido> administradoresContenido) {
        this.administradorContenidos = administradoresContenido;
    }

    public List<AdministradorUsuario> getAdministradoresUsuario() {
        return administradorUsuarios;
    }

    public void setAdministradoresUsuario(List<AdministradorUsuario> administradoresUsuario) {
        this.administradorUsuarios = administradoresUsuario;
    }
}
