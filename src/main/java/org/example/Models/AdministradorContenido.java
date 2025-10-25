package org.example.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.DiscriminatorValue;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "administradores_contenido")
@DiscriminatorValue("administrador_contenido")
public class AdministradorContenido extends Usuario {

    private String permisosEdicion;

    public AdministradorContenido() {
        super();
    }

    public AdministradorContenido(UUID id, String nombre, String email, String passwordHash, String rol, Date fechaRegistro, boolean estadoCuenta, String permisosEdicion) {
        super(id, nombre, email, passwordHash, rol, fechaRegistro, estadoCuenta);
        this.permisosEdicion = permisosEdicion;
    }

    public String getPermisosEdicion() {
        return permisosEdicion;
    }

    public void setPermisosEdicion(String permisosEdicion) {
        this.permisosEdicion = permisosEdicion;
    }
}