package org.example.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.DiscriminatorValue;
import java.util.UUID;
import java.util.Date;

@Entity
@Table(name = "administradores_usuario")
@DiscriminatorValue("administrador_usuario")
public class AdministradorUsuario extends Usuario {

    private int nivelAcceso;

    public AdministradorUsuario() {
        super();
    }

    public AdministradorUsuario(UUID id, String nombre, String email, String passwordHash, String rol, Date fechaRegistro, boolean estadoCuenta, int nivelAcceso) {
        super(id, nombre, email, passwordHash, rol, fechaRegistro, estadoCuenta);
        this.nivelAcceso = nivelAcceso;
    }

    public int getNivelAcceso() {
        return this.nivelAcceso;
    }

    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }
}
