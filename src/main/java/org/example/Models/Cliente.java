package org.example.Models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "cliente")
@DiscriminatorValue("cliente")
public class Cliente extends Usuario {

    private String direccionEnvio;
    private String telefono;

    public Cliente(UUID id, String nombre, String email, String passwordHash, String rol, Date fechaRegistro, boolean estadoCuenta) {
        super(id, nombre, email, passwordHash, rol, fechaRegistro, estadoCuenta);
    }

    protected Cliente() {
        super();
    }

    public String getDireccionEnvio() {
        return this.direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
